package org.usfirst.frc.team4277.robot.commands;

import org.usfirst.frc.team4277.robot.Robot;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTestCommand extends Command {
	
	double startTime;
	boolean finish  = false;
	
	double driveX;
	double driveY;
	double driveZ;
	
	double distanceX;
	double distanceY;
	double degrees;
	double durration;

    public AutoTestCommand(Double inputDistanceX, Double inputDistanceY, Double inputDegrees, Double inputDurration) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.mecanumDrive);
    	
    	distanceX = inputDistanceX;
    	distanceY = inputDistanceY;
    	durration = inputDurration;
    	degrees = inputDegrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = RobotController.getFPGATime();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if((RobotController.getFPGATime() - startTime)/1000000 >= durration) {
    		finish = true;
    	}
    	
    	driveX = Robot.mecanumDrive.calculateLMDriveVector(distanceX, distanceY, durration, startTime).x;
    	driveY = Robot.mecanumDrive.calculateLMDriveVector(distanceX, distanceY, durration, startTime).y;
    	driveZ = Robot.mecanumDrive.calculateLMRotationalVelocity(degrees, durration, startTime);
    	
    	Robot.mecanumDrive.autonomousMecanumDrive(driveX, driveY, driveZ, Robot.m_oi.getGyro());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finish;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.mecanumDrive.autonomousMecanumStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.mecanumDrive.autonomousMecanumStop();
    }
}
