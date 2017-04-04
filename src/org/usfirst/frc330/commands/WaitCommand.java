package org.usfirst.frc330.commands;

import edu.wpi.first.wpilibj.command.BBTimedCommand;


/**
 * A {@link WaitCommand} will wait for a certain amount of time before finishing. It is useful if
 * you want a {@link CommandGroup} to pause for a moment.
 *
 * @see CommandGroup
 */
public class WaitCommand extends BBTimedCommand {

	  /**
	   * Instantiates a {@link WaitCommand} with the given timeout.
	   *
	   * @param timeout the time the command takes to run
	   */
	  public WaitCommand(double timeout) {
	    this("Wait(" + timeout + ")", timeout);
	  }

	  /**
	   * Instantiates a {@link WaitCommand} with the given timeout.
	   *
	   * @param name    the name of the command
	   * @param timeout the time the command takes to run
	   */
	  public WaitCommand(String name, double timeout) {
	    super(name, timeout);
	  }
}
