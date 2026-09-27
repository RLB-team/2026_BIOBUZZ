/* 2026
   Authors: Wade Kuhn
   Game:    BIOBUZZ
   License: GPL V3.0
   Main.java provides an efficient, class organized OpMode */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Testing OpMode")
public final class main extends LinearOpMode
{
   @Override
   public void runOpMode() {
      double exceptionTime = 0;
      double loopTime = 0;
      double sysVoltage;
      int motorSpeedCap = 1;

      double x, y, rx;
      double aButton, bButton, xButton;
      double leftTrigger;

      final var pidfA = new PIDFCoefficients(50, 15, 0, 0);

      final var battery =    hardwareMap.get(VoltageSensor.class, "Control Hub");

      final var frontLeft =  hardwareMap.get(DcMotorEx.class, "Up Left");
      final var frontRight = hardwareMap.get(DcMotorEx.class, "Up Right");
      final var backLeft =   hardwareMap.get(DcMotorEx.class, "Down Left");
      final var backRight =  hardwareMap.get(DcMotorEx.class, "Down Right");
      final var intaker =    hardwareMap.get(DcMotorEx.class, "Intake");
      final var indexer =    hardwareMap.get(DcMotorEx.class, "Servo Hub");
      final var outtake1 =   hardwareMap.get(DcMotorEx.class, "Outake 1");
      final var outtake2 =   hardwareMap.get(DcMotorEx.class, "Outake 2");

      final DcMotorEx[] brakeMotors =   { frontLeft, frontRight, backLeft, backRight };
      final DcMotorEx[] encoderMotors = { frontLeft, frontRight, backLeft, backRight, indexer };
      final DcMotorEx[] reverseMotors = { frontLeft, backLeft, outtake1 };

      for (final var motor : brakeMotors)
         motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
      for (final var motor : encoderMotors)
         motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
      for (final var motor : reverseMotors)
         motor.setDirection(DcMotorEx.Direction.REVERSE);

      outtake1.setPIDFCoefficients(DcMotorEx.RunMode.RUN_USING_ENCODER, pidfA);
      outtake2.setPIDFCoefficients(DcMotorEx.RunMode.RUN_USING_ENCODER, pidfA);

      final var drive =   new drive(frontLeft, frontRight, backLeft, backRight);
      final var intake =  new intake(intaker, indexer);
      final var outtake = new outtake(outtake1, outtake2);
      final var console = new console(telemetry, intaker, outtake1, outtake2);
      final var logger =  new logger();

      final var loopTimer = new ElapsedTime();

      waitForStart();

      while (opModeIsActive()) {
         loopTimer.reset();

         sysVoltage = battery.getVoltage();

         x = -gamepad1.left_stick_x  / motorSpeedCap * 1.3;
         y = -gamepad1.left_stick_y  / motorSpeedCap;
         rx = gamepad1.right_stick_x / motorSpeedCap;

         aButton = gamepad1.a ? 1 : 0;
         bButton = gamepad1.b ? 1 : 0;
         xButton = gamepad1.x ? 1 : 0;
         leftTrigger = gamepad1.left_trigger / 2;

         drive.run(x, y, rx);
         intake.run(aButton, bButton, xButton);
         outtake.run(leftTrigger);
         console.run(sysVoltage, loopTime);
         motorSpeedCap = logger.run(sysVoltage, exceptionTime, loopTime);

         loopTime = loopTimer.milliseconds();
      }
   }
}