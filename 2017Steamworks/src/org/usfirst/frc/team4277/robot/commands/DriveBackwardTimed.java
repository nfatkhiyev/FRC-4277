package org.usfirst.frc.team4277.robot.commands;

import org.usfirst.frc.team4277.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command drives the robot at a given speed for a given time.
 */
public class DriveBackwardTimed extends Command {
	
	private double driveForwardSpeed;
	private double time;

	
	public DriveBackwardTimed() {
		this(3.2, -0.6);
	}

	public DriveBackwardTimed(double time, double speed) {
		requires(Robot.driveTrain);
		this.driveForwardSpeed = speed;
		this.time = time;
	}

	@Override
	protected void initialize() {
		setTimeout(time);
	}

	@Override
	protected void execute() {
		System.out.println("Execute Time since initialized: " + Double.toString(timeSinceInitialized()));
		Robot.driveTrain.driveBackwardAtPower(driveForwardSpeed);
	}
	
	@Override
	protected boolean isFinished() {
		System.out.println("Is Finished Time since initialized: " + Double.toString(timeSinceInitialized()));
		return isTimedOut();
	}

	@Override
	protected void end() {
		Robot.driveTrain.stop();
	} 
}
