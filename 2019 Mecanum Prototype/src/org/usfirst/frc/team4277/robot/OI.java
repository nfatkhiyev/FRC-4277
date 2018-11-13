/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4277.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.interfaces.Gyro;

import org.usfirst.frc.team4277.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI implements RobotMap {
	
	private Joystick driveStick = new Joystick(0);
	private Joystick XBOX = new Joystick(1);
	
	private JoystickButton rightBumperXBOX = new JoystickButton(XBOX, 6);
	private JoystickButton leftBumperXBOX = new JoystickButton(XBOX,5);
	private JoystickButton xXBOX = new JoystickButton(XBOX,3);
	private JoystickButton rightAxisXBOX = new JoystickButton(XBOX,10);
	
	private JoystickButton bXBOX = new JoystickButton(XBOX,2);
	
	private static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	private DigitalInput photoElectric = new DigitalInput(1);
	
	public OI() {
		rightBumperXBOX.whenPressed(new IntakeCubeCommand());
		leftBumperXBOX.whileHeld(new OuttakeCubeCommand());
		xXBOX.whileHeld(new ShootCubeCommand());
		rightAxisXBOX.whenPressed(new StopCommand());
		
		bXBOX.whenPressed(new AutoTestCommand(X_DISTANCE,Y_DISTANCE,DEGREES_OF_ROTATION,TIME));
	}
	
	public Joystick getDriveStick() {
		return driveStick;
	}
	
	public Joystick getXBOX() {
		return XBOX;
	}
	
	public Gyro getGyro() {
		return gyro;
	}
	public static void resetGyro() {
		gyro.reset();
	}
	public boolean getPhotoElectric() {
		return photoElectric.get();
	}
}
