// RobotBuilder Version: 2.0BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330.commands.commandgroups;

import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.constants.PickupConst;
import org.usfirst.frc330.constants.ShooterConst;
import org.usfirst.frc330.subsystems.*;
import org.usfirst.frc330.util.TalonPIDSettings;

/**
 *
 */
public class PrepareToShoot extends BBCommandGroup {

    public PrepareToShoot(TalonPIDSettings shooterSettings) {

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
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS
 
    	//Reverse the gate to prevent volunteers
    	addSequential(new GateReverse());
    	
    	//Start the flywheel
    	addSequential(new ShooterStart(shooterSettings));
    	
    	//Feed balls to the gate
    	addSequential(new GearPickupUp());
    	
    	//Delay and then use pickup to feed
    	addSequential(new WaitCommand(0.5));
    	//addSequential(new PickupReverse());
    	//addSequential(new PickupOn(PickupConst.PICKUP_WHILE_SHOOTING));
    	
    	addSequential(new HopperSet(PickupConst.HOPPER_FEED_SPEED, PickupConst.HOPPER_FEED_TIMEOUT));
    	
    } 
    
    public PrepareToShoot(){
    	this(ShooterConst.DEFAULT);
    }
}
