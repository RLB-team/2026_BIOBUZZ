/* 2026
   Authors: Wade Kuhn
   Game:    BIOBUZZ
   License: GPL V3.0
   Outtake.java is a simple outtake management class */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public final class outtake
{
   private final DcMotorEx outtake1, outtake2;

   public outtake(final DcMotorEx outtake1, final DcMotorEx outtake2) {
      this.outtake1 = outtake1;
      this.outtake2 = outtake2;
   }

   public void run(double leftTrigger) {
      outtake1.setPower(leftTrigger);
      outtake2.setPower(leftTrigger);
   }
}