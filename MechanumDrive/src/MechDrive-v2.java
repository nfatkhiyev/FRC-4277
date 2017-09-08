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
	
	//Ensures that the given double does not exceed 1 or -1
	public static double setMaxRange(double xyz){
		if(1-Math.abs(xyz) > 0){
			xyz = 0;
		}
		else if(xyz > 0){
			xyz = xyz - 1;
		}
		else if(xyz < 0) {
			xyz = 1 + xyz;
		}
		return xyz;
	}
	
	public void mechJoystickDrive(Joystick stick){
		
		double xVal = stick.getX();
		double yVal = stick.getY();//experiment with multiplying the values
		double zVal = stick.getTwist() / 2;
		
		double FR = setMaxRange(yVal - xVal - zVal);
		double BR = setMaxRange(yVal + xVal - zVal);
		double FL = setMaxRange(yVal + xVal + zVal);
		double BL = setMaxRange(yVal - xVal + zVal);
		
		/*
		* Instead of providing an if statement when the joystick is twisted
		* I just mapped the twist value to a z-axis, and added that to your
		* set calls.
		* I also have a test program in python that makes it easy to tell 
		*if the wheels are spinning the correct way, and i've double checked
		*your numbers in there. They're A-okay.
		*/
		if (FR == 0 && BR == 0 && FL == 0 && BL == 0) {
			FrontRight.set(yVal - xVal - zVal);
			BackRight.set(yVal + xVal - zVal);
			FrontLeft.set(yVal + xVal + zVal);
			BackLeft.set(yVal - xVal + zVal);
		}
		else if(FR != 0 && BL == 0 || FL != 0 && BR == 0) {
			FrontRight.set(yVal - xVal - zVal - FR);
			BackRight.set(yVal + xVal - zVal - FL);
			FrontLeft.set(yVal + xVal + zVal - FL);
			BackLeft.set(yVal - xVal + zVal - FR);
		}
		else if(FR == 0 && BL != 0 || FL == 0 && BR != 0) {
			FrontRight.set(yVal - xVal - zVal - BR);
			BackRight.set(yVal + xVal - zVal - BL);
			FrontLeft.set(yVal + xVal + zVal - BL);
			BackLeft.set(yVal - xVal + zVal - BR);
		}
		else {
			FrontRight.set(yVal - xVal - zVal - FR);
			BackRight.set(yVal + xVal - zVal - BR);
			FrontLeft.set(yVal + xVal + zVal - FL);
			BackLeft.set(yVal - xVal + zVal - BL);
		}
	}
	
	public void mechDirectionalDrive (Double angle, Double speed, Double durration) { 
		/*
		This meathod is intended for autonomous use or button programming. The motor timeouts are define the amount of time
		that the the robot will drive. I actually havent tried the setExpiriation line before, but I think it will work.
		*/
		//Sets motor timeouts
		FrontRight.setExpiration(durration);
		FrontLeft.setExpiration(durration);
		BackRight.setExpiration(durration);
		BackLeft.setExpiration(durration);
		
		//Conversion from polar to coordinate system
		Double rad = angle * (Math.PI / 180); // This converts standard degrees from the parameter into radians.
		/*
		The next 2 lines convert the input from polar coordinates to cartesian. It is a simple trig function that allows the
		code to mimick a joystick (x,y) value instead of the inputted (angle,hypotenuse(speed)) values.
		*/
		Double xVal = Math.cos(rad) * speed; 
		Double yVal = Math.sin(rad) * speed;
		
		//This section takes the new values from above and writes them to motors.
		FrontRight.set(yVal - xVal);
		BackLeft.set(yVal - xVal);
		FrontLeft.set(yVal + xVal);
		BackRight.set(yVal + xVal);
	}
	public void mechSpinRight (Double speed){
		// this is also intended for autonomous to orient the robot. 
		//the stopping of the rotation will be controlled by a gyroscope set up in the subsystem because it will be easier to callibrate.
		FrontRight.set(speed);
		BackLeft.set(-speed);
		FrontLeft.set(-speed);
		BackRight.set(speed);
	}
	public void mechSpinLeft (Double speed){ // same as above
		FrontRight.set(-speed);
		BackLeft.set(speed);
		FrontLeft.set(speed);
		BackRight.set(-speed);
	}
	// the last part is just returning values so you can display themon the dashboard for testing.
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
