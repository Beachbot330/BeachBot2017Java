package org.usfirst.frc330.commands.autocommands;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.*;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.ShooterConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class LeftBoilerRightGearSprint extends BBCommandGroup {

    public LeftBoilerRightGearSprint() {
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
    	addSequential(new DriveWaypoint(0, 77, 3, 4, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow )); //drive before turn
    	addSequential(new WaitCommand(0.3));
    	
    	// Drive to Airship
    	//double x, double y, double tolerance, double timeout, PIDGains gains
    	addSequential(new TurnGyroWaypoint(-39, 106, 3, 1, ChassisConst.GyroTurnLow));
    	addSequential(new DriveWaypoint(-39, 106, 6, 3, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow ));
    	
    	// Drop off gear
    	addSequential(new GearDropOff());
    	addSequential(new WaitCommand(0.3));
    	
    	// Prepare to shoot
    	addSequential(new IgniteSun());
    	addParallel(new PrepareToShoot(ShooterConst.FARSIDE_AUTO));
    	
    	// Drive back from airship
    	addSequential(new DriveWaypointBackward(0, 77, 3, 4, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow ));
    	
    	// Drive towards boiler
    	addSequential(new TurnGyroWaypoint(-150, 20, 3, 3, ChassisConst.GyroTurnLow ));
    	addSequential(new DriveDistanceAtCurAngle(48, 3, 2, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
    	//double distance, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains

    	// Aim and shoot
    	addSequential(new TurnCamera("target", 3.0, 15, 1.5, true, ChassisConst.CAMERA_LOW)); //aim at boiler
    	addParallel(new TurnCamera("target", 3.0, 15, 3, true, ChassisConst.CAMERA_LOW)); //aim at boiler
    	addParallel(new ShootWithWingsAgitate()); // shoot
    	addSequential(new WaitCommand(1.0));
    	//CODE TO STOP SHOOTER NEEDED??
    	addSequential(new ShooterStop());
    
    	// Drive to Open Space
    	addSequential(new WingsClosed()); 
    	addSequential(new DriveWaypointBackward(0, 77, 3, 4, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow )); 
    	
    	// Dash for open space
    	addSequential(new TurnGyroWaypoint(0, ChassisConst.DASH_DISTANCE, 3, 3, ChassisConst.GyroTurnLow )); //turn to hopper 
    	addSequential(new ShiftHigh()); 
    	addSequential(new DriveWaypoint(0, ChassisConst.DASH_DISTANCE, 3, 3, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh ));    	
    	
    }
}
