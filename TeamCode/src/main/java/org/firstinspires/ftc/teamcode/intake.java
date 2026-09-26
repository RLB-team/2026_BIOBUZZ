package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class intake {
   private final DcMotorEx intaker, indexer;

   public intake(DcMotorEx intaker, DcMotorEx indexer) {
      this.intaker = intaker;
      this.indexer = indexer;
   }

   public void run(double aButton, double bButton, double xButton) {
      intaker.setPower(aButton);
      indexer.setPower(bButton - xButton);
   }
}