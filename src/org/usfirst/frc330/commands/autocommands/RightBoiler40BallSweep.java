package org.usfirst.frc330.commands.autocommands;

import org.usfirst.frc330.commands.DriveCamVisionOn;
import org.usfirst.frc330.commands.GearGrab;
import org.usfirst.frc330.commands.IgniteSun;
import org.usfirst.frc330.commands.ShiftLow;
import org.usfirst.frc330.commands.WingsOpen;
import org.usfirst.frc330.commands.commandgroups.PrepareToShoot;
import org.usfirst.frc330.commands.commandgroups.ShootWithWingsAgitate;
import org.usfirst.frc330.commands.drivecommands.DriveWaypointBackward;
import org.usfirst.frc330.commands.drivecommands.TurnCamera;
import org.usfirst.frc330.commands.drivecommands.TurnGyroRel;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.ShooterConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RightBoiler40BallSweep extends BBCommandGroup {

	PIDGains SecretSauce1   = new PIDGains(0.001,0.001,0.000,0,0.5,1,"SecretSauce1");
	PIDGains SecretSauce2   = new PIDGains(0.003,0.0005,0.000,0,0.5,1,"SecretSauce2");
	
    public RightBoiler40BallSweep() {
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
    	addSequential(new WaitCommand(0.2));
    	
    	// Sweeping Drive Command
    	addSequential(new DriveWaypointBackward(0, -15, 10, 4.0, true, ChassisConst.DriveLow, SecretSauce1));
    	addSequential(new DriveWaypointBackward(56.5, -74, 5, 3.5, true, ChassisConst.DriveLow, SecretSauce2));
    	
    	addSequential(new WaitCommand(2));
    	
    	//TurnGyroRel(double angle, double tolerance, double timeout, boolean stopAtEnd, PIDGains gains)
    	addSequential(new TurnGyroRel(15, 3, 3, true, ChassisConst.GyroTurnLow)); //bump ball hopper
    	addSequential(new WingsOpen()); //catch ALL the balls
    	addSequential(new IgniteSun());
    	addParallel(new PrepareToShoot(ShooterConst.RB_KPA)); //start agitator and shooter wheels 
    	addSequential(new WaitCommand(1)); //try to reduce
    	//TurnCamera(String cameraName, double tolerance, int toleranceStableCount, double timeout, boolean stopAtEnd, PIDGains gains)
    	addSequential(new TurnCamera("target", 3.0, 15, 6, true, ChassisConst.CAMERA_LOW)); //aim at boiler
    	addSequential(new ShootWithWingsAgitate( )); // shoot
    	// end in high gear
    	
    }
}