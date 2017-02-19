package org.usfirst.frc330.constants;

public class ShooterConst {
	                                                                  //  P    I    D    F      rampRate RPM  tol    Hood
	public static final ShooterSettings CLOSE =       new ShooterSettings(0.2, 0.0, 0.0, 0.0045, 100.0, 3000, 100.0, 0.0);   //WAG
	public static final ShooterSettings FAR =         new ShooterSettings(0.2, 0.0, 0.0, 0.0045, 100.0, 4000, 100.0, 0.0);   //WAG
	public static final ShooterSettings RECIRCULATE = new ShooterSettings(0.2, 0.0, 0.0, 0.0045, 0.000, 1000, 200.0, 0.0);   //WAG
	public static final ShooterSettings DEFAULT     = CLOSE;
	
	
	public static class ShooterSettings {
		double p,i,d,f;
		double rampRate;
		double targetRPM;
		double tolerance;
		double hoodLocation;
		
		public double getP() {
			return p;
		}
		public double getI() {
			return i;
		}
		public double getD() {
			return d;
		}
		public double getF() {
			return f;
		}
		public double getRampRate() {
			return rampRate;
		}
		public double getTargetRPM() {
			return targetRPM;
		}
		public double getTolerance() {
			return tolerance;
		}
		public double getHoodLocation() {
			return hoodLocation;
		}
		public ShooterSettings(double p, double i, double d, double f, double rampRate, double targetRPM, double tolerance, double hoodLocation) {
			super();
			this.p = p;
			this.i = i;
			this.d = d;
			this.f = f;
			this.rampRate= rampRate;
			this.targetRPM = targetRPM;
			this.tolerance = tolerance;
			this.hoodLocation = hoodLocation;
		}
		@Override
		public String toString() {
			return "P: " + p + " I: " + i + " D: " + d + " F: " + f + " Ramp: " + rampRate + " Target: " + targetRPM + "Tolerance: " + tolerance + " Hood: " + hoodLocation;
		}
		
	}

}
