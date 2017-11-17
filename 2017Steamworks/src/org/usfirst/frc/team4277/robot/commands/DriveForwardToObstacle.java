package org.usfirst.frc.team4277.robot.commands;

//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4277.robot.Robot;

/**
 * This command drives the robot over a given distance with simple proportional
 * control This command will drive a given distance limiting to a maximum speed.
 */
public class DriveForwardToObstacle extends Command {
	
	private double driveForwardSpeed;
	private double distance;
	private double error;
	private final double kTolerance = 0.1;
	private final double kP = -1.0 / 5.0;
	
	public DriveForwardToObstacle() {
		this(10, 0.5);
	}

	public DriveForwardToObstacle(double dist) {
		this(dist, 0.5);
	}

	public DriveForwardToObstacle(double dist, double speed) {
		requires(Robot.driveTrain);
		distance = dist;
		driveForwardSpeed = speed;
	}

	@Override
	protected void initialize() {
	//	Robot.driveTrain.getRightEncoder().reset();
		setTimeout(6);
	}

	@Override
	protected void execute() {
		//error = (distance - Robot.driveTrain.getRightEncoder().getDistance());
		if (driveForwardSpeed * kP * error >= driveForwardSpeed) {
			Robot.driveTrain.drive(driveForwardSpeed, driveForwardSpeed);
		} else {
			Robot.driveTrain.drive(driveForwardSpeed * kP * error, driveForwardSpeed * kP * error);
	}
		Robot.driveTrain.driveForwardAtPower(0.2);
	}
	
	@Override
	protected boolean isFinished() {
		//return (Math.abs(error) <= kTolerance) || isTimedOut();
		 return Robot.driveTrain.isAtStopPosition(); 
		//return false;
	}

	@Override
	protected void end() {
		Robot.driveTrain.stop();
	} 
}
