/* 2026
   Authors: Wade Kuhn
   Game: BIOBUZZ
   Console.java manages telemetry and debugging output */

package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class console {
   private final Telemetry telemetry;
   private final DcMotorEx outtake1, outtake2;

   public console(Telemetry telemetry, DcMotorEx outtake1, DcMotorEx outtake2) {
      this.telemetry = telemetry;
      this.outtake1 = outtake1;
      this.outtake2 = outtake2;
   }

   public void run(double sysVoltage, double exceptionTime, double loopTime) {
      telemetry.addData("Outtake Top", outtake1.getVelocity());
      telemetry.addData("Outtake Bottom", outtake2.getVelocity());
      telemetry.addLine("Battery Voltage: " + sysVoltage);
      telemetry.addData("Battery Voltage", sysVoltage);
      telemetry.addData("Exception Time", exceptionTime);
      telemetry.addData("Loop Time", 1000 / loopTime);

      telemetry.update();
   }
}