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

import org.usfirst.frc330.constants.ChassisConst.Devices;
import org.usfirst.frc330.util.CSVLoggable;
import org.usfirst.frc330.util.CSVLogger;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;
import org.usfirst.frc330.RobotMap;
import org.usfirst.frc330.commands.*;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Frills extends Subsystem {
	
	// Variables for LED control
	boolean aimingLEDs = false;
	Alarm alarmLEDs = Alarm.OFF;
	public enum Alarm {SLOW, FAST, SOLID, OFF};
	Style decorative = Style.TWINKLE;
	public enum Style {SWAP, TWINKLE, OTHER1, OTHER2};
	
	private boolean m_climbing = false;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final DigitalOutput buzzer = RobotMap.frillsBuzzer;
    private final DigitalInput practiceRobot = RobotMap.frillspracticeRobot;
    private final PowerDistributionPanel powerDistPanel = RobotMap.frillsPowerDistPanel;
    private final DigitalOutput greenLEDs = RobotMap.frillsgreenLEDs;
    private final AnalogOutput lEDSelection = RobotMap.frillsLEDSelection;
    private final AnalogOutput dyingBlueSuns = RobotMap.frillsDyingBlueSuns;
    private final DigitalOutput gearLight = RobotMap.frillsGearLight;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    private UsbCamera driverCam;
    private UsbCamera shooterCam;
    
    public Frills() {
    	CSVLoggable temp = new CSVLoggable(true) {
			public double get() { return DriverStation.getInstance().getMatchTime(); }
    	};
    	CSVLogger.getInstance().add("MatchTime", temp);
    	temp = new CSVLoggable(false) {
			public double get() { return SmartDashboard.getNumber("targetAngle", 0); }
    	};
    	CSVLogger.getInstance().add("BoilerAngle", temp);

    }

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
    
    
    // LED Functions ---------------------------------------------
    public void setGreenLEDs(boolean set){
    	aimingLEDs = set;
    	updateLEDs();
    }
    
    public void setAlarmLED(Alarm AllenCouldntThinkOfVariableName){
    	alarmLEDs = AllenCouldntThinkOfVariableName;
    	updateLEDs();
    }
    
    public void setDecorativeStyleLED (Style AllenCouldntThinkOfVariableName){
    	decorative = AllenCouldntThinkOfVariableName;
    	updateLEDs();
    }
    
    private void updateLEDs(){
		greenLEDs.set(!aimingLEDs);
    	if(alarmLEDs != Alarm.OFF){
    		if(alarmLEDs == Alarm.SLOW)
    			lEDSelection.setVoltage(0.5);
    		else if(alarmLEDs == Alarm.FAST)
				lEDSelection.setVoltage(2.0);
    		else if(alarmLEDs == Alarm.SOLID)
				lEDSelection.setVoltage(2.5);
    	}
    	else{
    		if (decorative == Style.SWAP)
    			lEDSelection.setVoltage(0.5);
    		else if (decorative == Style.TWINKLE)
    			lEDSelection.setVoltage(3.0);
    		else if (decorative == Style.OTHER1)
    			lEDSelection.setVoltage(3.5);
    		else if (decorative == Style.OTHER2)
    			lEDSelection.setVoltage(4.0);
    	}
    }
    
    public void igniteDyingSun(){
    	dyingBlueSuns.setVoltage(2.5);
    }
    
    public void extinguishDyingSun(){
    	dyingBlueSuns.setVoltage(0.0);
    }
    
    public void dimBlueLEDs() {
		dyingBlueSuns.setVoltage(0.5);
	}
    
    //Camera Functions ----------------------------------------------------
    public void initDriverCamera(){
    	try {
    		driverCam = CameraServer.getInstance().startAutomaticCapture("Driver", "/dev/v4l/by-path/platform-ci_hdrc.0-usb-0:1.2.2:1.0-video-index0");
    		//if (!driverCam.setResolution(160, 120))
    		if (!driverCam.setResolution(160, 120))
    			Logger.getInstance().println("Resolution failed to set", Severity.ERROR);
    		if (!driverCam.setFPS(30))
    			Logger.getInstance().println("FPS failed to set", Severity.ERROR);
    		Logger.getInstance().println("DriverCam Get Video Mode: " + driverCam.getVideoMode().width +
    				", " + driverCam.getVideoMode().height + ", " + driverCam.getVideoMode().fps, Severity.DEBUG);
    		driverCam.setExposureManual(50);
    	}
    	catch (Exception ex) {
    		Logger.getInstance().println("Exception initializing Driver Camera", Severity.ERROR);
    		Logger.getInstance().printStackTrace(ex);
    		setAlarmLED(Alarm.FAST);
    	}
    }
    
    public void initShooterCamera(){
    	try {
    		shooterCam = CameraServer.getInstance().startAutomaticCapture("Vision", "/dev/v4l/by-path/platform-ci_hdrc.0-usb-0:1.2.3:1.0-video-index0");
    		if (!shooterCam.setResolution(640, 480))
    			Logger.getInstance().println("Resolution failed to set", Severity.ERROR);
    		if (!shooterCam.setFPS(7))
    			Logger.getInstance().println("FPS failed to set", Severity.ERROR);
    		Logger.getInstance().println("VisionCam Get Video Mode: " + shooterCam.getVideoMode().width +
    				", " + shooterCam.getVideoMode().height + ", " + shooterCam.getVideoMode().fps, Severity.DEBUG);
    		shooterCam.setExposureManual(1);
    	}
    	catch (Exception ex) {
    		Logger.getInstance().println("Exception initializing Shooter Camera", Severity.ERROR);
    		Logger.getInstance().printStackTrace(ex);
    		setAlarmLED(Alarm.FAST);
    	}
    }
    
    public void driverCameraBright(){
    	driverCam.setExposureAuto();
    }
    
    public void driverCameraDark(){
    	driverCam.setExposureManual(1);
    }
    
    public void setClimbing(boolean climbing) {
		m_climbing = climbing;
	}
	
	public boolean getClimbing(){
		return m_climbing;
	}
	
	public void gearLightOn(){
		gearLight.set(true);
	}
	
	public void gearLightOff(){
		gearLight.set(false);
	}
	
	public Alarm getAlarmLEDs() {
		return alarmLEDs;
	}

	
}

