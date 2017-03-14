package org.usfirst.frc330.constants;

import org.usfirst.frc330.util.TalonPIDSettings;

public class ShooterConst {
	                                                                 		// P     I     D     F       rampRate  RPM    tol     Hood
	public static final TalonPIDSettings CLOSE 			= new TalonPIDSettings(0.2,  0.0,  0.0,  0.0045, 100.0,    3450,  100.0,  0.985);   // AP 3-11
	public static final TalonPIDSettings FAR 		    = new TalonPIDSettings(0.25,  0.0,  0.0,  0.005, 100.0,    3600,  100.0,  0.94);   //WAG
	public static final TalonPIDSettings RECIRCULATE    = new TalonPIDSettings(0.2,  0.0,  0.0,  0.0045, 0.000,    -100,  200.0,  0.99);   // AP 3-11
	public static final TalonPIDSettings DEFAULT        = CLOSE;
	
																			// P     I     D     F       rampRate  RPM    tol     Hood
	public static final TalonPIDSettings GATE        	= new TalonPIDSettings(0.03, 0.0,  0.0,  0.045,  0.000,    1500,  100.0,  0.0);   //WAG
	public static final double GATE_REVERSE_PERCENT 	= -0.1;
	
	public static final double MIN_HOOD_ANGLE 			= 0.84; //AP 3-11
	
	//Climbing																// P     I     D     F       rampRate  RPM    tol     Hood
	public static final TalonPIDSettings CLIMB        	= new TalonPIDSettings(1.0, 0.0,  0.0,  0.045,  0.000,    400,  100.0,  0.0);   //AP 3-12
}
