/* 2026
   Authors: Wade Kuhn
   Game:    BIOBUZZ
   License: GPL V3.0
   Logger.java manages error + exception handling */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.RobotLog;

public final class logger
{
   int motorSpeedCap = 1;

   public logger() {}

   public int run(final double sysVoltage, double exceptionTime, final double loopTime) {
      if (sysVoltage < 10) {
         exceptionTime += loopTime / 1000;
         motorSpeedCap = 2;
      }
      if (exceptionTime >= 10) {
         RobotLog.w("Exception time is " + exceptionTime + " seconds.");
      }
      return motorSpeedCap;
   }
}