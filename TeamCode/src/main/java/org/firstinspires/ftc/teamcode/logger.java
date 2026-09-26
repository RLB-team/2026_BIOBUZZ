/* 2026
   Authors: Wade Kuhn
   Game: BIOBUZZ
   Logger.java manages error + exception handling */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.RobotLog;

public class logger {
   public logger() {}

   public double run(double sysVoltage, double exceptionTime, double loopTime) {
      if (sysVoltage < 10)
         exceptionTime += loopTime / 1000;
      if (exceptionTime >= 10)
         RobotLog.w("Exception time is " + exceptionTime + " seconds.");
      return exceptionTime;
   }
}