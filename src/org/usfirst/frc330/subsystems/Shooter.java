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

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.RobotMap;
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.constants.ShooterConst;
import org.usfirst.frc330.util.CSVLoggable;
import org.usfirst.frc330.util.CSVLogger;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.TalonPIDSettings;
import org.usfirst.frc330.util.Logger.Severity;
import org.usfirst.frc330.wpilibj.BBDualServo;
import org.usfirst.frc330.wpilibj.BBServo;
import org.usfirst.frc330.wpilibj.BBSolenoid;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Shooter extends Subsystem {
	
	private boolean m_climbing = false;
	
	private final boolean DISABLE_SHOOTER = Robot.isPracticeRobot();


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final BBSolenoid climberBrake = RobotMap.shooterClimberBrake;
    private final WPI_TalonSRX shooter = RobotMap.shooterShooter;
    private final WPI_TalonSRX shooter2 = RobotMap.shooterShooter2;
    private final WPI_TalonSRX gate = RobotMap.shooterGate;
    private final WPI_TalonSRX gate2 = RobotMap.shooterGate2;
    private final BBServo hood1 = RobotMap.shooterHood1;
    private final BBServo hood2 = RobotMap.shooterHood2;
    private final BBDualServo hood = RobotMap.shooterHood;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    private TalonPIDSettings shooterSettings = ShooterConst.DEFAULT;
    private TalonPIDSettings gateSettings = ShooterConst.GATE;
    
    public Shooter() {
    	CSVLoggable temp;
    	
    	if (!DISABLE_SHOOTER) {
			shooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
			shooter.setNeutralMode(NeutralMode.Coast);
	        shooter.setSensorPhase(true);
			shooter.setInverted(true);
	        shooter.configPeakOutputForward(1.0, 10);
	        shooter.configPeakOutputReverse(0.25, 10);
	        //shooter.changeControlMode(TalonControlMode.Speed);
	        shooter.config_kP(0, shooterSettings.getP(), 10);
	        shooter.config_kI(0, shooterSettings.getI(), 10);
	        shooter.config_kD(0, shooterSettings.getD(), 10);
	        shooter.config_kF(0, shooterSettings.getF(), 10);
	        shooter.configClosedloopRamp(shooterSettings.getRampRate(), 10);
	        
	    	shooter2.set(ControlMode.Follower, shooter.getDeviceID(), 0);
	    	shooter2.setInverted(true);
	    	shooter.setNeutralMode(NeutralMode.Coast); 
    	
	    	temp = new CSVLoggable(true) {
				public double get() { return shooter.getClosedLoopTarget(0); }
	    	};
	    	CSVLogger.getInstance().add("ShooterTargetRPM", temp);
	    	
	    	temp = new CSVLoggable(true) {
				public double get() { return shooter.getSelectedSensorVelocity(0); }
	    	};
	    	CSVLogger.getInstance().add("ShooterRPM", temp);
	    	
	        temp = new CSVLoggable(false) {
				public double get() { return shooter.getClosedLoopError(0); }
	    	};
	    	CSVLogger.getInstance().add("ShooterError", temp);
	    	
	    	temp = new CSVLoggable(false) {
				public double get() { return shooter.getMotorOutputVoltage(); }
	    	};
	    	CSVLogger.getInstance().add("ShooterVoltage", temp);
	    	
	    	temp = new CSVLoggable(false) {
				public double get() { return shooter.getOutputCurrent(); }
	    	};
	    	CSVLogger.getInstance().add("ShooterCurrent", temp);
	    	
	    	temp = new CSVLoggable(true) {
				public double get() { return isShooterAtSpeed() ? 1.0 : 0.0; }
	    	};
	    	CSVLogger.getInstance().add("ShooterRPMOk", temp);
    	}
    	
    	temp = new CSVLoggable(true) {
    		public double get() {return SmartDashboard.getNumber("rectWidth", 0.0);}
    	};
    	CSVLogger.getInstance().add("RectWidth", temp);
        
		gate.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		gate.setNeutralMode(NeutralMode.Coast);
        gate.setSensorPhase(false);
		gate.setInverted(false);
        gate.configPeakOutputForward(1.0, 10);
        gate.configPeakOutputReverse(1.0, 10);
        gate.config_kP(0, gateSettings.getP(), 10);
        gate.config_kI(0, gateSettings.getI(), 10);
        gate.config_kD(0, gateSettings.getD(), 10);
        gate.config_kF(0, gateSettings.getF(), 10);
        gate.configClosedloopRamp(gateSettings.getRampRate(), 10);
        
        
    	gate2.set(ControlMode.Follower, gate.getDeviceID(), 0);
    	gate2.setInverted(true);
    	gate2.setNeutralMode(NeutralMode.Coast); 
    	
    	temp = new CSVLoggable(false) {
			public double get() { return gate.getClosedLoopTarget(0); }
    	};
    	CSVLogger.getInstance().add("GateTargetRPM", temp);
    	
    	temp = new CSVLoggable(false) {
			public double get() { return gate.getSelectedSensorVelocity(0); }
    	};
    	CSVLogger.getInstance().add("GateRPM", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return gate.getMotorOutputVoltage(); }
    	};
    	CSVLogger.getInstance().add("GateVoltage", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return gate.getOutputCurrent(); }
    	};
    	CSVLogger.getInstance().add("GateCurrent", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return getHoodAngle(); }
    	};
    	CSVLogger.getInstance().add("HoodAngle", temp);
        
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
    	if (!DISABLE_SHOOTER) {
	    	//shooter.disableControl();
	    	Logger.getInstance().println("Changing Shooter Settings to: " + settings, Severity.INFO);
	    	this.shooterSettings = settings;
	        shooter.config_kP(0, shooterSettings.getP(), 10);
	        shooter.config_kI(0, shooterSettings.getI(), 10);
	        shooter.config_kD(0, shooterSettings.getD(), 10);
	        shooter.config_kF(0, shooterSettings.getF(), 10);
	        shooter.configClosedloopRamp(shooterSettings.getRampRate(), 10);
	    	Robot.shooter.setHoodAngle(settings.getHoodLocation());
    	}
    }
    
    public void enableShooter() {
    	if(!getClimbing() && !DISABLE_SHOOTER){
	    	shooter.set(ControlMode.Velocity, shooterSettings.getTargetRPM());
	    	//shooter. enable();
	    	Logger.getInstance().println("Shooter Enabled. " + shooterSettings.getTargetRPM() + " RPM");
    	}
    }
    
    public void disableShooter() {
    	if (!DISABLE_SHOOTER) {
	    	shooter.disable();
	    	Logger.getInstance().println("Shooter Disabled");
    	}
    }
    
    public void stopShooter() {
    	if (!DISABLE_SHOOTER) {
	    	shooter.set(ControlMode.PercentOutput, 0);
	    	Logger.getInstance().println("Shooter stopped");
    	}
    }
    
    public boolean isShooterAtSpeed() {
    	if (!DISABLE_SHOOTER)
    		return (Math.abs(shooter.getSelectedSensorVelocity(0) - shooterSettings.getTargetRPM()) < shooterSettings.getTolerance());
    	else
    		return false;
    }
    
	public void climberLock() {
		climberBrake.set(false);
		Logger.getInstance().println("Climber Locked");
	}
	
	public void climberUnlock() {
		climberBrake.set(true);	
		Logger.getInstance().println("Climber Unlocked");
	}
	
	public void setHoodAngle(double angle){
		if(angle>1.0){
			hood1.set(1.0);
			hood2.set(0.0);
			Logger.getInstance().println("Hood angle set too low (>1.0), using 1.0 instead", Logger.Severity.WARNING);
		}
		else if (angle < ShooterConst.MIN_HOOD_ANGLE){
			hood1.set(ShooterConst.MIN_HOOD_ANGLE);
			hood2.set(1-ShooterConst.MIN_HOOD_ANGLE);
			Logger.getInstance().println("Hood angle set too high (<min), using " + ShooterConst.MIN_HOOD_ANGLE + " instead", Logger.Severity.WARNING);
		}
		else
			hood1.set(angle); // angle from 0.0 to 1.0
			hood2.set(1-angle);
			Logger.getInstance().println("Hood angle set to " + angle);
	}
	
	public double getHoodAngle(){
		return hood1.get();
	}


	public void servoCal() {
		hood1.set(1.0);
		hood2.set(0.0);
	}


	//Gate Methods
	public void setGateSettings(TalonPIDSettings settings) {
		if(!getClimbing() && !DISABLE_SHOOTER){
	    	Logger.getInstance().println("Changing Gate Settings to: " + settings, Severity.INFO);
	    	this.gateSettings = settings;
	        gate.config_kP(0, settings.getP(), 10);
	        gate.config_kI(0, settings.getI(), 10);
	        gate.config_kD(0, settings.getD(), 10);
	        gate.config_kF(0, settings.getF(), 10);
	        gate.configClosedloopRamp(gateSettings.getRampRate(), 10);
		}
    }
    
    public void enableGate() {
    	if(!getClimbing() && !DISABLE_SHOOTER){
	    	gate.set(ControlMode.Velocity, gateSettings.getTargetRPM());
	    	Logger.getInstance().println("Gate set to " + gateSettings.getTargetRPM() + " RPM");
    	}
    }
    
    public void enableGateReverse() {
    	if(!getClimbing()){
	    	gate.set(ControlMode.PercentOutput, ShooterConst.GATE_REVERSE_PERCENT);
	    	Logger.getInstance().println("Gate set to reverse");
    	}
    }
    
    public void disableGate() {
    	gate.disable();
    	Logger.getInstance().println("Gate Disabled");
    }
    
    public void stopGate() {
    	gate.set(ControlMode.PercentOutput,0);
    	Logger.getInstance().println("Gate Stopped");
    }
    
    public void setClimbing(boolean climbing) {
		m_climbing = climbing;
	}
	
	public boolean getClimbing(){
		return m_climbing;
	}
	
	public void setGateSettingsClimb(TalonPIDSettings settings) {
    	Logger.getInstance().println("Changing Gate Settings to: " + settings, Severity.INFO);
    	this.gateSettings = settings;
        gate.config_kP(0, gateSettings.getP(), 10);
        gate.config_kI(0, gateSettings.getI(), 10);
        gate.config_kD(0, gateSettings.getD(), 10);
        gate.config_kF(0, gateSettings.getF(), 10);
        gate.configClosedloopRamp(gateSettings.getRampRate(), 10);
    }
	
	public void enableGateClimbMAX() {
    	gate.set(ControlMode.PercentOutput, 1.0);
    }
	
	public void enableGateClimb() {
    	gate.set(ControlMode.Velocity, gateSettings.getTargetRPM());
    }
	
	public double getGateCurrent(){
		return gate.getOutputCurrent();
	}
	
	public double getShooterSetpoint(){
		if (!DISABLE_SHOOTER)
			return shooter.getClosedLoopTarget(0);
		else
			return 0.0;
	}
	
	public void setShooterSetpoint(double setpoint){
		if (!DISABLE_SHOOTER)
		shooter.set(setpoint);
	}
	
}

