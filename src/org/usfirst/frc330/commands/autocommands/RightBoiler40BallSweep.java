package org.usfirst.frc330.commands.autocommands;

import org.usfirst.frc330.commands.DriveCamVisionOn;
import org.usfirst.frc330.commands.GearGrab;
import org.usfirst.frc330.commands.IgniteSun;
import org.usfirst.frc330.commands.ShiftLow;
import org.usfirst.frc330.commands.WingsOpen;
import org.usfirst.frc330.commands.commandgroups.PrepareToShoot;
import org.usfirst.frc330.commands.commandgroups.ShootWithWingsAgitate;
import org.usfirst.frc330.commands.commandgroups.ShootWithWingsAgitateAuto;
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
    	
    	addSequential(new DriveCamVisionOn());
    	addSequential(new GearGrab());
    	addSequential(new ShiftLow()); 
    	
    	// Sweeping Drive Command
    	addSequential(new DriveWaypointBackward(0, -15, 10, 4.0, true, ChassisConst.DriveLow, SecretSauce1));
    	addSequential(new DriveWaypointBackward(47, -80, 5, 3.5, true, ChassisConst.DriveLow, SecretSauce2)); // was -74 coming to long beach, 54.5, -80 before hail mary
    	
    	addSequential(new PrepareToShoot(ShooterConst.RB_KPA)); //start agitator and shooter wheels 

    	addSequential(new WingsOpen()); //catch ALL the balls
    	
    	//Bump the Hopper
    	//addSequential(new TurnGyroRel(+20, 3.0, 0.25, true, ChassisConst.GyroTurnLow));
    	//addSequential(new TurnGyroRel(-5, 2.0, 0.25, true, ChassisConst.GyroTurnLow)); 
    	
    	// Bump the hopper hard
    	addSequential(new TurnGyroRel(+25, 3.0, 0.25, true, ChassisConst.GyroTurnLow)); 
    	addSequential(new TurnGyroRel(-10, 2.0, 0.25, true, ChassisConst.GyroTurnLow)); 
    	
    	//Aim
    	addSequential(new IgniteSun());
    	addSequential(new TurnCamera("target", 1.5, 15, 1.5, true, ChassisConst.CAMERA_LOW)); //aim at boiler
    	addParallel(new TurnCamera("target", 3.0, 15, 3, true, ChassisConst.CAMERA_LOW)); //aim at boiler
    	
    	//Shoot
    	addSequential(new ShootWithWingsAgitateAuto( ));
    }
}
