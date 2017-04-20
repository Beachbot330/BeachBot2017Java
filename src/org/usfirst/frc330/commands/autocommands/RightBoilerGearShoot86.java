package org.usfirst.frc330.commands.autocommands;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.*;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.ShooterConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommandGroup;
import org.usfirst.frc330.commands.WaitCommand;

/**
 *
 */
public class RightBoilerGearShoot86 extends BBCommandGroup {

    public RightBoilerGearShoot86() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	addSequential(new DriveCamVisionOn());
    	addSequential(new GearGrab());
    	addSequential(new ShiftLow());
    	
    	// Drive forward
    	//double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
    	addSequential(new DriveWaypoint(0, 90, 3, 4, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow ));
    	addSequential(new WaitCommand(0.3));

    	// Drive to airship
    	//double x, double y, double tolerance, double timeout, PIDGains gains
    	addSequential(new TurnGyroWaypoint(-24, 107, 2, 1, ChassisConst.GyroTurnLow));
    	addSequential(new DriveWaypoint(-24, 107, 9, 3, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow ));
    	
    	// Drop off Gear
    	//addSequential(new WaitCommand(0.3)); // I don't know why this is here, so I am removing it --AP 4/18
    	addSequential(new GearDropOff());
    	addSequential(new WaitCommand(0.3));
    	
    	//Drive away from airship
    	addSequential(new DriveWaypointBackward(5, 85, 3, 4, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow ));
    	
    	//Prepare Shooter
    	addSequential(new IgniteSun());
    	addSequential(new PrepareToShoot(ShooterConst.RIGHT_AUTO));
    	
    	//Aim
    	addSequential(new TurnGyroWaypoint(50, 24, 3, 3, new PIDGains(0.020,0,0.050,0,0.6,1,"GyroTurnLowSpecial") )); //turn to boiler 180
    	addSequential(new TurnCamera("target", 3.0, 15, 3, true, ChassisConst.CAMERA_LOW));
    	addSequential(new WaitCommand(0.5));
    	
    	//Shoot
    	addParallel(new ShootWithWingsAgitate( ));
    	addSequential(new WaitCommand(ShooterConst.TIME_TO_SHOOT_10_BALLS));

    	//Turn and dash
    	addSequential(new TurnGyroWaypoint(5, 250, 3, 3, ChassisConst.GyroTurnLow )); 
    	addSequential(new ShiftHigh());
    	addSequential(new WingsClosed());
    	addSequential(new DriveWaypoint(5, ChassisConst.DASH_DISTANCE, 3, 4, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh ));
    	
    }	
}
