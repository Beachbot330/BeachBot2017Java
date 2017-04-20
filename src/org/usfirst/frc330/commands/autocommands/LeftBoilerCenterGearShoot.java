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
public class LeftBoilerCenterGearShoot extends BBCommandGroup {

    public LeftBoilerCenterGearShoot() {
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
    	
    	// Drive Forward to airship
    	//double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
    	addSequential(new DriveWaypoint(0, 74, 3, 4, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh ));
    	
    	// Drop off gear
    	addSequential(new GearDropOff());
    	addSequential(new WaitCommand(0.3));
    	
    	// Drive Backwards
    	//double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
    	addSequential(new DriveWaypointBackward(0, 44, 3, 1, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	addSequential(new WaitCommand(0.3));
    	
    	//Prepare to shoot
    	addParallel(new PrepareToShoot(ShooterConst.CENTER_AUTO));
    	
    	// Aim
    	//double x, double y, double tolerance, double timeout, PIDGains gains
    	addParallel(new IgniteSun());
    	addSequential(new TurnGyroWaypoint(-156, 20, 3, 1.5, ChassisConst.GyroTurnLow ));
    	addParallel(new TurnCamera("target", 3.0, 15, 6, true, ChassisConst.CAMERA_LOW));
    	addSequential(new WaitCommand(0.5));
    	
    	// Shoot
    	addSequential(new ShootWithWingsAgitate( ));
    	
    	
    	
    	
    }
}
