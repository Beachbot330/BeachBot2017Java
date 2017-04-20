package org.usfirst.frc330.commands.autocommands;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.*;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.ShooterConst;

import edu.wpi.first.wpilibj.command.BBCommandGroup;
import org.usfirst.frc330.commands.WaitCommand;

/**
 *
 */
public class RightBoilerCenterGearShootCatch extends BBCommandGroup {

    public RightBoilerCenterGearShootCatch() {
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
    	
    	addParallel(new DriveCamVisionOn());
    	addParallel(new GearGrab());
    	//addSequential(new WaitCommand(2)); //allow human to get out of way
    	addParallel(new ShiftLow()); //change to shift high
    	//double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
    	addSequential(new DriveWaypoint(0, 74, 3, 4, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh )); //drive to airship
    	addSequential(new WaitCommand(0.3)); //reduce
    	addSequential(new GearDropOff());
    	addSequential(new WaitCommand(0.3)); // reduce
    	addSequential(new DriveWaypointBackward(0, 44, 3, 1, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh)); //away from airship
    	addSequential(new WaitCommand(0.3)); //reduce
    	addParallel(new ShiftLow()); 
    	//double x, double y, double tolerance, double timeout, PIDGains gains
    	addParallel(new IgniteSun());
    	addSequential(new TurnGyroWaypoint(156, 20, 3, 1.5, ChassisConst.GyroTurnLow )); // turn to boiler
    	addSequential(new WaitCommand(0.3)); //reduce
    	addSequential(new ShiftHigh());
    	//double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
    	addSequential(new DriveWaypoint(125, 25, 3, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh )); //drive to boiler
    	addSequential(new WaitCommand(0.5));
    	addParallel(new PrepareToShoot(ShooterConst.CENTER_AUTO)); //starts rollers and shooter
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypoint(156, 0.5, 3, 1.5, ChassisConst.GyroTurnLow )); // turn to boiler
    	addSequential(new TurnCamera("target", 3.0, 15, 6, true, ChassisConst.CAMERA_LOW)); //aim at boiler
    	addSequential(new WaitCommand(1.5)); // try to reduce
    	addSequential(new ShootWithWingsAgitate( )); // shoot
    	addParallel(new ShiftHigh());
    	
    	
    }
}
