package org.usfirst.frc.team4277.robot;



import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon; // replace with other motor controllers if necessary

public class MechDrive {
	
	Talon FrontRight;
	Talon FrontLeft;
	Talon BackRight;
	Talon BackLeft;
	
	double FRValue,BRValue,FLValue,BLValue;

	public MechDrive(int FR, int FL, int BR, int BL){
		//motor controller assignments
		FrontRight = new Talon(FR);
		FrontLeft = new Talon(FL);
		BackRight = new Talon(BR);
		BackLeft = new Talon(BL);
		BackLeft.setInverted(true);
		FrontLeft.setInverted(true);
		FRValue = FrontRight.getSpeed();
		BRValue = BackRight.getSpeed();
		BLValue = BackLeft.getSpeed();
		FLValue = FrontLeft.getSpeed();
	}
	
	public void mechJoystickDrive(Joystick stick){
		
		double xVal = stick.getX();
		double yVal = stick.getY();//experiment with running this through equations
		double twist = stick.getTwist();
		
		if (Math.abs(xVal) < 0.05) xVal = 0;
		if (Math.abs(yVal) < 0.05) yVal = 0;
		if (Math.abs(twist) < 0.05) twist = 0;
		
		double adjustor = ((2*Math.sqrt(2))/2) + Math.abs(twist);
		
		double speed = Math.sqrt((Math.pow(xVal, 2)) + (Math.pow(yVal, 2)));
		double angle = Math.asin(yVal/Math.sqrt(yVal*yVal+xVal*xVal));
		
		double fLeft = ((Math.cos(angle)*speed)-(Math.sin(angle)*speed)-twist) / adjustor;
		double fRight = ((Math.cos(angle)*speed)+(Math.sin(angle)*speed)+twist) / adjustor;
		double bLeft = ((Math.cos(angle)*speed)-(Math.sin(angle)*speed)+twist) / adjustor;
		double bRight = ((Math.cos(angle)*speed)+(Math.sin(angle)*speed)-twist) / adjustor;
		
		if (Math.abs(fLeft) < 0.05) fLeft = 0;
		if (Math.abs(fRight) < 0.05) fRight = 0;
		if (Math.abs(bLeft) < 0.05) bLeft = 0;
		if (Math.abs(bRight) < 0.05) bRight = 0;
		
		FrontLeft.set(fLeft);
		FrontRight.set(fRight);
		BackLeft.set(bLeft);
		BackRight.set(bRight);
	}
	
	public void mechDirectionalDrive (Double angle, Double speed, Double durration) {
		//Sets motor timeouts
		FrontRight.setExpiration(durration);
		FrontLeft.setExpiration(durration);
		BackRight.setExpiration(durration);
		BackLeft.setExpiration(durration);
		
		//Conversion from polar to coordinate system
		Double rad = Math.toRadians(angle);
		Double xVal = Math.cos(rad) * speed;
		Double yVal = Math.sin(rad) * speed;
		
		//Drives the motors
		FrontRight.set((yVal - xVal)/1.45);
		BackLeft.set((yVal - xVal)/1.45);
		FrontLeft.set((yVal + xVal)/1.45);
		BackRight.set((yVal + xVal)/1.45);
	}
	public void mechSpinRight (Double speed){
		FrontRight.set(speed);
		BackLeft.set(-speed);
		FrontLeft.set(-speed);
		BackRight.set(speed);
	}
	public void mechSpinLeft (Double speed){
		FrontRight.set(-speed);
		BackLeft.set(speed);
		FrontLeft.set(speed);
		BackRight.set(-speed);
	}
	
	public double getFRValue(){
		return FRValue;
		
	}
	public double getBRValue(){
		return BRValue;
		
	}
	public double getBLValue(){
		return BLValue;
		
	}
	public double getFLValue(){
		return FLValue;
		
	}
}
