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

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.*;
import org.usfirst.frc330.commands.drivecommands.TeleopAutoAim;
import org.usfirst.frc330.constants.ShooterConst;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.buttons.TwoJoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton releaseGear_L4;
    public JoystickButton releaseGear_L5;
    public JoystickButton shiftLow_2;
    public JoystickButton releaseClimberLock_3;
    public Joystick driverL;
    public JoystickButton shoot_R1;
    public JoystickButton releaseGear_R4;
    public JoystickButton releaseGear_R5;
    public JoystickButton shiftHigh_2;
    public Joystick driverR;
    public JoystickButton prepareToShoot_4;
    public JoystickButton pickupGear_3Pressed;
    public JoystickButton defenseMode_1;
    public JoystickButton climb_56;
    public JoystickButton wingsOpen_7;
    public JoystickButton wingsClosed_8;
    public JoystickButton prepareToShootDistance_12;
    public JoystickButton teleopAutoAim_11;
    public Joystick operator;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    public JoystickButton prepareToShootClose_2;
    public POVButton increaseSpeed;
    public POVButton decreaseSpeed;
    public POVButton increaseHood;
    public POVButton decreaseHood;

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        operator = new Joystick(2);
        
        teleopAutoAim_11 = new JoystickButton(operator, 11);
        teleopAutoAim_11.whenPressed(new TeleopAutoAim());
        prepareToShootDistance_12 = new JoystickButton(operator, 12);
        prepareToShootDistance_12.whenPressed(new PrepareToShootDistance());
        wingsClosed_8 = new JoystickButton(operator, 8);
        wingsClosed_8.whenPressed(new WingsClosed());
        wingsOpen_7 = new JoystickButton(operator, 7);
        wingsOpen_7.whenPressed(new WingsOpen());
        climb_56 = new JoystickButton(operator, 5);
        climb_56.whileHeld(new Climb());
        defenseMode_1 = new JoystickButton(operator, 1);
        defenseMode_1.whenPressed(new DefenseMode());
        pickupGear_3Pressed = new JoystickButton(operator, 3);
        pickupGear_3Pressed.whenPressed(new PickupGear());
        prepareToShoot_4 = new JoystickButton(operator, 4);
        prepareToShoot_4.whenPressed(new PrepareToShoot());
        driverR = new Joystick(1);
        
        shiftHigh_2 = new JoystickButton(driverR, 2);
        shiftHigh_2.whenPressed(new ShiftHigh());
        releaseGear_R5 = new JoystickButton(driverR, 5);
        releaseGear_R5.whenPressed(new GearDropOffTeleop());
        releaseGear_R4 = new JoystickButton(driverR, 4);
        releaseGear_R4.whenPressed(new GearDropOffTeleop());
        shoot_R1 = new JoystickButton(driverR, 1);
        shoot_R1.whileHeld(new ShootWithWingsAgitate());
        driverL = new Joystick(0);
        
        releaseClimberLock_3 = new JoystickButton(driverL, 3);
        releaseClimberLock_3.whileHeld(new DriverClimberUnlock());
        shiftLow_2 = new JoystickButton(driverL, 2);
        shiftLow_2.whenPressed(new ShiftLow());
        releaseGear_L5 = new JoystickButton(driverL, 5);
        releaseGear_L5.whenPressed(new GearDropOffTeleop());
        releaseGear_L4 = new JoystickButton(driverL, 4);
        releaseGear_L4.whenPressed(new GearDropOffTeleop());


        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("PickupOn", new PickupOn());
        SmartDashboard.putData("PickupOff", new PickupOff());
        SmartDashboard.putData("PickupReverse", new PickupReverse());
        SmartDashboard.putData("ShiftHigh", new ShiftHigh());
        SmartDashboard.putData("ShiftLow", new ShiftLow());
        SmartDashboard.putData("WingsOpen", new WingsOpen());
        SmartDashboard.putData("WingsClosed", new WingsClosed());
        SmartDashboard.putData("PickUpBalls", new PickUpBalls());
        SmartDashboard.putData("PrepareToShoot", new PrepareToShoot());
        SmartDashboard.putData("GearPickupDown", new GearPickupDown());
        SmartDashboard.putData("GearPickupUp", new GearPickupUp());
        SmartDashboard.putData("HopperAgitate", new HopperAgitate());
        SmartDashboard.putData("HopperFeed", new HopperFeed());
        SmartDashboard.putData("ShooterStart", new ShooterStart());
        SmartDashboard.putData("GateFeed", new GateFeed());
        SmartDashboard.putData("ShooterStop", new ShooterStop());
        SmartDashboard.putData("DefenseMode", new DefenseMode());
        SmartDashboard.putData("PickupGear", new PickupGear());
        SmartDashboard.putData("GearRelease", new GearRelease());
        SmartDashboard.putData("SD_Shoot", new SD_Shoot());
        SmartDashboard.putData("GateStop", new GateStop());
        SmartDashboard.putData("GearGrab", new GearGrab());
        SmartDashboard.putData("ClimberLock", new ClimberLock());
        SmartDashboard.putData("ClimberUnlock", new ClimberUnlock());
        SmartDashboard.putData("GateOn", new GateOn());
        SmartDashboard.putData("WatchForGear", new WatchForGear());
        SmartDashboard.putData("HopperStop", new HopperStop());
        SmartDashboard.putData("GearDropOff", new GearDropOff());
        SmartDashboard.putData("DriveCamVisionOn", new DriveCamVisionOn());
        SmartDashboard.putData("DriveCamVisionOff", new DriveCamVisionOff());
        SmartDashboard.putData("GateReverse", new GateReverse());
        SmartDashboard.putData("ShooterStartNear", new ShooterStartNear());
        SmartDashboard.putData("ShooterStartFar", new ShooterStartFar());
        SmartDashboard.putData("ServoCal", new ServoCal());
        SmartDashboard.putData("DriverClimberUnlock", new DriverClimberUnlock());
        SmartDashboard.putData("ConditionalGearRelease", new ConditionalGearRelease());
        SmartDashboard.putData("ShootWithWingsAgitate", new ShootWithWingsAgitate());
        SmartDashboard.putData("IgniteSun", new IgniteSun());
        SmartDashboard.putData("ExtinguishSun", new ExtinguishSun());
        SmartDashboard.putData("AllensTestCode", new AllensTestCode());
        SmartDashboard.putData("IncreaseSpeed", new IncreaseSpeed());
        SmartDashboard.putData("IncreaseHood", new IncreaseHood());
        SmartDashboard.putData("DecreaseSpeed", new DecreaseSpeed());
        SmartDashboard.putData("DecreaseHood", new DecreaseHood());
        SmartDashboard.putData("PrepareToShootDistance", new PrepareToShootDistance());
        SmartDashboard.putData("TeleopAutoAim", new TeleopAutoAim());
        SmartDashboard.putData("GearDropOffTeleop", new GearDropOffTeleop());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        prepareToShootClose_2 = new JoystickButton(operator, 2);
        prepareToShootClose_2.whenPressed(new PrepareToShoot(ShooterConst.EXTRA_CLOSE));
        
        increaseSpeed = new POVButton(operator, 0, 90);  //right
        decreaseSpeed = new POVButton(operator, 0, 270);  // left
        increaseHood = new POVButton(operator, 0, 0);  //Up
        decreaseHood = new POVButton(operator, 0, 180); //Down
        
        increaseSpeed.whenPressed(new IncreaseSpeed());
        decreaseSpeed.whenPressed(new DecreaseSpeed());
        increaseHood.whenPressed(new IncreaseHood());
        decreaseHood.whenPressed(new DecreaseHood());
        
        
    	
    	SmartDashboard.putData("Shooter Start Default", new ShooterStart());
    	SmartDashboard.putData("Shooter Start Close", new ShooterStart(ShooterConst.CLOSE));
    	SmartDashboard.putData("Shooter Start Far", new ShooterStart(ShooterConst.FAR));
    	SmartDashboard.putData("Shooter Start Recirculate", new ShooterStart(ShooterConst.RECIRCULATE));
    	SmartDashboard.putData("Reset Gyro", new ResetGyro());
    	SmartDashboard.putData("Jiffy Pop Spin UP", new PrepareToShoot(ShooterConst.JIFFY_POP));
    	SmartDashboard.putData("Allen Test Code", new ShootWithWingsAuto());
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriverL() {
        return driverL;
    }

    public Joystick getDriverR() {
        return driverR;
    }

    public Joystick getOperator() {
        return operator;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

