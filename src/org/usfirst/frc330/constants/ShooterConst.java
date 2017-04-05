package org.usfirst.frc330.constants;

import org.usfirst.frc330.util.TalonPIDSettings;

public class ShooterConst {
	                                                                 		// P     I     D     F       rampRate  RPM    tol     Hood
	public static final TalonPIDSettings EXTRA_CLOSE	= new TalonPIDSettings(0.2,  0.0,  0.0,  0.0045, 100.0,    3450,  100.0,  0.993);   // AP 3-25
	public static final TalonPIDSettings CLOSE 			= new TalonPIDSettings(0.2,  0.0,  0.0,  0.0045, 100.0,    3450,  100.0,  0.989);   // AP 3-25 TOok off 0.004 per Shane
	public static final TalonPIDSettings FAR 		    = new TalonPIDSettings(0.25,  0.0,  0.0,  0.005, 100.0,    3600,  100.0,  0.94);   // WAG
	public static final TalonPIDSettings JIFFY_POP	    = new TalonPIDSettings(0.25,  0.0,  0.0,  0.005, 100.0,    1200,  100.0,  0.85);   // AP
	public static final TalonPIDSettings RECIRCULATE    = new TalonPIDSettings(0.2,  0.0,  0.0,  0.0045, 0.000,    -100,  200.0,  0.99);   // AP 3-11
	public static final TalonPIDSettings CENTER_AUTO 	= new TalonPIDSettings(0.25,  0.0,  0.0,  0.005, 100.0,    4150,  100.0,  0.923);	//zac 3-18 WAG
	public static final TalonPIDSettings RB_KPA 		= new TalonPIDSettings(0.25,  0.0,  0.0,  0.005, 100.0,    3700,  100.0,  0.96);	//zac 3-20 WAG
	public static final TalonPIDSettings RIGHT_AUTO 	= new TalonPIDSettings(0.25,  0.0,  0.0,  0.005, 100.0,    3800,  100.0,  0.937);	//zac 3-20 WAG
	public static final TalonPIDSettings FARSIDE_AUTO 	= new TalonPIDSettings(0.25,  0.0,  0.0,  0.005, 100.0,    4150,  100.0,  0.930);	//SP 3-20 WAG
	public static final TalonPIDSettings DEFAULT        = CLOSE;
	
	//3700 actual
	//public static final TalonPIDSettings CENTER_AUTO 	= new TalonPIDSettings(0.25,  0.0,  0.0,  0.005, 100.0,    0,  100.0,  0.90);	//zac 3-18 WAG
	
																			// P     I     D     F       rampRate  RPM    tol     Hood
	public static final TalonPIDSettings GATE        	= new TalonPIDSettings(0.03, 0.0,  0.0,  0.045,  0.000,    1500,  100.0,  0.0);   //WAG
	public static final TalonPIDSettings GATE_SHOOTING  = GATE;
	public static final double GATE_REVERSE_PERCENT 	= -0.1;
	
	public static final double MIN_HOOD_ANGLE 			= 0.84; //AP 3-11

	//Climbing																// P     I     D     F     rampRate  RPM    tol     Hood
	public static final TalonPIDSettings CLIMB        	= new TalonPIDSettings(0.5, 0.0,  0.0,  0.35,  0.000,    400,  100.0,  0.0);   //JR 3-12
	

	public static TalonPIDSettings shooterLookup(double rect){
		if(rect > 110){
			return EXTRA_CLOSE;
		}
		else if(rect > 104){
			return new TalonPIDSettings(0.2,  0.0,  0.0,  0.005, 100.0,    3450,  100.0,  0.9838);
		}
		else if(rect > 101){
			return new TalonPIDSettings(0.2,  0.0,  0.0,  0.005, 100.0,    3500,  100.0,  0.9694);
		}
		else if(rect > 97){
			return new TalonPIDSettings(0.2,  0.0,  0.0,  0.005, 100.0,    3500,  100.0,  0.965);
		}
		else if(rect > 92){
			return new TalonPIDSettings(0.2,  0.0,  0.0,  0.005, 100.0,    3500,  100.0,  0.9561);
		}
		else if(rect > 86){
			return new TalonPIDSettings(0.2,  0.0,  0.0,  0.005, 100.0,    3550,  100.0,  0.9516);
		}
		else if(rect > 82){
			return new TalonPIDSettings(0.2,  0.0,  0.0,  0.005, 100.0,    3650,  100.0,  0.942);
		}
		else if(rect > 77){
			return new TalonPIDSettings(0.2,  0.0,  0.0,  0.005, 100.0,    3650,  100.0,  0.9394);
		}
		else if(rect > 72){
			return new TalonPIDSettings(0.2,  0.0,  0.0,  0.005, 100.0,    3750,  100.0,  0.93);
		}
		else if(rect > 68){
			return new TalonPIDSettings(0.2,  0.0,  0.0,  0.005, 100.0,    3750,  100.0,  0.921);
		}
		else if(rect > 63){
			return new TalonPIDSettings(0.2,  0.0,  0.0,  0.005, 100.0,    3850,  100.0,  0.9167);
		}
		else if(rect > 58){
			return new TalonPIDSettings(0.2,  0.0,  0.0,  0.005, 100.0,    3950,  100.0,  0.9078);
		}
		else if(rect > 52){
			return new TalonPIDSettings(0.2,  0.0,  0.0,  0.005, 100.0,    4100,  100.0,  0.903);
		}
		else{
			return new TalonPIDSettings(0.2,  0.0,  0.0,  0.005, 100.0,    4250,  100.0,  0.8944);
		}
	}
}
