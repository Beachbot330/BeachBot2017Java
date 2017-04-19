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
public class LeftBoilerRightGearCatch extends BBCommandGroup {

    public LeftBoilerRightGearCatch() {
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
 
    	//Drive Forward
    	//double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
    	addSequential(new DriveWaypoint(0, 77, 3, 4, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow )); //drive before turn
    	//addSequential(new WaitCommand(0.3)); // Don't see why this is here, so I am removing it --AP 4/18
    	
    	// Drive to Airship
    	//double x, double y, double tolerance, double timeout, PIDGains gains
    	addSequential(new TurnGyroWaypoint(-39, 106, 3, 1, ChassisConst.GyroTurnLow));
    	addSequential(new DriveWaypoint(-39, 106, 6, 3, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow ));
    	
    	//Drop off Gear
    	addSequential(new GearDropOff());
    	addSequential(new WaitCommand(0.3));
    	
    	// Prepare to shoot and drive back from airship
    	addSequential(new IgniteSun());
    	addParallel(new PrepareToShoot(ShooterConst.EXTRA_CLOSE)); //starts rollers and shooter
    	addSequential(new DriveWaypointBackward(-20, 77, 6, 4, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow ));
    	
    	// Drive towards wall
    	addSequential(new TurnGyroWaypointBackward(-20, 20, 6, 3, ChassisConst.GyroTurnLow ));
    	addSequential(new DriveWaypointBackward(-20, 20, 6, 4, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow )); 

    	// Drive across wall
    	addSequential(new TurnGyroWaypoint(-212, 20, 5, 2, ChassisConst.GyroTurnLow)); 
    	addSequential(new ShiftHigh()); 
    	addSequential(new DriveWaypoint(-212, 20, 6, 3, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh ));

    	// Wall bump turn
    	addSequential(new ShiftLow()); 
    	addSequential(new TurnGyroAbs(-130, 5, 2,true, ChassisConst.GyroTurnLow)); 

    	// Shoot
    	addParallel(new ShootWithWingsAgitate()); // shoot
    	
    }
}
