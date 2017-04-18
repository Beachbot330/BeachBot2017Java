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
    	
    	addParallel(new DriveCamVisionOn());
    	addParallel(new GearGrab());
    	addSequential(new ShiftLow()); //change to ShiftHigh
    	//addSequential(new WaitCommand(2));
    	//double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
    	addSequential(new DriveWaypoint(0, 90, 3, 4, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow )); //drive before turn
    	addSequential(new WaitCommand(0.3));
    	addSequential(new ShiftLow());
    	//double x, double y, double tolerance, double timeout, PIDGains gains
    	addSequential(new TurnGyroWaypoint(24, 107, 2, 1, ChassisConst.GyroTurnLow)); //turn to airship
    	//addSequential(new WaitCommand(5));
    	addSequential(new DriveWaypoint(24, 107, 9, 3, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow )); //drive into airship (Too far)
    	addSequential(new WaitCommand(0.3));
    	addSequential(new GearDropOff());
    	addSequential(new WaitCommand(0.3));
    	//DriveDistance(double distance, double tolerance, PIDGains gains)
    	addParallel(new IgniteSun());
    	//addSequential(new DriveDistance(-36, 3, ChassisConst.DriveLow)); //away from airship
    	addSequential(new DriveWaypointBackward(-5, 85, 3, 4, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow ));
    	addSequential(new ShiftLow());
    	addSequential(new PrepareToShoot(ShooterConst.RIGHT_AUTO));
    	//double x, double y, double tolerance, double timeout, PIDGains gains
    	
    	
    	addSequential(new TurnGyroWaypoint(-50, 24, 3, 3, new PIDGains(0.020,0,0.050,0,0.6,1,"GyroTurnLowSpecial") )); //turn to boiler 180
    	
    	//addSequential(new WaitCommand(0.3));
    	addSequential(new TurnCamera("target", 3.0, 15, 1.5, true, ChassisConst.CAMERA_LOW));
    	addParallel(new ShootWithWingsAgitate( ));
    	addSequential(new WaitCommand(1.0));

    	addSequential(new TurnGyroWaypoint(-5, 200, 3, 3, ChassisConst.GyroTurnLow )); 
    	addSequential(new ShiftHigh());
    	addSequential(new WingsClosed());
    	addSequential(new DriveWaypoint(-5, 200, 3, 4, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh ));
    	
    }	
    }
