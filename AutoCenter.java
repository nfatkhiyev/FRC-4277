package org.usfirst.frc.team4277.robot.commands.auto;

import org.usfirst.frc.team4277.robot.Robot;
import org.usfirst.frc.team4277.robot.commands.auto.groups.AutoCenterLeft;
import org.usfirst.frc.team4277.robot.commands.auto.groups.AutoCenterRight;
import org.usfirst.frc.team4277.robot.commands.auto.groups.AutoDriveStraight;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoCenter extends Command {

    public AutoCenter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.randomizerSorter.isCloseSwitchLeft() == null) {
    		new AutoDriveStraight().start();
    	}
    	else if(Robot.randomizerSorter.isCloseSwitchLeft().booleanValue()) {
    		System.out.println("AutoCenterRight"+ Robot.isSwitchLeft);
    		new AutoCenterLeft().start();;
    	}
    	else if (!Robot.randomizerSorter.isCloseSwitchLeft().booleanValue()) {
    		System.out.println("AutoCenterRight"+Robot.isSwitchLeft);
    		new AutoCenterRight().start();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
