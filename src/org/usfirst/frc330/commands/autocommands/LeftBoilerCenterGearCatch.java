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
public class LeftBoilerCenterGearCatch extends BBCommandGroup {

    public LeftBoilerCenterGearCatch() {
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
 
    	//Drive To Airship
    	//double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
    	addSequential(new DriveWaypoint(0, 72, 3, 4, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow )); //drive before turn
    	addSequential(new WaitCommand(0.3));
    	
    	//Drop off gear
    	addSequential(new GearDropOff());
    	addSequential(new WaitCommand(0.3));
    	
    	//Spin Up
    	addSequential(new PrepareToShoot(ShooterConst.AUTO_CATCH));
    	
    	//Drive to wall
    	addSequential(new TurnGyroWaypointBackward(0, 20, 6, 3, ChassisConst.GyroTurnLow )); //
    	addSequential(new DriveWaypointBackward(0, 20, 6, 4, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow )); // drive to wall

    	//Drive along wall
    	addSequential(new TurnGyroWaypoint(-(212-91), 20, 5, 2, ChassisConst.GyroTurnLow)); //   
    	addSequential(new ShiftHigh()); 
    	addSequential(new DriveWaypoint(-(212-91), 20, 6, 3, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh ));

    	//Bump turn into boiler
    	addSequential(new IgniteSun());
    	addSequential(new ShiftLow()); 
    	addSequential(new TurnGyroAbs(-140, 5, 2,true, ChassisConst.GyroTurnLow)); 

    	// Aim
    	addSequential(new TurnCamera("target", 3.0, 15, 3, true, ChassisConst.CAMERA_LOW)); //aim at boiler
    	addSequential(new WaitCommand(0.5));
    	
    	//Shoot
    	addParallel(new ShootWithWingsAgitate()); // shoot
    	
    }
}
