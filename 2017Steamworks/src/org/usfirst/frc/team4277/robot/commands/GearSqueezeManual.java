package org.usfirst.frc.team4277.robot.commands;

import org.usfirst.frc.team4277.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearSqueezeManual extends Command {

	
	    public GearSqueezeManual() {
	    	super();
	    	requires(Robot.gearSqueezer);
	    }

	    // Called just before this Command runs the first time
	    protected void initialize() {
	    	Robot.gearSqueezer.reset();
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	System.out.println("GearSqueeze execute");
	    	Robot.gearSqueezer.slowSqueeze();
	    }

	    // Make this return true when this Command no longer needs to run execute()
	    protected boolean isFinished() {
	    	return false;
	    	//return Robot.gearSqueezer.isAtSqueezerPosition();
	    }

	    // Called once after isFinished returns true
	    protected void end() {
	    	Robot.gearSqueezer.stop();
	    }

	    // Called when another command which requires one or more of the same
	    // subsystems is scheduled to run
	    protected void interrupted() {
	    	Robot.gearSqueezer.stop();
	    }
    
}
