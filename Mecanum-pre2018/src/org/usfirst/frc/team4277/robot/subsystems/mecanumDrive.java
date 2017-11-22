package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import com.ctre.CANTalon;
//import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class mecanumDrive extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	static CANTalon FrontRight;
	static CANTalon FrontLeft;
	static CANTalon BackRight;
	static CANTalon BackLeft;
	
	/*
	static Victor FrontRight;
	static Victor FrontLeft;
	static Victor BackRight;
	static Victor BackLeft;
	*/
	
	double FRValue,BRValue,FLValue,BLValue;
	
	 public mecanumDrive(int FR, int FL, int BR, int BL){
		 
			//motor controller assignments
			FrontRight = new CANTalon(FR);
			FrontLeft = new CANTalon(FL);
			BackRight = new CANTalon(BR);
			BackLeft = new CANTalon(BL);
			/*
			FrontRight = new Victor(FR);
			FrontLeft = new Victor(FL);
			BackRight = new Victor(BR);
			BackLeft = new Victor(BL);
			*/
			BackLeft.setInverted(true);
			FrontLeft.setInverted(true);
			FRValue = FrontRight.getSpeed();
			BRValue = BackRight.getSpeed();
			BLValue = BackLeft.getSpeed();
			FLValue = FrontLeft.getSpeed();
			FrontRight.setSafetyEnabled(true);
			FrontLeft.setSafetyEnabled(true);
			BackRight.setSafetyEnabled(true);
			BackLeft.setSafetyEnabled(true);
			//FrontRight.setVoltageRampRate(6);
			//FrontLeft.setVoltageRampRate(6);
			//BackRight.setVoltageRampRate(6);
			//BackLeft.setVoltageRampRate(6);
		}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new JoystickDriveCommand());
    }
	
	public static void mechJoystickDrive(Joystick stick){
		
		double xVal = stick.getX();
		double yVal = stick.getY();//experiment with running this through equations
		double twist = stick.getTwist();
		
		if (Math.abs(xVal) < 0.05) xVal = 0;
		if (Math.abs(yVal) < 0.05) yVal = 0;
		if (Math.abs(twist) < 0.05) twist = 0;
		
		double adjustor = ((2*Math.sqrt(2))/2) + Math.abs(twist);
		
		double speed = Math.sqrt((Math.pow(xVal, 2)) + (Math.pow(yVal, 2)));
		double angle = Math.asin(yVal/speed);
		
		double fLeft = ((Math.sin(angle)*speed)-(Math.cos(angle)*speed)-twist) / adjustor;
		double fRight = ((Math.sin(angle)*speed)+(Math.cos(angle)*speed)+twist) / adjustor;
		double bLeft = ((Math.sin(angle)*speed)-(Math.cos(angle)*speed)+twist) / adjustor;
		double bRight = ((Math.sin(angle)*speed)+(Math.cos(angle)*speed)-twist) / adjustor;
		
		if (Math.abs(fLeft) < 0.05) fLeft = 0;
		if (Math.abs(fRight) < 0.05) fRight = 0;
		if (Math.abs(bLeft) < 0.05) bLeft = 0;
		if (Math.abs(bRight) < 0.05) bRight = 0;
		
		FrontLeft.set(fLeft);
		FrontRight.set(fRight);
		BackLeft.set(bLeft);
		BackRight.set(bRight);
	}
	
	public static void mechJoystickGyroDrive(Joystick stick, Double gyroAngle) {//hopefully we can get a navX gyro for precise data(IM)
		double xVal = stick.getX();
		double yVal = stick.getY();//experiment with running this through equations
		double twist = stick.getTwist();
		double orientation = Math.toRadians(gyroAngle);
		
		if (Math.abs(xVal) < 0.05) xVal = 0;
		if (Math.abs(yVal) < 0.05) yVal = 0;
		if (Math.abs(twist) < 0.05) twist = 0;
		
		double adjustor = Math.sqrt(2) + Math.abs(twist);
		
		double speed = Math.sqrt((Math.pow(xVal, 2)) + (Math.pow(yVal, 2)));
		double angle = Math.asin(yVal/speed) + orientation;
		
		double fLeft = ((Math.sin(angle)*speed)-(Math.cos(angle)*speed)-twist) / adjustor;
		double fRight = ((Math.sin(angle)*speed)+(Math.cos(angle)*speed)+twist) / adjustor;
		double bLeft = ((Math.sin(angle)*speed)-(Math.cos(angle)*speed)+twist) / adjustor;
		double bRight = ((Math.sin(angle)*speed)+(Math.cos(angle)*speed)-twist) / adjustor;
		
		if (Math.abs(fLeft) < 0.05) fLeft = 0;
		if (Math.abs(fRight) < 0.05) fRight = 0;
		if (Math.abs(bLeft) < 0.05) bLeft = 0;
		if (Math.abs(bRight) < 0.05) bRight = 0;
		
		FrontLeft.set(fLeft);
		FrontRight.set(fRight);
		BackLeft.set(bLeft);
		BackRight.set(bRight);
	}
	
	public void mechDirectionalDrive (Double angle, Double speed, long durration) {
		//Sets motor timeouts
		/*FrontRight.setExpiration(durration);
		FrontLeft.setExpiration(durration);
		BackRight.setExpiration(durration);
		BackLeft.setExpiration(durration);*/
		
		long millisecondsToRun = durration; // Timeout
		long initTime = Utility.getFPGATime();
		
		//Conversion from polar to coordinate system
		Double rad = Math.toRadians(angle);
		Double xVal = Math.cos(rad) * speed;
		Double yVal = Math.sin(rad) * speed;
		
		//Drives the motors
		while (Utility.getFPGATime() - initTime <= millisecondsToRun){
			FrontRight.set((yVal - xVal)/Math.sqrt(2));
			BackLeft.set((yVal - xVal)/Math.sqrt(2));
			FrontLeft.set((yVal + xVal)/Math.sqrt(2));
			BackRight.set((yVal + xVal)/Math.sqrt(2));
		}
		
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



