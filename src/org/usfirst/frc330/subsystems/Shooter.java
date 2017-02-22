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
    private final CANTalon shooter = RobotMap.shooterShooter;
    private final CANTalon gate = RobotMap.shooterGate;
    private final Servo hood1 = RobotMap.shooterHood1;
    private final Servo hood2 = RobotMap.shooterHood2;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    private TalonPIDSettings shooterSettings = ShooterConst.DEFAULT;
    private TalonPIDSettings gateSettings = ShooterConst.GATE;
    
    public Shooter() {
		shooter.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        shooter.reverseSensor(false);
        shooter.configNominalOutputVoltage(+0.0f, -0.0f);
        shooter.configPeakOutputVoltage(+12.0f, -3.0f);
        shooter.changeControlMode(TalonControlMode.Speed);
        shooter.setPID(shooterSettings.getP(), shooterSettings.getI(), shooterSettings.getD(), shooterSettings.getF(), 0, shooterSettings.getRampRate(), 0);
        
    	//shooter2.changeControlMode(TalonControlMode.Follower);
    	//shooter2.set(shooter1.getDeviceID());
    	//shooter2.reverseOutput(true);
    	
    	CSVLoggable temp = new CSVLoggable(false) {
			public double get() { return shooter.getSetpoint(); }
    	};
    	CSVLogger.getInstance().add("ShooterTargetRPM", temp);
    	
    	temp = new CSVLoggable(false) {
			public double get() { return shooter.getSpeed(); }
    	};
    	CSVLogger.getInstance().add("ShooterRPM", temp);
    	
        temp = new CSVLoggable(false) {
			public double get() { return shooter.getClosedLoopError(); }
    	};
    	CSVLogger.getInstance().add("ShooterError", temp);
    	
    	temp = new CSVLoggable(false) {
			public double get() { return shooter.getOutputVoltage(); }
    	};
    	CSVLogger.getInstance().add("ShooterVoltage", temp);
    	
    	temp = new CSVLoggable(false) {
			public double get() { return shooter.getOutputCurrent(); }
    	};
    	CSVLogger.getInstance().add("ShooterCurrent", temp);
    	
    	temp = new CSVLoggable(false) {
			public double get() { return isShooterAtSpeed() ? 1.0 : 0.0; }
    	};
    	CSVLogger.getInstance().add("ShooterRPMOk", temp);

		gate.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        gate.reverseSensor(false);
        gate.configNominalOutputVoltage(+0.0f, -0.0f);
        gate.configPeakOutputVoltage(+12.0f, 0.0f);
        gate.changeControlMode(TalonControlMode.Speed);
        gate.setPID(gateSettings.getP(), gateSettings.getI(), gateSettings.getD(), gateSettings.getF(), 0, gateSettings.getRampRate(), 0);
        
    	//gate2.changeControlMode(TalonControlMode.Follower);
    	//gate2.set(gate1.getDeviceID());
    	//gate2.reverseOutput(true);
    	
    	temp = new CSVLoggable(false) {
			public double get() { return gate.getSetpoint(); }
    	};
    	CSVLogger.getInstance().add("GateTargetRPM", temp);
    	
    	temp = new CSVLoggable(false) {
			public double get() { return gate.getSpeed(); }
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
    	shooter.disableControl();
    	Logger.getInstance().println("Changing Shooter Settings to: " + settings, Severity.INFO);
    	this.shooterSettings = settings;
    	shooter.setPID(settings.getP(), settings.getI(), settings.getD(), settings.getF(), 0, settings.getRampRate(), 0);
    }
    
    public void enableShooter() {
    	shooter.enableControl();
    }
    
    public void disableShooter() {
    	shooter.disableControl();
    }
    
    public boolean isShooterAtSpeed() {
    	return (Math.abs(shooter.getSpeed() - shooterSettings.getTargetRPM()) < shooterSettings.getTolerance());
    }
    
    public void setGateSettings(TalonPIDSettings settings) {
    	gate.disableControl();
    	Logger.getInstance().println("Changing Gate Settings to: " + settings, Severity.INFO);
    	this.gateSettings = settings;
    	gate.setPID(settings.getP(), settings.getI(), settings.getD(), settings.getF(), 0, settings.getRampRate(), 0);
    }
    
    public void enableGate() {
    	gate.setSetpoint(gateSettings.getTargetRPM());
    	gate.enableControl();
    }
    
    public void disableGate() {
    	gate.disableControl();
    }
    
    public void stopGate() {
    	gate.setSetpoint(0);
    }
    
	public void climberLock() {
		climberBrake.set(false);	
	}
	
	public void climberUnlock() {
		climberBrake.set(true);	
	}
}

