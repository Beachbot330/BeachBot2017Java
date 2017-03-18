// RobotBuilder Version: 2.0BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.BBIterativeRobot;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.WPILibVersion;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.autocommands.*;
import org.usfirst.frc330.subsystems.*;
import org.usfirst.frc330.util.BeachbotLibVersion;
import org.usfirst.frc330.util.Buzzer;
import org.usfirst.frc330.util.CSVLogger;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends BBIterativeRobot {

    Command autonomousCommand;
    SendableChooser<Command> autoProgram;

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Frills frills;
    public static Chassis chassis;
    public static Shooter shooter;
    public static Pickup pickup;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    public static Buzzer buzzer;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        frills = new Frills();
        chassis = new Chassis();
        shooter = new Shooter();
        pickup = new Pickup();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        buzzer = new Buzzer(RobotMap.frillsBuzzer);
        
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();
        
        // Setup Cameras --------------------------------------------------------
        frills.initDriverCamera();
        frills.initShooterCamera();
        // </Cameras> -------------------------------------------------------------

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        autonomousCommand = new AutonomousCommand();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        
        CSVLogger.getInstance().writeHeader();
        
        Logger.getInstance().println("BeachbotLib Version:        " + BeachbotLibVersion.Version, Severity.INFO);
        Logger.getInstance().println("WPILib Version: "             + WPILibVersion.Version, Severity.INFO);
        Logger.getInstance().println("NavX Firmware Version:      " + RobotMap.chassisImu.getFirmwareVersion(), Severity.INFO);
        Logger.getInstance().println("CAN Talon Firmware Version: " + RobotMap.shooterShooter.GetFirmwareVersion(), Severity.INFO);
        

        if (isPracticeRobot())
        	Logger.getInstance().println("Practice Robot Detected",true);
        else
        	Logger.getInstance().println("Competition Robot Detected",true);
    
        buzzer.enable(0.75);
        frills.setGreenLEDs(false);
        frills.setAlarmLED(Frills.Alarm.OFF);
        frills.setDecorativeStyleLED(Frills.Style.TWINKLE);
        
        autoProgram = new SendableChooser<Command>();
        autoProgram.addDefault("Do Nothing", new DoNothing());
        autoProgram.addObject("Z_JoeTest", new JoeTest());
        
        SmartDashboard.putData("Auto Program", autoProgram);
        
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
		Logger.getInstance().println("Disabled Init",true);
		Robot.shooter.climberLock();
		Robot.pickup.gearPickupUp();
		Robot.pickup.gearRelease();
		Robot.pickup.wingsClosed();
		Robot.shooter.stopGate();
		Robot.shooter.stopShooter();
		Robot.pickup.hopperStop();
		Robot.pickup.pickupOff();
		Robot.chassis.stopDrive();
		Robot.shooter.setHoodAngle(0.98);
		Robot.frills.extinguishDyingSun();
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    	chassis.calcXY();
    	CSVLogger.getInstance().writeData();
    	Logger.getInstance().updateDate();
    	buzzer.update();
    }

    public void autonomousInit() {
		buzzer.enable(1.25);
    	Logger.getInstance().println("Autonomous Init",true);
    	Logger.getInstance().updateDate();
    	
    	Robot.chassis.resetPosition();
    	Robot.shooter.climberUnlock();
    	
    	autonomousCommand = autoProgram.getSelected();
        if (autonomousCommand != null) {
        	autonomousCommand.start();
        	Logger.getInstance().println("Running Auto: " + autonomousCommand.getName(),true);
        }
        
        if(Math.abs(Robot.chassis.getAngle()) > 0.2){
        	Robot.chassis.resetPosition();
        	Logger.getInstance().println("Gyro failed to reset, retrying", Severity.ERROR);
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
		chassis.calcXY();
        Scheduler.getInstance().run();
        chassis.pidDriveAuto();
    	CSVLogger.getInstance().writeData();
		buzzer.update();
    }

    public void teleopInit() {
    	Logger.getInstance().println("Teleop Init", true);
    	Logger.getInstance().updateDate();
    	Robot.frills.dimBlueLEDs();
    	buzzer.enable(1.25);
    	Robot.shooter.climberUnlock();
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        chassis.calcXY();
        Scheduler.getInstance().run();
        chassis.pidDrive();
    	CSVLogger.getInstance().writeData();
    	buzzer.update();
    }
	
    @Override
    public void testInit() {
    	buzzer.enable(1.25);
        Logger.getInstance().println("Test Init", true);
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	buzzer.update();
    	LiveWindow.run();
        chassis.calcXY();
        CSVLogger.getInstance().writeData();
    }
	
    /*****************************************************************
     * 
     * Disconnected METHODS
     * 
     ****************************************************************/
    
    /**
     * This function is responsible for initialization for disconnected mode. 
     * 
     * 1. Log entry for Test Initialization
     */
    @Override
    public void disconnectedInit() {
    	buzzer.enable(0.2);
        Logger.getInstance().println("Disconnected Init", true);
    }

    /**
     * This function is called periodically during disconnected mode
     */
    @Override
    public void disconnectedPeriodic() {
    	buzzer.update();
    	disabledPeriodic();
    }
    
    public static boolean isPracticeRobot() {
        return (frills.isPracticeRobot());
    }
}
