/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4277.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public interface RobotMap {
	
	//Drive Talons
	public static int FRONT_RIGHT_TALON = 5; //40AMP  -- Talon
	public static int FRONT_LEFT_TALON = 6;  //40AMP  -- Talon
	public static int BACK_RIGHT_TALON = 8;  //40AMP   -- Talon
	public static int BACK_LEFT_TALON = 9;  //40AMP   -- Talon
	
	//Intake 
	public static int INTAKE_LEFT_CONTROLLER = 16;
	public static int INTAKE_RIGHT_CONTROLLER = 15;
	
	//Shooter
	public static int SHOOTER_LEFT_CONTROLLER = 12;
	public static int SHOOTER_RIGHT_CONTROLLER = 13;
	
	//Auto test numbers
	public static double X_DISTANCE = 0.0;
	public static double Y_DISTANCE = 5.0;
	public static double DEGREES_OF_ROTATION = 0.0;
	public static double TIME = 2.0;
}
