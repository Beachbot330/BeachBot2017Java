package org.usfirst.frc330.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.BBCommand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WaitUntilMatchTime extends BBCommand {
	double matchTime;

	/**
	 * Waits until a specific time in the match. In autonomous, the match time starts 
	 * at 15 and counts down to 0. In teleop, it starts at 135 and counts down to 0.
	 * This only works when connected to FMS or in practice mode on DS.
	 * @param matchTime
	 */
    public WaitUntilMatchTime(double matchTime) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		this.matchTime = matchTime;
    }

    double currentMatchTime;
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	currentMatchTime = DriverStation.getInstance().getMatchTime();
    	if (currentMatchTime <= 0)
    		return false;
        return currentMatchTime <= matchTime;
    }
}
