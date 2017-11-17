package org.usfirst.frc.team4277.robot.commands;

import org.usfirst.frc.team4277.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearRelease extends Command {

    public GearRelease() {
    	super();
    	requires(Robot.gearSqueezer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gearSqueezer.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("GearRelease execute");
    	Robot.gearSqueezer.release();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("GearRelease isFinished Is released?" + Robot.gearSqueezer.isReleased());
    	return Robot.gearSqueezer.isAtReleasePosition() || Robot.gearSqueezer.isReleased();
    	//return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("GearRelease end");
    	Robot.gearSqueezer.stop();
    	Robot.gearSqueezer.completeRelease();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.gearSqueezer.stop();
    }
}
