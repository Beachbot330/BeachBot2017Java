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
public class LeftBoilerGearShoot86 extends BBCommandGroup {

    public LeftBoilerGearShoot86() {
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

    	// Drive Forward
    	//double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
    	addSequential(new DriveWaypoint(0, 90, 3, 4, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow ));
    	addSequential(new WaitCommand(0.3));
    	
    	// Drive to Airship
    	//double x, double y, double tolerance, double timeout, PIDGains gains
    	addSequential(new TurnGyroWaypoint(24, 107, 2, 1, ChassisConst.GyroTurnLow));
    	addSequential(new DriveWaypoint(24, 107, 9, 3, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow ));
    	//addSequential(new WaitCommand(0.3)); //Don't know why this is here, so I am removing it --AP 4-19
    	
    	// Drop off gear
    	addSequential(new GearDropOff());
    	addSequential(new WaitCommand(0.3));
    	
    	// Drive away from airship
    	//addSequential(new DriveDistance(-36, 3, ChassisConst.DriveLow)); //away from airship
    	addSequential(new DriveWaypointBackward(-5, 85, 3, 4, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow ));
    	
    	// Prepare to shoot
    	addSequential(new IgniteSun());
    	addSequential(new PrepareToShoot(ShooterConst.RIGHT_AUTO));
    	//double x, double y, double tolerance, double timeout, PIDGains gains
    	
    	// Aim
    	addSequential(new TurnGyroWaypoint(-50, 24, 3, 3, new PIDGains(0.020,0,0.050,0,0.6,1,"GyroTurnLowSpecial") )); //turn to boiler 180
    	addSequential(new TurnCamera("target", 3.0, 15, 1.5, true, ChassisConst.CAMERA_LOW));
    	
    	// Shoot
    	addParallel(new ShootWithWingsAgitate( ));
    	addSequential(new WaitCommand(1.0));

    	// Turn and dash
    	addSequential(new TurnGyroWaypoint(-5, 200, 3, 3, ChassisConst.GyroTurnLow )); 
    	addSequential(new ShiftHigh());
    	addSequential(new WingsClosed());
    	addSequential(new DriveWaypoint(-5, 250, 3, 4, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh ));
    	
    }	
}
