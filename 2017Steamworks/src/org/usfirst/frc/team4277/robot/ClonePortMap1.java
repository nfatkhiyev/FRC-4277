package org.usfirst.frc.team4277.robot;

public interface ClonePortMap1 {
	
	
	//  PWM Ports
	public final static int LEFT_BACK_DRIVE_PWM = 3;
	public final static int LEFT_FRONT_DRIVE_PWM = 2;
	
	public final static int RIGHT_BACK_DRIVE_PWM = 1;
	public final static int RIGHT_FRONT_DRIVE_PWM = 0;
	
	public final static int TILTER_PWM = 4;
	public final static int SHOOTER_PWM = 5;
	public final static int SQUEEZER_PWM = 6;
	
	// CAN
	
	
	
	//  Digital
	
	public final static int SQUEEZER_DIGITAL_IO = 9;
	public final static int BALL_GATE_DIGITAL_IO = 7; 
	public final static int SQUEEZER_ONE_IO = 6; //power
	public final static int SQUEEZER_TWO_IO = 5; //signal

	public final static int SQUEEZER_ZEROER_IO = 3;
	
	
	public final static int LEFT_ENCODER_A = 0;
	public final static int LEFT_ENCODER_B = 1;
	public final static int RIGHT_ENCODER_A = 8;
	public final static int RIGHT_ENCODER_B = 9;
	
	//  Analog
	public final static int TILTER_ANALOG = 3;
	
	
	// Laptop USB Ports
	public final int LEFT_JOYSTICK = 0;
	public final int RIGHT_JOYSTICK = 5;
	public final int XBOX_CONTROLLER = 3;
	
	// XBoxController
	public final  int XBOX_LEFT_STICK_AXIS_Y = 1;
	public final  int XBOX_RIGHT_STICK_AXIS_Y = 5;
	
	public final int XBOX_BUTTON_A = 1;
	public final int XBOX_BUTTON_B = 2;
	public final int XBOX_BUTTON_X = 3;
	public final int XBOX_BUTTON_Y = 4;
	public final int XBOX_BUTTON_LB = 5;
	public final int XBOX_BUTTON_RB = 6;
	public final int XBOX_BUTTON_BACK = 7;
	public final int XBOX_BUTTON_START = 8;
	public final int XBOX_JOY_LEFT_BUTTON = 9;
	public final int XBOX_JOY_RIGHT_BUTTON = 10;
	
	
//	The buttons on the controller follow this mapping
//
//	1: A
//	2: B
//	3: X
//	4: Y
//	5: Left Bumper
//	6: Right Bumper
//	7: Back
//	8: Start
//	9: Left Joystick
//	10: Right Joystick
//
//	The axis on the controller follow this mapping
//	(all output is between -1 and 1)
//	1: Left Stick X Axis
//	-Left:Negative ; Right: Positive
//	2: Left Stick Y Axis
//	-Up: Negative ; Down: Positive
//	3: Triggers
//	-Left: Positive ; Right: Negative
//	4: Right Stick X Axis
//	-Left: Negative ; Right: Positive
//	5: Right Stick Y Axis
//	-Up: Negative ; Down: Positive
//	6: Directional Pad (Not recommended, buggy)

}
