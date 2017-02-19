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

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
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
    public JoystickButton shoot_L1;
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
    public JoystickButton pickUpBalls_2;
    public JoystickButton prepareToShoot_4;
    public JoystickButton pickupGear_3;
    public TwoJoystickButton climb_56;
    public JoystickButton defenseMode_1;
    public Joystick operator;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        operator = new Joystick(2);
        
        defenseMode_1 = new JoystickButton(operator, 1);
        defenseMode_1.whenPressed(new DefenseMode());
        climb_56 = new TwoJoystickButton(operator, 5, 6);
        climb_56.whileHeld(new DoNothing(), new Climb());
        pickupGear_3 = new JoystickButton(operator, 1);
        pickupGear_3.whileHeld(new PickupGear());
        prepareToShoot_4 = new JoystickButton(operator, 4);
        prepareToShoot_4.whenPressed(new PrepareToShoot());
        pickUpBalls_2 = new JoystickButton(operator, 2);
        pickUpBalls_2.whenPressed(new PickUpBalls());
        driverR = new Joystick(1);
        
        shiftHigh_2 = new JoystickButton(driverR, 2);
        shiftHigh_2.whenPressed(new ShiftHigh());
        releaseGear_R5 = new JoystickButton(driverR, 5);
        releaseGear_R5.whenPressed(new ReleaseGear());
        releaseGear_R4 = new JoystickButton(driverR, 4);
        releaseGear_R4.whenPressed(new ReleaseGear());
        shoot_R1 = new JoystickButton(driverR, 1);
        shoot_R1.whileHeld(new Shoot());
        driverL = new Joystick(0);
        
        releaseClimberLock_3 = new JoystickButton(driverL, 3);
        releaseClimberLock_3.whenPressed(new ReleaseClimberLock());
        shiftLow_2 = new JoystickButton(driverL, 2);
        shiftLow_2.whenPressed(new ShiftLow());
        releaseGear_L5 = new JoystickButton(driverL, 5);
        releaseGear_L5.whenPressed(new ReleaseGear());
        releaseGear_L4 = new JoystickButton(driverL, 4);
        releaseGear_L4.whenPressed(new ReleaseGear());
        shoot_L1 = new JoystickButton(driverL, 1);
        shoot_L1.whileHeld(new Shoot());


        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("PickupOn", new PickupOn());
        SmartDashboard.putData("PickupOff", new PickupOff());
        SmartDashboard.putData("PickupReverse", new PickupReverse());
        SmartDashboard.putData("ShiftHigh", new ShiftHigh());
        SmartDashboard.putData("ShiftLow", new ShiftLow());
        SmartDashboard.putData("IntakeIn", new IntakeIn());
        SmartDashboard.putData("IntakeOut", new IntakeOut());
        SmartDashboard.putData("IntakeExtend", new IntakeExtend());
        SmartDashboard.putData("IntakeRetract", new IntakeRetract());
        SmartDashboard.putData("WingsOpen", new WingsOpen());
        SmartDashboard.putData("WingsClosed", new WingsClosed());
        SmartDashboard.putData("PickUpBalls", new PickUpBalls());
        SmartDashboard.putData("PrepareToShoot", new PrepareToShoot());
        SmartDashboard.putData("GearPickupDown", new GearPickupDown());
        SmartDashboard.putData("GearPickupUp", new GearPickupUp());
        SmartDashboard.putData("HopperAgitate", new HopperAgitate());
        SmartDashboard.putData("HopperFeed", new HopperFeed());
        SmartDashboard.putData("ShooterStart", new ShooterStart());
        SmartDashboard.putData("ShooterFeed", new ShooterFeed());
        SmartDashboard.putData("ShooterStop", new ShooterStop());
        SmartDashboard.putData("DefenseMode", new DefenseMode());
        SmartDashboard.putData("PickupGear", new PickupGear());
        SmartDashboard.putData("ReleaseGear", new ReleaseGear());
        SmartDashboard.putData("ReleaseClimberLock", new ReleaseClimberLock());
        SmartDashboard.putData("SD_Shoot", new SD_Shoot());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
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

