package org.usfirst.frc.team4277.robot.commands;

import org.usfirst.frc.team4277.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearTiltDownManual extends Command {

    public GearTiltDownManual() {
    	super();
    	requires(Robot.gearTilter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gearTilter.tiltDownFast();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;

    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gearTilter.stop();
    }
}
