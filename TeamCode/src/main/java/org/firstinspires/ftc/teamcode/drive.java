package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class drive {
   private final DcMotorEx frontLeft, frontRight, backLeft, backRight;

   public drive(DcMotorEx frontLeft, DcMotorEx frontRight,
                DcMotorEx backLeft, DcMotorEx backRight) {
      this.frontLeft = frontLeft;
      this.frontRight = frontRight;
      this.backLeft = backLeft;
      this.backRight = backRight;
   }

   public void run(double x, double y, double rx) {
      frontLeft.setPower(rx + x + y);
      backLeft.setPower(rx - x + y);
      frontRight.setPower(rx - x - y);
      backRight.setPower(rx + x - y);
   }
}