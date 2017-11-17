package org.usfirst.frc.team4277.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4277.robot.Robot;

/**
 * This command drives the robot over a given distance with simple proportional
 * control This command will drive a given distance limiting to a maximum speed.
 */
public class DriveForwardDistance extends Command {
	
	 private PIDController pid;

	    public DriveForwardDistance(double distance) {
	        requires(Robot.driveTrain);
	        pid = new PIDController(4, 0, 0,
	                new PIDSource() {
	                    PIDSourceType m_sourceType = PIDSourceType.kDisplacement;

	                    public double pidGet() {
	                        return Robot.driveTrain.getDistance();
	                    }

	                    @Override
	                    public void setPIDSourceType(PIDSourceType pidSource) {
	                      m_sourceType = pidSource;
	                    }

	                    @Override
	                    public PIDSourceType getPIDSourceType() {
	                        return m_sourceType;
	                    }
	                },
	                new PIDOutput() { public void pidWrite(double d) {
	                    Robot.driveTrain.drive(d, d);
	                }});
	        pid.setAbsoluteTolerance(0.01);
	        pid.setSetpoint(distance);
	    }

	    // Called just before this Command runs the first time
	    protected void initialize() {
	    	// Get everything in a safe starting state.
	        Robot.driveTrain.reset();
	    	pid.reset();
	        pid.enable();
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {}

	    // Make this return true when this Command no longer needs to run execute()
	    protected boolean isFinished() {
	    	System.out.println("Distance: " + Robot.driveTrain.getDistance());
	        return pid.onTarget();
	    }

	    // Called once after isFinished returns true
	    protected void end() {
	    	// Stop PID and the wheels
	    	pid.disable();
	        Robot.driveTrain.stop();
	    }

	    // Called when another command which requires one or more of the same
	    // subsystems is scheduled to run
	    protected void interrupted() {
	        end();
	    }
}
