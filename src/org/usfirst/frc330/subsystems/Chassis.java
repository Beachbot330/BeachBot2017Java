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

import java.util.ArrayList;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.RobotMap;
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.drivecommands.Waypoint;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.util.CSVLoggable;
import org.usfirst.frc330.util.CSVLogger;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;
import org.usfirst.frc330.wpilibj.BBDoubleSolenoid;
import org.usfirst.frc330.wpilibj.DummyPIDOutput;
import org.usfirst.frc330.wpilibj.MultiPIDController;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Chassis extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController leftDrive1 = RobotMap.chassisLeftDrive1;
    private final SpeedController leftDrive2 = RobotMap.chassisLeftDrive2;
    private final SpeedController leftDrive3 = RobotMap.chassisLeftDrive3;
    private final SpeedController rightDrive1 = RobotMap.chassisRightDrive1;
    private final SpeedController rightDrive2 = RobotMap.chassisRightDrive2;
    private final SpeedController rightDrive3 = RobotMap.chassisRightDrive3;
    private final Compressor compressor = RobotMap.chassisCompressor;
    private final Encoder driveTrainEncoderL = RobotMap.chassisDriveTrainEncoderL;
    private final Encoder driveTrainEncoderR = RobotMap.chassisDriveTrainEncoderR;
    private final BBDoubleSolenoid shifter = RobotMap.chassisShifter;
    private final AnalogInput pressureSensor = RobotMap.chassisPressureSensor;
    private final AHRS imu = RobotMap.chassisImu;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    double  gyro_prevVal = 0.0;
    int     ctrRollOver  = 0;
    boolean fFirstUse    = true;
    double  left, right;
    private boolean m_climbing = false;
    
    public MultiPIDController gyroPID, leftDrivePID, rightDrivePID;
    private DummyPIDOutput gyroOutput, leftDriveOutput, rightDriveOutput;
    
    public Chassis()
    {
    	super();
        
        PIDSource gyroSource = new PIDSource()
        {

			@Override
			public double pidGet()
			{
				return getAngle();
			}

			@Override
			public void setPIDSourceType(PIDSourceType pidSource)
			{
			}

			@Override
			public PIDSourceType getPIDSourceType()
			{
				return PIDSourceType.kDisplacement;
			}
        	
        };
        gyroOutput = new DummyPIDOutput();
        leftDriveOutput = new DummyPIDOutput();
        rightDriveOutput = new DummyPIDOutput();
        
        gyroPID = new MultiPIDController(ChassisConst.GyroTurnLow, gyroSource,gyroOutput,"Gyro");
        leftDrivePID = new MultiPIDController(ChassisConst.DriveLow, driveTrainEncoderL,leftDriveOutput,"LeftDrive");
        rightDrivePID = new MultiPIDController(ChassisConst.DriveLow, driveTrainEncoderR,rightDriveOutput, "RightDrive");
        
        gyroPID.setToleranceBuffer(ChassisConst.gyroTolerancebuffer);
        
        SmartDashboard.putData("gyroPID", gyroPID);
        SmartDashboard.putData("leftDrivePID", leftDrivePID);
        SmartDashboard.putData("rightDrivePID", rightDrivePID);
        
        double pulsePerRevolution;
        if (Robot.isPracticeRobot())
        	pulsePerRevolution = ChassisConst.practicePulsePerRevolution;
        else
        	pulsePerRevolution = ChassisConst.pulsePerRevolution;
        
        final double distanceperpulse = Math.PI*ChassisConst.wheelDiameter/pulsePerRevolution /
        		ChassisConst.encoderGearRatio/ChassisConst.gearRatio * ChassisConst.Fudgefactor;

        driveTrainEncoderR.setReverseDirection(true);
        driveTrainEncoderL.setDistancePerPulse(distanceperpulse);
        driveTrainEncoderR.setDistancePerPulse(distanceperpulse);
     
        	
        // LOGGING!
        CSVLoggable temp = new CSVLoggable(true) {
			public double get() { return driveTrainEncoderL.getDistance(); }
    	};
    	CSVLogger.getInstance().add("DriveTrainDistanceL", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return driveTrainEncoderR.getDistance(); }
    	};
    	CSVLogger.getInstance().add("DriveTrainDistanceR", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return driveTrainEncoderL.getRate(); }  		
    	};
    	CSVLogger.getInstance().add("DriveTrainRateL", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return driveTrainEncoderR.getRate(); }  		
    	};
    	CSVLogger.getInstance().add("DriveTrainRateR", temp);    	

    	temp = new CSVLoggable() {
			public double get() { return leftDrive1.get(); }  		
    	};
    	CSVLogger.getInstance().add("DriveTrainLeft1", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return rightDrive1.get(); }  		
    	};
    	CSVLogger.getInstance().add("DriveTrainRight1", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return leftDrive2.get(); }  		
    	};
    	CSVLogger.getInstance().add("DriveTrainLeft2", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return rightDrive2.get(); }  		
    	};
    	CSVLogger.getInstance().add("DriveTrainRight2", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return leftDrive3.get(); }  		
    	};
    	CSVLogger.getInstance().add("DriveTrainLeft3", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return rightDrive3.get(); }  		
    	};
    	CSVLogger.getInstance().add("DriveTrainRight3", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return getAngle(); }  		
    	};    	
    	CSVLogger.getInstance().add("ChassisAngle", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return getGyroComp(); }  		
    	};    	
    	CSVLogger.getInstance().add("GyroCompensation", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return imu.isConnected() ? 1: 0; }  		
    	};    	
    	CSVLogger.getInstance().add("GyroIsConnected", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return imu.isCalibrating() ? 1: 0; }  		
    	};    	
    	CSVLogger.getInstance().add("GyroIsCalibrating", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return getX(); }  		
    	};     	
    	CSVLogger.getInstance().add("ChassisX", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return getY(); }  		
    	};      	
    	CSVLogger.getInstance().add("ChassisY", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return getPressure(); }  		
    	};  
    	CSVLogger.getInstance().add("Pressure", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { 
				DoubleSolenoid.Value state = shifter.get();
				double state_int;
				if (state == DoubleSolenoid.Value.kForward)
					state_int = 1.0;
				else
					state_int = 0.0;
				return state_int;}  		
    	};  
    	CSVLogger.getInstance().add("Shifter", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return imu.getPitch(); }  		
    	};  
    	CSVLogger.getInstance().add("ChassisPitch", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return imu.getRoll(); }  		
    	};  
    	CSVLogger.getInstance().add("ChassisRoll", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { 
				if (path == null) 
					return -1;
				return getNextWaypointNumber(); }  		
    	};  
    	CSVLogger.getInstance().add("NextWaypointNumber", temp);
    	
    }

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new TankDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    }
    
    private double x = 0, 
	           	   y = 0;
    private double prevLeftEncoderDistance  = 0, 
	           	   prevRightEncoderDistance = 0;

	public void calcXY()
	{
		 double distance, 
		 	    leftEncoderDistance, 
		 	    rightEncoderDistance, 
		 	    gyroAngle;
		 
		 leftEncoderDistance  = driveTrainEncoderL.getDistance();
		 rightEncoderDistance = driveTrainEncoderR.getDistance();
		 gyroAngle = getAngle();
		 distance =  ((leftEncoderDistance - prevLeftEncoderDistance) + (rightEncoderDistance - prevRightEncoderDistance))/2;
		 x = x + distance * Math.sin(Math.toRadians(gyroAngle));
		 y = y + distance * Math.cos(Math.toRadians(gyroAngle));
		 prevLeftEncoderDistance  = leftEncoderDistance;
		 prevRightEncoderDistance = rightEncoderDistance;
	}
	
	
	public void resetPosition()
    {
    	driveTrainEncoderL.reset();
    	driveTrainEncoderR.reset();
        imu.zeroYaw();
        fFirstUse = true;
        ctrRollOver = 0;
        setXY(0,0);
        this.prevLeftEncoderDistance = 0;
        this.prevRightEncoderDistance = 0;
    } /* End resetPosition */
    
    public void setXY(double x, double y)
    {
        this.x = x;
        this.y = y;
    } /* End setXY */
    
    public void setXYoffset(double xOffset, double yOffset) {
    	this.x += xOffset;
    	this.y += yOffset;
    }
    
    // Function Name: getAngle()
    // Purpose: Return angle relative to 0 instead of -/+ 180 degrees
    public double getAngle()
    {

    	// First case
    	// Old reading: +150 degrees
    	// New reading: +170 degrees
    	// Difference:  (170 - 150) = +20 degrees
    	
    	// Second case
    	// Old reading: -20 degrees
    	// New reading: -50 degrees
    	// Difference : (-50 - -20) = -30 degrees 
    	
    	// Third case
    	// Old reading: +179 degrees
    	// New reading: -179 degrees
    	// Difference:  (-179 - 179) = -358 degrees
    	
    	// Fourth case
    	// Old reading: -179  degrees
    	// New reading: +179 degrees
    	// Difference:  (+150 - -60) = +358 degrees
    	
    	// Declare variables
    	double difference = 0.0;
    	double gyroVal    = 0.0;
    	
    	// Retrieve current yaw value from gyro
    	double yawVal     = imu.getYaw();
        
    	// Has gyro_prevVal been previously set?
    	// If not, return do not calculate, return current value
    	if( !fFirstUse )
    	{
    		// Determine count for rollover counter
    		difference = yawVal - gyro_prevVal;

	    	// Clockwise past +180 degrees
    		// If difference > 180*, increment rollover counter
	    	if( difference < -180.0 ) {
	    		ctrRollOver++;
	   		
	    	// Counter-clockwise past -180 degrees\
	    	// If difference > 180*, decrement rollover counter
	    	}
	    	else if ( difference > 180.0 ) {
	    		ctrRollOver--;
	    	} 
    	}
    	
    	// Mark gyro_prevVal as being used
    	fFirstUse = false;
    		
    	// Calculate value to return back to calling function
    	// e.g. +720 degrees or -360 degrees
    	gyroVal = yawVal + (360.0 * ctrRollOver);
    	gyro_prevVal = yawVal;
    	
    	return gyroVal + gyroComp;
    } /* End getAngle() */
    
    double gyroComp = 0;
    
    public void setGyroComp(double compensation) {
    	gyroComp = compensation;
    }
    
    public double getGyroComp() {
    	return gyroComp;
    }
    
    public void tankDrive(Joystick leftJoystick, Joystick rightJoystick)
    {
       left = -leftJoystick.getY();
       right = -rightJoystick.getY();
    }
    
    public void tankDrive(double left, double right)
    {
        this.left = left;
        this.right = right;
    }
    
    public void stopDrive()
    {
        if (gyroPID.isEnabled())
            gyroPID.reset();
        if (leftDrivePID.isEnabled())
            leftDrivePID.reset();
        if (rightDrivePID.isEnabled())
            rightDrivePID.reset();        
       
        tankDrive(0, 0);  
    }
    
    private void drive(double left, double right)
    {
        leftDrive1.set(-left);
        leftDrive2.set(-left);
        leftDrive3.set(-left);
        
        rightDrive1.set(right);
        rightDrive2.set(right);
        rightDrive3.set(right);
    }
    
    public void pidDrive()
    {
        double left, right;
        if (DriverStation.getInstance().isDisabled())
        {
            stopDrive();
        }
        else
        {
            left = this.left+leftDriveOutput.getOutput() + gyroOutput.getOutput();
            right = this.right+rightDriveOutput.getOutput() - gyroOutput.getOutput();
            drive(left, right);
            this.left = 0;
            this.right = 0;
        }
    }
    
    public void pidDriveAuto()
    {
        double left, right, gyroValue;
        if (DriverStation.getInstance().isDisabled())
        {
            stopDrive();
        }
        else
        {
        	//gyroValue = Math.signum(gyroOutput.getOutput()) * Math.min(Math.abs(gyroOutput.getOutput()) , 0.5);
        	gyroValue = Math.signum(gyroOutput.getOutput()) * Math.min(Math.abs(gyroOutput.getOutput()) , 1.0);
        	left = this.left+leftDriveOutput.getOutput() + gyroValue;
            right = this.right+rightDriveOutput.getOutput() - gyroValue;
            drive(left, right);
            this.left = 0;
            this.right = 0;
        }
    } /* End pidDriveAuto() */
    
    
    public void shiftHigh()
    {
    	shifter.set(DoubleSolenoid.Value.kForward);
    }
    
    public void shiftLow()
    {
    	shifter.set(DoubleSolenoid.Value.kReverse);
    }
    
    public boolean isHighGear()
    {
    	return (shifter.get() == DoubleSolenoid.Value.kForward);
    }
    
    
    /////////////////////////////////////////////////////////////
    // GET methods
    /////////////////////////////////////////////////////////////   
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public double getLeftDistance() 
    {
    	return driveTrainEncoderL.getDistance();
    }
    
    public double getRightDistance() {
    	return driveTrainEncoderR.getDistance();
    }
    
    public double getPressure()
    {
    	if (Robot.isPracticeRobot())  //TODO may not be necessary to account for different pressure sensors
    		return (50*pressureSensor.getAverageVoltage() -25);
    	return 37.5*(pressureSensor.getAverageVoltage()- 0.5);
    }
    
    //Path methods
	ArrayList<Waypoint> path;
	int currentWaypoint = 0;
	
	public void setPath(ArrayList<Waypoint> path) {
		if ((path == null)) {
			Logger.getInstance().println("Null path in setPath", Severity.ERROR);
			Logger.getInstance().printStackTrace(new NullPointerException());
		}
		this.path = path;
		currentWaypoint = 0;
	}

	public int getNextWaypointNumber() {
		return currentWaypoint;
	}
	
	public Waypoint getNextWaypoint() {
		return path.get(getNextWaypointNumber());
	}
	
	public void incrementWaypoint() {
		if (currentWaypoint + 1 < path.size()) {
			currentWaypoint++;
		}
		else {
			Logger.getInstance().println("Attempt to increment waypoint past path", Severity.ERROR);
			Logger.getInstance().printStackTrace(new IndexOutOfBoundsException());
		}
	}
	
	public double getDistanceToEnd() {
		return getDistanceToWaypoint(path.get(path.size()-1));
	}
	
	public double getAngleToWaypoint(Waypoint waypt) {
		double deltaX = waypt.getX() - getX();
        double deltaY = waypt.getY() - getY();
        
		return Math.toDegrees(Math.atan2(deltaX, deltaY));
	}

	public double getAngleToNextWaypoint() {
		return getAngleToWaypoint(getNextWaypoint());
	}
	
	public double getDistanceBetweenWaypoints(Waypoint cur, Waypoint to) {
		double deltaX = to.getX() - cur.getX();
        double deltaY = to.getY() - cur.getY();
        
        return Math.sqrt(deltaX*deltaX+deltaY*deltaY);
	}
	
	public double getDistanceToWaypoint(Waypoint waypt) {
		Waypoint currentLocation = new Waypoint(Robot.chassis.getX(), Robot.chassis.getY(), Robot.chassis.getAngle());
        return getDistanceBetweenWaypoints(currentLocation, waypt);
	}
	
	public double getDistanceToNextWaypoint() {
		return getDistanceToWaypoint(getNextWaypoint());
	}
	
	public int getCurrentWaypointNumber() {
		return currentWaypoint;
	}
	
	public Waypoint getCurrentWaypoint() {
		return path.get(currentWaypoint);
	}
	
	public int getPreviousWaypointNumber() {
		if (currentWaypoint - 1 >= 0) {
			return currentWaypoint - 1;
		}
		else {
			Logger.getInstance().println("Attempt to get negative previous waypoint", Severity.ERROR);
			return currentWaypoint;
		}
	}
	
	public Waypoint getPreviousWaypoint() {
		if (currentWaypoint - 1 >= 0) {
			return path.get(currentWaypoint - 1);
		}
		else {
			Logger.getInstance().println("Attempt to get negative previous waypoint", Severity.ERROR);
			return path.get(currentWaypoint);
		}
	}
	
	public int getLastWaypointNumber() {
		return path.size()-1;
	}

	public void setClimbing(boolean climbing) {
		m_climbing = climbing;
	}
	
	public boolean getClimbing(){
		return m_climbing;
	}
}
