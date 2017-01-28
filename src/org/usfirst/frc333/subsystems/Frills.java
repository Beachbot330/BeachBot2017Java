// RobotBuilder Version: 2.0BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc333.subsystems;

import org.usfirst.frc330.constants.ChassisConst.Devices;
import org.usfirst.frc330.util.CSVLoggable;
import org.usfirst.frc330.util.CSVLogger;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc333.RobotMap;
import org.usfirst.frc333.commands.*;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Frills extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final DigitalOutput buzzer = RobotMap.frillsBuzzer;
    private final DigitalInput practiceRobot = RobotMap.frillspracticeRobot;
    private final PowerDistributionPanel powerDistPanel = RobotMap.frillsPowerDistPanel;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public void pdp_execute() {
    	CSVLoggable temp = new CSVLoggable() {
			public double get() { return getTotalCurrent(); }
    	};
    	CSVLogger.getInstance().add("PDPTotalCurrent", temp);

    	// Drivetrain - Left 1
    	temp = new CSVLoggable() {
			public double get() { return getChCurrent(Devices.DRIVETRAIN_LEFT1); }
    	};
    	CSVLogger.getInstance().add("Drivetrain - Left 1", temp);
    	
    	// Drivetrain - Left 2
    	temp = new CSVLoggable() {
			public double get() { return getChCurrent(Devices.DRIVETRAIN_LEFT2); }
    	};
    	CSVLogger.getInstance().add("Drivetrain - Left 2", temp);

    	// Drivetrain - Left 3
    	temp = new CSVLoggable() {
			public double get() { return getChCurrent(Devices.DRIVETRAIN_LEFT3); }
    	};
    	CSVLogger.getInstance().add("Drivetrain - Left 3", temp);
    	
    	// Drivetrain - Right 1
    	temp = new CSVLoggable() {
			public double get() { return getChCurrent(Devices.DRIVETRAIN_RIGHT1); }
    	};
    	CSVLogger.getInstance().add("Drivetrain - Right 1", temp);   	
    	
    	// Drivetrain - Right 2
    	temp = new CSVLoggable() {
			public double get() { return getChCurrent(Devices.DRIVETRAIN_RIGHT2); }
    	};
    	CSVLogger.getInstance().add("Drivetrain - Right 2", temp);
    	
    	// Drivetrain - Right 3
    	temp = new CSVLoggable() {
			public double get() { return getChCurrent(Devices.DRIVETRAIN_RIGHT3); }
    	};
    	CSVLogger.getInstance().add("Drivetrain - Right 3", temp);
    	
    	// Arm - Left
    	temp = new CSVLoggable() {
			public double get() { return getChCurrent(Devices.ARM_LEFT); }
    	};
    	CSVLogger.getInstance().add("Arm - Left", temp);
    	
    	// Arm - Right
    	temp = new CSVLoggable() {
			public double get() { return getChCurrent(Devices.ARM_RIGHT); }
    	};
    	CSVLogger.getInstance().add("Arm - Right", temp);
    	
    	// Pickup - Left
    	temp = new CSVLoggable() {
			public double get() { return getChCurrent(Devices.PICKUP_LEFT); }
    	};
    	CSVLogger.getInstance().add("Pickup - Left", temp);

    	// Pickup - Right
    	temp = new CSVLoggable() {
			public double get() { return getChCurrent(Devices.PICKUP_RIGHT); }
    	};
    	CSVLogger.getInstance().add("Pickup - Right", temp);
    	
    	// Turret
    	temp = new CSVLoggable() {
			public double get() { return getChCurrent(Devices.TURRET); }
    	};
    	CSVLogger.getInstance().add("Turret", temp);
    	
    }
    
    public void buzzerOn() {
    	buzzer.set(true);
    }
    
    public void buzzerOff() {
    	buzzer.set(false);
    }
    
    public boolean isPracticeRobot() {
    	return !practiceRobot.get();
    }
    
    /* Total current for all channels (in amps) */
    public double getTotalCurrent() {
    	return powerDistPanel.getTotalCurrent();
    }
     
    public double getChCurrent(Devices d) {
    	return powerDistPanel.getCurrent(d.getValue());
    }
    
    public double getTemperature() {
    	return powerDistPanel.getTemperature();
    }
    
    public double getInputVoltage() {
    	return powerDistPanel.getVoltage();
    }

    /* Total power for all channels (in watts) */
    public double getTotalPower() {
    	return powerDistPanel.getTotalPower();
    }
    
}

