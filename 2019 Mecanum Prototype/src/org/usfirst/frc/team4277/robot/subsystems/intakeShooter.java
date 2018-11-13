package org.usfirst.frc.team4277.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class intakeShooter extends Subsystem {
	
	private static TalonSRX SHOOTER_LEFT,SHOOTER_RIGHT,INTAKE_RIGHT;
	private static VictorSPX INTAKE_LEFT;

    public intakeShooter(int intakeLeft, int intakeRight, int shooterLeft, int shooterRight) {
    	INTAKE_LEFT = new VictorSPX(intakeLeft);
    	INTAKE_RIGHT = new TalonSRX(intakeRight);
    	
    	SHOOTER_LEFT = new TalonSRX(shooterLeft);
    	SHOOTER_RIGHT = new TalonSRX(shooterRight);
    	
    	INTAKE_LEFT.setInverted(true);
    	SHOOTER_LEFT.setInverted(true);
    }
    
    public void intakeCubeXBOX(Joystick stickXBOX) {
    	if (Math.abs(stickXBOX.getRawAxis(5)) < 0.1) {
    		INTAKE_LEFT.set(ControlMode.PercentOutput, 0.4);
    		INTAKE_RIGHT.set(ControlMode.PercentOutput, 0.4);
    		
    		SHOOTER_LEFT.set(ControlMode.PercentOutput, 0.3);
    		SHOOTER_RIGHT.set(ControlMode.PercentOutput, 0.3);
    	}
    	else if (Math.abs(stickXBOX.getRawAxis(5)) > 0.1) {
    		INTAKE_LEFT.set(ControlMode.PercentOutput, -(stickXBOX.getRawAxis(5)));
    		INTAKE_RIGHT.set(ControlMode.PercentOutput,-(stickXBOX.getRawAxis(5)));
    		
    		SHOOTER_LEFT.set(ControlMode.PercentOutput, -(0.75 * stickXBOX.getRawAxis(5)));
    		SHOOTER_RIGHT.set(ControlMode.PercentOutput, -(0.75 * stickXBOX.getRawAxis(5)));
    	}
    }
    
    public void outtakeCubeXBOX(Joystick stickXBOX) {
    	if (Math.abs(stickXBOX.getRawAxis(5)) < 0.1) {
    		INTAKE_LEFT.set(ControlMode.PercentOutput, -0.4);
    		INTAKE_RIGHT.set(ControlMode.PercentOutput, -0.4);
    		
    		SHOOTER_LEFT.set(ControlMode.PercentOutput, -0.3);
    		SHOOTER_RIGHT.set(ControlMode.PercentOutput, -0.3);
    	}
    	else if(Math.abs(stickXBOX.getRawAxis(5)) > 0.1) {
    		INTAKE_LEFT.set(ControlMode.PercentOutput, stickXBOX.getRawAxis(5));
    		INTAKE_RIGHT.set(ControlMode.PercentOutput, stickXBOX.getRawAxis(5));
    		
    		SHOOTER_LEFT.set(ControlMode.PercentOutput, 0.75 * stickXBOX.getRawAxis(5));
    		SHOOTER_RIGHT.set(ControlMode.PercentOutput, 0.75 * stickXBOX.getRawAxis(5));
    	}
    }
    
    public void stop() {
    	INTAKE_LEFT.set(ControlMode.PercentOutput, 0.00);
		INTAKE_RIGHT.set(ControlMode.PercentOutput, 0.00);
		
		SHOOTER_LEFT.set(ControlMode.PercentOutput, 0.00);
		SHOOTER_RIGHT.set(ControlMode.PercentOutput, 0.00);
    }
    
    public void shoot() {
    	SHOOTER_LEFT.set(ControlMode.PercentOutput, 0.75);
		SHOOTER_RIGHT.set(ControlMode.PercentOutput, 0.75);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

