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
public class LeftBoilerGearShoot extends BBCommandGroup {

    public LeftBoilerGearShoot() {
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
    	addParallel(new ShiftLow()); //change to ShiftHigh
    	//addSequential(new WaitCommand(2));
    	//double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
    	addSequential(new DriveWaypoint(0, 90, 3, 4, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh )); //drive before turn
    	//addSequential(new WaitCommand(5));
    	addParallel(new ShiftLow());
    	//double x, double y, double tolerance, double timeout, PIDGains gains
    	addSequential(new TurnGyroWaypoint(24, 110, 2, 1, ChassisConst.GyroTurnLow)); //turn to airship
    	//addSequential(new WaitCommand(5));
    	addSequential(new DriveWaypoint(24, 110, 3, 4, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh )); //drive into airship
    	addSequential(new WaitCommand(0.3));
    	addSequential(new GearDropOff());
    	addSequential(new WaitCommand(0.3));
    	//DriveDistance(double distance, double tolerance, PIDGains gains)
    	addParallel(new IgniteSun());
    	addSequential(new DriveDistance(-36, 3, ChassisConst.DriveHigh)); //away from airship
    	addParallel(new ShiftLow());
    	addSequential(new PrepareToShoot(ShooterConst.RIGHT_AUTO));
    	//double x, double y, double tolerance, double timeout, PIDGains gains
    	addSequential(new TurnGyroWaypoint(-84, 18, 3, 3, ChassisConst.GyroTurnLow )); //turn to boiler 180
    	addSequential(new WaitCommand(0.3));
    	addSequential(new TurnCamera("target", 3.0, 15, 3, true, ChassisConst.CAMERA_LOW));
    	addSequential(new ShootWithWingsAgitate( ));
    	addParallel(new ShiftHigh());
    	
    	
    }
}
