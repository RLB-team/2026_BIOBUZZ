/* 2026
   Authors: Wade Kuhn
   Game: BIOBUZZ
   Main.java provides an efficient, class organized OpMode */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Testing OpMode")
public final class main extends LinearOpMode {
   @Override
   public void runOpMode() {
      double exceptionTime = 0;
      double loopTime = 0;
      double sysVoltage;

      double x, y, rx;
      double aButton, bButton, xButton;
      double leftTrigger;

      final var pidfA = new PIDFCoefficients(50, 15, 0, 0);

      final var frontLeft =  hardwareMap.get(DcMotorEx.class, "Up Left");
      final var frontRight = hardwareMap.get(DcMotorEx.class, "Up Right");
      final var backLeft =   hardwareMap.get(DcMotorEx.class, "Down Left");
      final var backRight =  hardwareMap.get(DcMotorEx.class, "Down Right");
      final var intaker =    hardwareMap.get(DcMotorEx.class, "Intake");
      final var indexer =    hardwareMap.get(DcMotorEx.class, "Servo Hub");
      final var outtake1 =   hardwareMap.get(DcMotorEx.class, "Outake 1");
      final var outtake2 =   hardwareMap.get(DcMotorEx.class, "Outake 2");
      
      final var battery =    hardwareMap.get(VoltageSensor.class, "Control Hub");

      final DcMotorEx[] brakeMotors =    { frontLeft, frontRight, backLeft, backRight };
      final DcMotorEx[] encoderMotors =  { frontLeft, frontRight, backLeft, backRight, indexer };
      final DcMotorEx[] reversedMotors = { frontLeft, backLeft, outtake1 };

      for (final var motor : brakeMotors)
         motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
      for (final var motor : encoderMotors)
         motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      for (final var motor : reversedMotors)
         motor.setDirection(DcMotorEx.Direction.REVERSE);

      outtake1.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfA);
      outtake2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfA);

      var drive = new drive(frontLeft, frontRight, backLeft, backRight);
      var intake = new intake(intaker, indexer);
      var outtake = new outtake(outtake1, outtake2);
      var console = new console(telemetry, outtake1, outtake2);
      var logger = new logger();

      var loopTimer = new ElapsedTime();

      waitForStart();

      while (opModeIsActive()) {
         loopTimer.reset();

         sysVoltage = battery.getVoltage();

         x = -gamepad1.left_stick_x * 1.3;
         y = -gamepad1.left_stick_y;
         rx = gamepad1.right_stick_x;

         aButton = gamepad1.a ? 1 : 0;
         bButton = gamepad1.b ? 1 : 0;
         xButton = gamepad1.x ? 1 : 0;

         leftTrigger = gamepad1.left_trigger;

         drive.run(x, y, rx);
         intake.run(aButton, bButton, xButton);
         outtake.run(leftTrigger);
         console.run(sysVoltage, exceptionTime, loopTime);

         exceptionTime = logger.run(sysVoltage, exceptionTime, loopTime);

         loopTime = loopTimer.milliseconds();
      }
   }
}