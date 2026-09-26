/* 2026
   Authors: Wade Kuhn
   Game: BIOBUZZ
   Outtake.java is a simple outtake management class */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class outtake {
   private final DcMotorEx outtake1, outtake2;

   public outtake(DcMotorEx outtake1, DcMotorEx outtake2) {
      this.outtake1 = outtake1;
      this.outtake2 = outtake2;
   }

   public void run(final double leftTrigger) {
      outtake1.setPower(leftTrigger);
      outtake2.setPower(leftTrigger);
   }
}