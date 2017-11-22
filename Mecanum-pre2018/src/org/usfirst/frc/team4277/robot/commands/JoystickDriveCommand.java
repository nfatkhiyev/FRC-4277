package org.usfirst.frc.team4277.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4277.robot.subsystems.*;
import org.usfirst.frc.team4277.robot.*;

/**
 *
 */
public class JoystickDriveCommand extends Command {
	private boolean gyroDrive;

    public JoystickDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires( new mecanumDrive(1, 2, 3, 4)); // FR 1 BR 3 FL 2 BL 4
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	gyroDrive = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.getdSTrigger() == true && gyroDrive == false) gyroDrive = true;
    	else if(OI.getdSTrigger() == true && gyroDrive == true) gyroDrive = false;
    	
    	if(gyroDrive == false) {
    		mecanumDrive.mechJoystickDrive(OI.getdriveStick());
    	}
    	if(gyroDrive == true) {
    		mecanumDrive.mechJoystickGyroDrive(OI.getdriveStick(), OI.getGyroAngle());
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
