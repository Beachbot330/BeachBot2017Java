package org.usfirst.frc330.commands.autocommands;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.drivecommands.TurnCamera;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class TestAuto extends BBCommandGroup {

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
    	
    	addSequential(new IgniteSun());
    	addSequential(new ShiftLow());
    	addSequential(new WaitCommand(1.0));
    	//String cameraName, double tolerance, int toleranceStableCount, double timeout, boolean stopAtEnd, PIDGains gains
    	addSequential(new TurnCamera("target", 3.0, 15, 6, true, ChassisConst.CAMERA_LOW));
    	addSequential(new ExtinguishSun());
    	
    }
}
