/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.commands.drivecommands;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;
import org.usfirst.frc330.wpilibj.PIDGains;
 
/**
 * Turn in place towards a waypoint using the gyro.
 */
public class TurnGyroWaypoint extends TurnGyroAbs {
    double x, y;
    
    
    public TurnGyroWaypoint(double x, double y, double tolerance, double timeout, PIDGains gains)
    {
        super(0,tolerance,timeout,false,true, gains);
        this.x=x;
        this.y=y;
        
    }

    protected void initialize() {
        calcAngle(x, y);
        super.initialize();
    }

    protected void calcAngle(double x, double y) {
        double curX, curY, deltaX, deltaY, calcAngle, robotAngle;
        
        curX = Robot.chassis.getX();
        curY = Robot.chassis.getY();
        
        deltaX = x - curX;
        deltaY = y - curY;
        
        calcAngle = Math.toDegrees(Math.atan2(deltaX, deltaY));
        
        if (Double.isNaN(calcAngle) || Double.isInfinite(calcAngle))
        {
        	Logger.getInstance().println("Infinite calcAngle in TurnGyroWaypoint", Severity.ERROR);
            calcAngle = 0;
        }
        
        robotAngle = Robot.chassis.getAngle();
        
        if (Double.isNaN(robotAngle) || Double.isInfinite(robotAngle))
        {
        	Logger.getInstance().println("Infinite robotAngle in TurnWaypoint", Severity.ERROR);
            robotAngle = 0;
        }
 
        Logger.getInstance().println("Robot angle: " + robotAngle, Severity.DEBUG);
        Logger.getInstance().println("Calc angle: " + calcAngle, Severity.DEBUG);
        
        if (Math.abs(robotAngle-calcAngle)<180)
        {
            //do nothing
        }
        else if (robotAngle > calcAngle)
        {
            while (Math.abs(robotAngle-calcAngle)>180)
                calcAngle += 360;
        }
        else 
        {
            while (Math.abs(robotAngle-calcAngle)>180)
                calcAngle -= 360;
        }
       Logger.getInstance().println("angle: " + calcAngle);
        
        angle = calcAngle;
    }
}
