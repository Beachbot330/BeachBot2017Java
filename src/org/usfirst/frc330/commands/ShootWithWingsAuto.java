// RobotBuilder Version: 2.0BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.BBCommand;
import org.usfirst.frc330.Robot;
import org.usfirst.frc330.constants.ShooterConst;

/**
 *
 */
public class ShootWithWingsAuto extends BBCommand {

	double timer, interval, duration;
	boolean wingsOpened = true;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public ShootWithWingsAuto() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.shooter);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.climberUnlock();
    	Robot.shooter.setGateSettings(ShooterConst.GATE_SHOOTING);
    	Robot.shooter.enableGate();
    	Robot.pickup.wingsOpen();
    	wingsOpened = true;	
    	timer = Timer.getFPGATimestamp()+ 3.2;
    	interval = 0.8;
    	duration = 0.04;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if( ((Timer.getFPGATimestamp() - timer) > interval) && wingsOpened){
    		Robot.pickup.wingsClosed();
    		wingsOpened = false;
    		timer = Timer.getFPGATimestamp();
    	}
    	else if ( ((Timer.getFPGATimestamp() - timer) > duration) && !wingsOpened){
    		Robot.pickup.wingsOpen();
    		wingsOpened = true;
    		timer = Timer.getFPGATimestamp();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.climberUnlock();
    	Robot.shooter.enableGateReverse();
    	Robot.pickup.wingsOpen();
    	wingsOpened = true;	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
