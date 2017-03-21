package org.usfirst.frc330.commands.autocommands;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.*;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.ShooterConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RightBoiler40Ball extends BBCommandGroup {

    public RightBoiler40Ball() {
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
    	addSequential(new WaitCommand(2));
    	//double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
    	addSequential(new DriveWaypointBackward(0, -85, 3, 4, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh ));
    	addSequential(new WingsOpen());
    	//addSequential(new WaitCommand(5));
    	addParallel(new PrepareToShoot(ShooterConst.RB_KPA));
    	//TurnGyroRel(double angle, double tolerance, double timeout, boolean stopAtEnd, PIDGains gains)
    	addSequential(new TurnGyroRel(-15, 5, 3, true, ChassisConst.GyroTurnLow));
    	addSequential(new DriveDistance(9, 3, ChassisConst.DriveLow));
    	addParallel(new IgniteSun());
    	addSequential(new TurnCamera("target", 3.0, 15, 6, true, ChassisConst.CAMERA_LOW));
    	
    	
    	
    }
}
