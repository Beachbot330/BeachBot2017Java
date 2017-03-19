package org.usfirst.frc330.commands.autocommands;

import org.usfirst.frc330.commands.GearGrab;
import org.usfirst.frc330.commands.ShiftHigh;
import org.usfirst.frc330.commands.ShiftLow;
import org.usfirst.frc330.commands.commandgroups.GearDropOff;
import org.usfirst.frc330.commands.commandgroups.PrepareToShoot;
import org.usfirst.frc330.commands.commandgroups.ShootWithWingsAgitate;
import org.usfirst.frc330.commands.drivecommands.DriveDistance;
import org.usfirst.frc330.commands.drivecommands.DriveWaypoint;
import org.usfirst.frc330.commands.drivecommands.TurnGyroWaypoint;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.ShooterConst;

import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class NotRightBoilerGearShoot extends BBCommandGroup {

    public NotRightBoilerGearShoot() {
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
    	addParallel(new ShiftLow()); //change to ShitHigh
    	addSequential(new WaitCommand(2));
    	//double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
    	addSequential(new DriveWaypoint(0, 108, 3, 4, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh )); //drive forward
    	addSequential(new WaitCommand(2));
    	addSequential(new TurnGyroWaypoint(24, 132, 3, 1.5, ChassisConst.GyroTurnLow )); //turn to airship
    	addSequential(new WaitCommand(2));
    	addSequential(new DriveWaypoint(24, 132, 3, 4, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh )); //drive to airship
    	addSequential(new WaitCommand(2));
    	addSequential(new GearDropOff());
    	addSequential(new WaitCommand(2));
    	addSequential(new DriveDistance(-36, 3, ChassisConst.DriveHigh)); //away from airship
    	addParallel(new ShiftLow());
    	addSequential(new TurnGyroWaypoint(36, 36, 3, 1.5, ChassisConst.GyroTurnLow )); //turn to center of driver wall
    	addParallel(new PrepareToShoot(ShooterConst.CENTER_AUTO));
    	addSequential(new DriveWaypoint(36, 36, 3, 4, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh )); //drive to center of driver wall
    	addSequential(new WaitCommand(2));
    	//aim to boiler
    	addSequential(new ShootWithWingsAgitate( ));
    	addParallel(new ShiftHigh());
    	
    }
}
