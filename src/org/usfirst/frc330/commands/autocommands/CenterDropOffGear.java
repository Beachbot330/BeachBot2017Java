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
public class CenterDropOffGear extends BBCommandGroup {

    public CenterDropOffGear() {
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
    	
    	addParallel(new GearGrab());
    	addSequential(new WaitCommand(2));
    	addParallel(new ShiftLow());
    	//double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
    	addSequential(new DriveWaypoint(0, 63, 3, 4, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh ));
    	addSequential(new WaitCommand(2));
    	addSequential(new GearDropOff());
    	addSequential(new WaitCommand(2));
    	addSequential(new DriveWaypointBackward(0, 44, 3, 1, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	addSequential(new WaitCommand(2));
    	addParallel(new ShiftLow( ));
    	//double x, double y, double tolerance, double timeout, PIDGains gains
    	addSequential(new TurnGyroWaypoint(156, 30, 3, 1.5, ChassisConst.GyroTurnLow ));
    	addSequential(new WaitCommand(2));
    	//addSequential(new ( ));		aim robot for high shot maybe later
    	//addParallel(new ( ));			create wing move 1hz maybe later
    	addSequential(new PrepareToShoot(ShooterConst.CENTER_AUTO));
    	addSequential(new WaitCommand(1));
    	addSequential(new ShootWithWingsAgitate( ));
    	
    	
    	
    }
}
