package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.PortMap;

import edu.wpi.first.wpilibj.Servo;
//import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class CamPan extends Subsystem implements PortMap {
	
	private Servo pan = new Servo (PAN_PWM) ;
	
	public CamPan() {
		super();
		LiveWindow.addActuator("Camera Pan", "Camera Pan", (Servo) pan);
	
	}
	
	public void positionForGears(){
		moveServo(0.1);
	}
	public void positionForClimb(){
		moveServo(0.99);
	}
	
	
	
	public void moveServo(double speed){
		pan.set(speed);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

