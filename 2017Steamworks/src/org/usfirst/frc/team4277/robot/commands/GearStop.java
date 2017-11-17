package org.usfirst.frc.team4277.robot.commands;

import org.usfirst.frc.team4277.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearStop extends Command {

    public GearStop() {
    	super();
    	requires(Robot.gearSqueezer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gearSqueezer.stop();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gearSqueezer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
