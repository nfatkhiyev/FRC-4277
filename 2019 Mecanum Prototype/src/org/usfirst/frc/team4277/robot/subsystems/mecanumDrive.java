package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.commands.JoystickDriveCommand;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */

@SuppressWarnings("deprecation")
public class mecanumDrive extends Subsystem {
	
	static RobotDrive drive;
	
	private static WPI_TalonSRX FRONT_LEFT_TALON, FRONT_RIGHT_TALON, BACK_LEFT_TALON, BACK_RIGHT_TALON;
	
	static final double measuredTopSpeedStraight = 9.6;// ft/second
	static final double measuredTopSpeedSide = 6.383; // ft/second
	static final double measuredTopSpeedRotational = 10; //degrees per second

    public mecanumDrive(int frontLeft, int backLeft, int frontRight, int backRight) {
    	
    	FRONT_LEFT_TALON = new WPI_TalonSRX(frontLeft);
    	BACK_LEFT_TALON = new WPI_TalonSRX(backLeft);
    	FRONT_RIGHT_TALON = new WPI_TalonSRX(frontRight);
    	BACK_RIGHT_TALON = new WPI_TalonSRX(backRight);
    	
    	drive = new RobotDrive(FRONT_LEFT_TALON, BACK_LEFT_TALON, FRONT_RIGHT_TALON, BACK_RIGHT_TALON);
    	
    	FRONT_LEFT_TALON.setInverted(true);
    	BACK_LEFT_TALON.setInverted(true);
    	
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new JoystickDriveCommand());
    }
    
    //Joystick mecanum drive that is not field oriented
    public void joystickDrive(Joystick stick) {
    	double xVal = Math.pow(stick.getX(), (1/3) );
    	double yVal = Math.pow(stick.getY(), (1/3) );
    	drive.mecanumDrive_Cartesian(xVal, yVal, stick.getZ(), 0);
    	
    }
    
    //Joystick mecanum drive that uses a gyroscope for field oriented driving
    public void joystickFieldOrientatedDrive(Joystick stick, Gyro gyro) {
    	double xVal = Math.pow(stick.getX(), (1/3) );
    	double yVal = Math.pow(stick.getY(), (1/3) );
    	drive.mecanumDrive_Cartesian(xVal, yVal, stick.getZ(), gyro.getAngle());
    }
    
    public void autonomousMecanumDrive(Double driveX, Double driveY, Double driveZ, Gyro gyro) {
    	drive.mecanumDrive_Cartesian(driveX,driveY,driveZ,gyro.getAngle());
    }
    
    public void autonomousMecanumStop() {
    	drive.mecanumDrive_Cartesian(0.0,0.0,0.0,0.0);
    }
    //non-rotational direct directional drive calculations using a logistic model. Requires encoder to monitor x and y
    public Vector2d calculateLMDriveVector(Double distanceX, Double distanceY, Double durration, Double startTime) { 
    	//creating a vector to hold motor write values and robot velocity components
    	Vector2d driveVector;
    	Vector2d velocityVector;
    	
    	//calculating the current time from when the robot starts moving.
    	double timeEllapsed = (RobotController.getFPGATime() - startTime)/1000000;
    	
    	//calculating constant k that defines the motion of the robot in the x and y direction
    	double kX = Math.log((distanceX*(0.05/(distanceX-0.05))/(distanceX-0.01))-(0.05/(distanceX-0.05)))/(-distanceX*durration);
    	double kY = Math.log((distanceY*(0.05/(distanceY-0.05))/(distanceY-0.01))-(0.05/(distanceY-0.05)))/(-distanceY*durration);
    	
    	//calculates the constant c that defines the initial conditions for the motion profile
    	double cX = 0.05 / ((distanceX*kX)-(kX*0.05));
    	double cY = 0.05 / ((distanceY*kY)-(kY*0.05));
    	
    	//creates the motion profile for the robots motion
    	double xX = (distanceX*kX*cX)/((kX*cX)+Math.pow(Math.E, (-distanceX*kX*timeEllapsed)));
    	double xY = (distanceY*kY*cY)/((kY*cY)+Math.pow(Math.E, (-distanceY*kY*timeEllapsed)));
    	
    	//creates the velocity motion profile for the robots motion
    	double vX = kX*xX*(distanceX - xX);
    	double vY = kY*xY*(distanceY - xY);
    	
    	if (distanceX == 0) {
    		velocityVector = new Vector2d(0.0,vY);
    	}
    	else if(distanceY == 0) {
    		velocityVector = new Vector2d(vX,0.0);
    	}
    	else {
    		velocityVector = new Vector2d(vX,vY);
    	}
    	
    	driveVector = new Vector2d(velocityVector.x/measuredTopSpeedStraight,velocityVector.y/measuredTopSpeedSide);
    	
    	return  driveVector;
    }
    
    public Double  calculateLMRotationalVelocity( Double degrees, Double durration, Double startTime) {
    	double velocity;
    	double driveValue;
    	
    	double timeEllapsed = (RobotController.getFPGATime()-startTime)/1000000;
    	
    	double kZ = Math.log((degrees*(0.05/(degrees-0.05))/(degrees-0.03))-(0.05/(degrees-0.05)))/(-degrees*durration);

    	double cZ = 0.05 / ((degrees*kZ)-(kZ*0.05));
    	
    	double xZ = (degrees*kZ*cZ)/((kZ*cZ)+Math.pow(Math.E, (-degrees*kZ*timeEllapsed)));

    	double vZ = kZ*xZ*(degrees - xZ);
    	
    	if(degrees == 0.0) {
    		velocity = 0;
    	}
    	else {
    		velocity = vZ;
    	}
    	
    	driveValue = velocity/measuredTopSpeedRotational;
    	
    	return driveValue;
    }
  
}

