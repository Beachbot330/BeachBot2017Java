package org.usfirst.frc330.commands.autocommands;

import java.util.ArrayList;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.drivecommands.DrivePath;
import org.usfirst.frc330.commands.drivecommands.DriveWaypointBackward;
import org.usfirst.frc330.commands.drivecommands.TurnCamera;
import org.usfirst.frc330.commands.drivecommands.TurnGyroWaypointBackward;
import org.usfirst.frc330.commands.drivecommands.Waypoint;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class TestAuto extends BBCommandGroup {
	PIDGains SecretSauce1   = new PIDGains(0.001,0.001,0.000,0,0.5,1,"SecretSauce1");
	PIDGains SecretSauce2   = new PIDGains(0.003,0.0005,0.000,0,0.5,1,"SecretSauce2");

    public TestAuto() {
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
    	
    	//double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
		addSequential(new ShiftLow());
    	addSequential(new DriveWaypointBackward(0, -15, 10, 4.0, true, ChassisConst.DriveLow, SecretSauce1)); //Angle -48
    	//addSequential(new WaitCommand(1.0));
    	//addSequential(new TurnGyroWaypointBackward(39, -80, 5, 3.0, ChassisConst.GyroTurnLow));
    	addSequential(new DriveWaypointBackward(54, -67, 5, 3.5, true, ChassisConst.DriveLow, SecretSauce2)); //Angle -48
    }
}



/*public class TestAuto extends BBCommandGroup {
	PIDGains SecretSauce1   = new PIDGains(0.001,0.001,0.000,0,0.5,1,"SecretSauce2");
	PIDGains SecretSauce2   = new PIDGains(0.010,0,0.000,0,0.5,1,"SecretSauce2");

    public TestAuto() {
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
    	
    	//double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
		addSequential(new ShiftLow());
    	addSequential(new DriveWaypointBackward(24, -47, 5, 3.0, true, ChassisConst.DriveLow, SecretSauce1)); //Angle -48
    	addSequential(new TurnGyroWaypointBackward(39, -80, 5, 3.0, ChassisConst.GyroTurnLow));
    	addSequential(new DriveWaypointBackward(40, -70, 5, 3.0, true, ChassisConst.DriveLow, SecretSauce2)); //Angle -48
    }
}
*/