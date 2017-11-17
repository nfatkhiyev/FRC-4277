package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.PortMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Climber extends Subsystem implements PortMap {

	private Talon climber = new Talon(CLIMBER_PWM);
	private DigitalOutput sonarOut = new DigitalOutput(CLIMBER_SONAR_OUTPUT);
	private DigitalInput sonarIn = new DigitalInput(CLIMBER_SONAR_INPUT);
	private Ultrasonic sonar = new Ultrasonic(sonarOut,sonarIn);
	private Double duration = 0.0;
	private Double distance = 0.0;

	public Climber() {
		super();
		//sonar.setAutomaticMode(true);
		sonar.setEnabled(true);
		LiveWindow.addActuator("Climber", "climber", (Talon) climber);
	
	}

    public void initDefaultCommand() {

        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    public void SetToSpeed (double speed) {
    climber.set(speed);    	 
    }
	public void climbSlow() {
		SetToSpeed(0.40);
		sonarOut.pulse(0.1);
		distance = sonar.getRangeInches();
		System.out.println(sonarIn.get());
	}
	public void stop() {
		SetToSpeed(0.0);
	}
	public void climbFast() {
		//sonarPing.pulse(5);
		//duration = (sonarRecieve.readRisingTimestamp()-sonarRecieve.readFallingTimestamp());
		//distance = duration / 148;
		//if (distance > 1){
			SetToSpeed (0.99);
		//}
		//else{
			//SetToSpeed(0.8);
			//Timer.delay(0.25);
			//stop();
		//}
	}
	public void calibrate() {
		SetToSpeed(-0.33);
	
	}
	
}

