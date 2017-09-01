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
		double yVal = stick.getY();//experiment with multiplying the values
		double zVal = stick.getTwist();
		
		/*
		* Instead of providing an if statement when the joystick is twisted
		* I just mapped the twist value to a z-axis, and added that to your
		* set calls.
		* I also have a test program in python that makes it easy to tell 
		*if the wheels are spinning the correct way, and i've double checked
		*your numbers in there. They're A-okay.
		*/
		
		FrontRight.set(yVal - xVal - zVal);
		BackRight.set(yVal + xVal - zVal);
		FrontLeft.set(yVal + xVal + zVal);
		BackLeft.set(yVal - xVal + zVal);
	}
	
	public void mechDirectionalDrive (Double angle, Double speed, Double durration) { // I honestly don't know what's going on here
		//Sets motor timeouts
		FrontRight.setExpiration(durration);
		FrontLeft.setExpiration(durration);
		BackRight.setExpiration(durration);
		BackLeft.setExpiration(durration);
		
		//Conversion from polar to coordinate system
		Double rad = angle * (Math.PI / 180);
		Double xVal = Math.cos(rad) * speed;
		Double yVal = Math.sin(rad) * speed;
		
		//Drives the motors
		FrontRight.set(yVal - xVal);
		BackLeft.set(yVal - xVal);
		FrontLeft.set(yVal + xVal);
		BackRight.set(yVal + xVal);
	}
	public void mechSpinRight (Double speed){ // Is this for automation?
		FrontRight.set(speed);
		BackLeft.set(-speed);
		FrontLeft.set(-speed);
		BackRight.set(speed);
	}
	public void mechSpinLeft (Double speed){ // Is this for automation?
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
