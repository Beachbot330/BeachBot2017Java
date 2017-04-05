package org.usfirst.frc330.commands.autocommands;

import org.usfirst.frc330.commands.DriveCamVisionOn;
import org.usfirst.frc330.commands.GearGrab;
import org.usfirst.frc330.commands.IgniteSun;
import org.usfirst.frc330.commands.ShiftLow;
import org.usfirst.frc330.commands.WingsOpen;
import org.usfirst.frc330.commands.commandgroups.PrepareToShoot;
import org.usfirst.frc330.commands.commandgroups.ShootWithWingsAgitate;
import org.usfirst.frc330.commands.commandgroups.ShootWithWingsAgitateAuto;
import org.usfirst.frc330.commands.drivecommands.DriveWaypoint;
import org.usfirst.frc330.commands.drivecommands.DriveWaypointBackward;
import org.usfirst.frc330.commands.drivecommands.TurnCamera;
import org.usfirst.frc330.commands.drivecommands.TurnGyroRel;
import org.usfirst.frc330.commands.drivecommands.TurnGyroWaypoint;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.ShooterConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RightBoiler40Ballv2 extends BBCommandGroup {

        PIDGains SecretSauce3   = new PIDGains(0.004,0.0005,0.000,0,0.5,1,"SecretSauce3");
	
    public RightBoiler40Ballv2() {
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
    	
 
    	
    	// Drive to Hopper to get balls (STRAIGHT, TURN, STRAIGHT)

        //double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
    	//addSequential(new DriveWaypoint(0, 60, 3, 1.5, true, ChassisConst.DriveLow, ChassiConst.GyroTurnLow));
	//addSequential(new TurnGyroWaypoint(48, 60, 3, 1.0, ChassisConst.GyroTurnLow )); // turn to hopper
    	//addSequential(new DriveWaypoint(48, 60, 3, 1.0, true, ChassisConst.DriveLow, ChassiConst.GyroTurnLow)); //drive into hopper

    	// Drive to Hopper to get balls (ARC TURN)
    	addSequential(new WingsOpen());
        //double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
    	addSequential(new DriveWaypoint(0, 48, 3, 3.0, false, ChassisConst.DriveLow, ChassisConst.GyroTurnLow)); //drive into hopper
    	addSequential(new DriveWaypoint(86, 84, 3, 3.0, true, ChassisConst.DriveLow, SecretSauce3)); //drive into hopper


    	// Back up and turn to boiler while starting up shooter

    	addSequential(new IgniteSun());
    	addSequential(new PrepareToShoot(ShooterConst.RB_KPA)); //start agitator and shooter wheels 
    	addSequential(new WaitCommand(1.5)); //wait for balls to load
        addSequential(new DriveWaypointBackward(36, 84, 5, 3.5, true, ChassisConst.DriveLow, ChassisConst.GyroTurnLow)); //back away from hopper
        addSequential(new TurnGyroWaypoint(48, 20, 3, 1.0, ChassisConst.GyroTurnLow )); // turn to boiler

    	//TurnCamera(String cameraName, double tolerance, int toleranceStableCount, double timeout, boolean stopAtEnd, PIDGains gains)
    	addSequential(new TurnCamera("target", 2.0, 15, 0.5, true, ChassisConst.CAMERA_LOW)); //aim at boiler
    	addParallel(new TurnCamera("target", 3.0, 15, 3, true, ChassisConst.CAMERA_LOW)); //aim at boiler
    	addSequential(new ShootWithWingsAgitateAuto( )); // shoot

    	
    }
}