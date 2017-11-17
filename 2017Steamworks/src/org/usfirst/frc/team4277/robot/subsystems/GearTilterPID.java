package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.PortMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDController;
/**
 *
 */
public class GearTilterPID extends Subsystem implements PortMap{

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private  Talon tilter = new Talon(TILTER_PWM);
	private AnalogPotentiometer pot = new AnalogPotentiometer(TILTER_ANALOG, 1000, 0);
	private PIDController gearTilterUP;
	private boolean trigger; 

	public GearTilterPID(){
		
		gearTilterUP = new PIDController(0, 0, 0, pot, tilter);
		gearTilterUP.setSetpoint(326.8);
		gearTilterUP.setContinuous(false);
		trigger = false;
		
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void TiltGearUP(){
    	tilter.setInverted(true);
    	gearTilterUP.enable();
    }
    public void stop(){
    	tilter.set(0);
    }
    public boolean getPIDError(){
    	if (gearTilterUP.getError() == 0){
    		trigger = true;
    	}
    	else{
    		trigger = false;
    	}
    	return trigger;
    }
    
}

