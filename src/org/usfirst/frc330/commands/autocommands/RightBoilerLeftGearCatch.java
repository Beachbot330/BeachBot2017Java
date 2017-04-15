package org.usfirst.frc330.commands.autocommands;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.*;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.ShooterConst;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class RightBoilerLeftGearCatch extends BBCommandGroup {

    public RightBoilerLeftGearCatch() {
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
    	addParallel(new ShiftLow());
 
    	//double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
    	addSequential(new DriveWaypoint(0, 77, 3, 4, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow )); //drive before turn
    	addSequential(new WaitCommand(0.3));
    	
    	//double x, double y, double tolerance, double timeout, PIDGains gains
    	addSequential(new TurnGyroWaypoint(39, 106, 3, 1, ChassisConst.GyroTurnLow)); //turn to airship
    	
    	
    	addSequential(new DriveWaypoint(39, 106, 6, 3, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow )); //drive into airship (Too far)
    	addSequential(new GearDropOff());
    	addSequential(new WaitCommand(0.3));
    	
    	//DRIVE AWAY FROM AIRSHIP, TURN ON LED, TURN AND DRIVE TOWARDS BOILER
    	addSequential(new IgniteSun());
    	addParallel(new PrepareToShoot(ShooterConst.RIGHT_AUTO)); //starts rollers and shooter
    	addSequential(new DriveWaypointBackward(0, 77, 3, 4, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow )); //away from airship
    	addSequential(new TurnGyroWaypoint(180, 30, 3, 3, ChassisConst.GyroTurnLow )); //150 test
    	
    	
    	//GET CLOSER AND PREPARE TO SHOOT
    	
     	
    	
    	//DriveDistance(double distance, double tolerance, PIDGains gains)
    	//addSequential(new DriveDistance(48, 3, ChassisConst.DriveLow));
    	
    	addSequential(new ShiftHigh()); 

    	addSequential(new DriveWaypoint(180, 30, 6, 3, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh ));
    	//AIM AND SHOOT, TURN OFF SHOOTER
    	addSequential(new ShiftLow()); 
    	
    	addSequential(new TurnCamera("target", 3.0, 15, 1.5, true, ChassisConst.CAMERA_LOW)); //aim at boiler
    	addParallel(new TurnCamera("target", 3.0, 15, 3, true, ChassisConst.CAMERA_LOW)); //aim at boiler

    	addParallel(new ShootWithWingsAgitate()); // shoot
    	addSequential(new WaitCommand(1.0));
    	//CODE TO STOP SHOOTER NEEDED??
    	addSequential(new ShooterStop());
    
    	
    	//TURN AND DRIVE TO NEAR HOPPER, TURN AGAIN AND DRIVE TO HOPPER
    	
    	//double x, double y, double tolerance, double timeout, PIDGains gains
    	//addSequential(new TurnGyroWaypoint(30, 175, 3, 3, ChassisConst.GyroTurnLow )); //turn to near hopper
    	addSequential(new WingsClosed()); 
    	
    	addSequential(new TurnGyroWaypoint(180, 300, 3, 3, ChassisConst.GyroTurnLow )); //turn to hopper 
    	addSequential(new ShiftHigh()); 
    	addSequential(new DriveWaypoint(180, 300, 3, 3, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh ));
    	
    	
   

   	

    	
    	
    }
}
