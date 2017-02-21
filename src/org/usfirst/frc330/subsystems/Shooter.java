// RobotBuilder Version: 2.0BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330.subsystems;

import org.usfirst.frc330.RobotMap;
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.constants.ShooterConst;
import org.usfirst.frc330.util.CSVLoggable;
import org.usfirst.frc330.util.CSVLogger;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.TalonPIDSettings;
import org.usfirst.frc330.util.Logger.Severity;
import org.usfirst.frc330.wpilibj.BBSolenoid;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.VictorSP;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Shooter extends Subsystem {
	


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final BBSolenoid climberBrake = RobotMap.shooterClimberBrake;
    private final CANTalon shooter1 = RobotMap.shooterShooter1;
    private final CANTalon shooter2 = RobotMap.shooterShooter2;
    private final CANTalon gate1 = RobotMap.shooterGate1;
    private final CANTalon gate2 = RobotMap.shooterGate2;
    private final Servo hood1 = RobotMap.shooterHood1;
    private final Servo hood2 = RobotMap.shooterHood2;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    private TalonPIDSettings shooterSettings = ShooterConst.DEFAULT;
    private TalonPIDSettings gateSettings = ShooterConst.GATE;
    
    public Shooter() {
		shooter1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        shooter1.reverseSensor(false);
        shooter1.configNominalOutputVoltage(+0.0f, -0.0f);
        shooter1.configPeakOutputVoltage(+12.0f, -3.0f);
        shooter1.changeControlMode(TalonControlMode.Speed);
        shooter1.setPID(shooterSettings.getP(), shooterSettings.getI(), shooterSettings.getD(), shooterSettings.getF(), 0, shooterSettings.getRampRate(), 0);
        
    	shooter2.changeControlMode(TalonControlMode.Follower);
    	shooter2.set(shooter1.getDeviceID());
    	shooter2.reverseOutput(true);
    	
    	CSVLoggable temp = new CSVLoggable(false) {
			public double get() { return shooter1.getSetpoint(); }
    	};
    	CSVLogger.getInstance().add("ShooterTargetRPM", temp);
    	
    	temp = new CSVLoggable(false) {
			public double get() { return shooter1.getSpeed(); }
    	};
    	CSVLogger.getInstance().add("ShooterRPM", temp);
    	
        temp = new CSVLoggable(false) {
			public double get() { return shooter1.getClosedLoopError(); }
    	};
    	CSVLogger.getInstance().add("ShooterError", temp);
    	
    	temp = new CSVLoggable(false) {
			public double get() { return shooter1.getOutputVoltage(); }
    	};
    	CSVLogger.getInstance().add("ShooterVoltage", temp);
    	
    	temp = new CSVLoggable(false) {
			public double get() { return shooter1.getOutputCurrent(); }
    	};
    	CSVLogger.getInstance().add("ShooterCurrent", temp);
    	
    	temp = new CSVLoggable(false) {
			public double get() { return isShooterAtSpeed() ? 1.0 : 0.0; }
    	};
    	CSVLogger.getInstance().add("ShooterRPMOk", temp);

		gate1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        gate1.reverseSensor(false);
        gate1.configNominalOutputVoltage(+0.0f, -0.0f);
        gate1.configPeakOutputVoltage(+12.0f, 0.0f);
        gate1.changeControlMode(TalonControlMode.Speed);
        gate1.setPID(gateSettings.getP(), gateSettings.getI(), gateSettings.getD(), gateSettings.getF(), 0, gateSettings.getRampRate(), 0);
        
    	gate2.changeControlMode(TalonControlMode.Follower);
    	gate2.set(gate1.getDeviceID());
    	gate2.reverseOutput(true);
    	
    	temp = new CSVLoggable(false) {
			public double get() { return gate1.getSetpoint(); }
    	};
    	CSVLogger.getInstance().add("GateTargetRPM", temp);
    	
    	temp = new CSVLoggable(false) {
			public double get() { return gate1.getSpeed(); }
    	};
    	CSVLogger.getInstance().add("GateRPM", temp);
    	
        
    }


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public void setShooterSettings(TalonPIDSettings settings) {
    	shooter1.disableControl();
    	Logger.getInstance().println("Changing Shooter Settings to: " + settings, Severity.INFO);
    	this.shooterSettings = settings;
    	shooter1.setPID(settings.getP(), settings.getI(), settings.getD(), settings.getF(), 0, settings.getRampRate(), 0);
    }
    
    public void enableShooter() {
    	shooter1.enableControl();
    }
    
    public void disableShooter() {
    	shooter1.disableControl();
    }
    
    public boolean isShooterAtSpeed() {
    	return (Math.abs(shooter1.getSpeed() - shooterSettings.getTargetRPM()) < shooterSettings.getTolerance());
    }
    
    public void setGateSettings(TalonPIDSettings settings) {
    	gate1.disableControl();
    	Logger.getInstance().println("Changing Gate Settings to: " + settings, Severity.INFO);
    	this.gateSettings = settings;
    	gate1.setPID(settings.getP(), settings.getI(), settings.getD(), settings.getF(), 0, settings.getRampRate(), 0);
    }
    
    public void enableGate() {
    	gate1.setSetpoint(gateSettings.getTargetRPM());
    	gate1.enableControl();
    }
    
    public void disableGate() {
    	gate1.disableControl();
    }
    
    public void stopGate() {
    	gate1.setSetpoint(0);
    }
    
	public void climberLock() {
		climberBrake.set(false);	
	}
	
	public void climberUnlock() {
		climberBrake.set(true);	
	}
}

