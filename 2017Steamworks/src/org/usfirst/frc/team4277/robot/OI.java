package org.usfirst.frc.team4277.robot;

import org.usfirst.frc.team4277.robot.commands.ClimbFast;
import org.usfirst.frc.team4277.robot.commands.ClimbReverse;
import org.usfirst.frc.team4277.robot.commands.ClimbSlow;
import org.usfirst.frc.team4277.robot.commands.ClimbStop;
import org.usfirst.frc.team4277.robot.commands.DriveReverseCommand;
//import org.usfirst.frc.team4277.robot.commands.GearDeployCommand;
import org.usfirst.frc.team4277.robot.commands.AllStopManual;
import org.usfirst.frc.team4277.robot.commands.CameraPositionForClimb;
import org.usfirst.frc.team4277.robot.commands.CameraPositionForGears;
//import org.usfirst.frc.team4277.robot.commands.DriveReverseCommand;
import org.usfirst.frc.team4277.robot.commands.GearRelease;
import org.usfirst.frc.team4277.robot.commands.GearReleaseCombo;
import org.usfirst.frc.team4277.robot.commands.GearReleaseGroup;
import org.usfirst.frc.team4277.robot.commands.GearReleaseManual;
import org.usfirst.frc.team4277.robot.commands.GearReset;
import org.usfirst.frc.team4277.robot.commands.GearSqueeze;
import org.usfirst.frc.team4277.robot.commands.GearSqueezeGroup;
import org.usfirst.frc.team4277.robot.commands.GearSqueezeManual;
import org.usfirst.frc.team4277.robot.commands.GearSqueezeNTilt;
import org.usfirst.frc.team4277.robot.commands.GearStop;
import org.usfirst.frc.team4277.robot.commands.GearTiltDown;
import org.usfirst.frc.team4277.robot.commands.GearTiltDownManual;
//import org.usfirst.frc.team4277.robot.commands.GearTiltPIDUP;
import org.usfirst.frc.team4277.robot.commands.GearTiltToPeg;
import org.usfirst.frc.team4277.robot.commands.GearTiltUp;
import org.usfirst.frc.team4277.robot.commands.GearTiltUpManual;
import org.usfirst.frc.team4277.robot.commands.GearTilterStop;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI implements PortMap{

	public static Joystick driverControllerLeft;
	public static Joystick copilotXboxController;
	public static Joystick driverControllerRight;

	public OI() {
		
		setDriverControllerLeft(new Joystick(2));
		setCopilotXboxController(new Joystick(1));
		setDriverControllerRight(new Joystick(5));
		
		System.out.println("new OI");

		// Pilot controls
		Button gearSqueezeButton = new JoystickButton(driverControllerRight, 4);
		gearSqueezeButton.whenPressed(new GearSqueezeGroup());
		
		Button gearReleaseButton = new JoystickButton(driverControllerRight, 5);
		gearReleaseButton.whenPressed(new GearReleaseGroup());
		
		Button gearTiltUpButton = new JoystickButton(driverControllerRight, 3);
		gearTiltUpButton.whenPressed(new GearTiltUp());
		
		Button gearTiltDownButton = new JoystickButton(driverControllerRight, 2);
		gearTiltDownButton.whenPressed(new GearTiltDown());
		
		Button gearTiltToPeg = new JoystickButton(driverControllerRight, 8);
		gearTiltToPeg.whenPressed(new GearTiltToPeg());
		
		Button climbStopButton = new JoystickButton(driverControllerLeft, 2);
		climbStopButton.whileHeld(new ClimbStop());
		
		Button climbBackButton = new JoystickButton(driverControllerLeft, 8);
		climbBackButton.whileHeld(new ClimbReverse());
		
		Button cameraClimb = new JoystickButton(driverControllerLeft, 4);
		cameraClimb.whileHeld(new CameraPositionForClimb());
		
		Button cameraGears = new JoystickButton(driverControllerLeft, 5);
		cameraGears.whileHeld(new CameraPositionForGears());
		
		Button climbSlowButton = new JoystickButton(driverControllerLeft, 9);
		climbSlowButton.whileHeld(new ClimbSlow());
		
		Button climbFastButton = new JoystickButton(driverControllerLeft, 3);
		climbFastButton.whileHeld(new ClimbFast());
		
		Button reverseDrive = new JoystickButton(driverControllerLeft, 11);
		reverseDrive.whileHeld(new DriveReverseCommand());
		
		Button gearDeploy = new JoystickButton(driverControllerLeft, 10);
		gearDeploy.whenPressed(new GearReleaseCombo());
		
		// Copilot controls
		Button gearSqueezeManualButton = new JoystickButton(copilotXboxController, XBOX_BUTTON_X);
		gearSqueezeManualButton.whileHeld(new GearSqueezeManual());
		
		Button gearReleaseManualButton = new JoystickButton(copilotXboxController, XBOX_BUTTON_B);
		gearReleaseManualButton.whileHeld(new GearReleaseManual());
		
		Button gearReleaseStopButton = new JoystickButton(copilotXboxController, XBOX_BUTTON_LB);
		gearReleaseStopButton.whenPressed(new AllStopManual());
		
		Button gearTiltUpManualButton = new JoystickButton(copilotXboxController, XBOX_BUTTON_Y);
		gearTiltUpManualButton.whileHeld(new GearTiltUpManual());
		
		Button gearTiltDownManualButton = new JoystickButton(copilotXboxController, XBOX_BUTTON_A);
		gearTiltDownManualButton.whileHeld(new GearTiltDownManual());
		
		SmartDashboard.putData("All Stop Manual", new AllStopManual());
		
		SmartDashboard.putData("Gear Squeeze", new GearSqueeze());
		SmartDashboard.putData("Gear Release", new GearRelease());
		SmartDashboard.putData("Gear Manual Squeeze", new GearSqueezeManual());
		SmartDashboard.putData("Gear Manual Release", new GearReleaseManual());
		
		SmartDashboard.putData("Climb", new ClimbSlow());
		SmartDashboard.putData("Calibrate", new ClimbFast());
		
		SmartDashboard.putData("Gear Stop", new GearStop());
		SmartDashboard.putData("Gear Reset", new GearReset());
		
		SmartDashboard.putData("Gear Tilt Up", new GearTiltUp());
		SmartDashboard.putData("Gear Tilt Down", new GearTiltDown());
		SmartDashboard.putData("Gear Tilt To Peg", new GearTiltToPeg());
		SmartDashboard.putData("Tilt Up Manual", new GearTiltUpManual());
		SmartDashboard.putData("Tilt Down Manual", new GearTiltDownManual());

		SmartDashboard.putData("Gear Tilter Stop", new GearTilterStop());
		
		SmartDashboard.putData("Climb Stop", new ClimbStop());
		SmartDashboard.putData("Climb Reverse", new ClimbReverse());
		
		SmartDashboard.putData("Gear Squeeze N Tilt", new GearSqueezeNTilt());
		//SmartDashboard.putData("Camera For Gears", new CameraPositionForGears());
		//SmartDashboard.putData("Camera For Climb", new CameraPositionForClimb());
		}

	public Joystick getDriverControllerLeft() {
		return driverControllerLeft;
	}

	public static void setDriverControllerLeft(Joystick driverController) {
		OI.driverControllerLeft = driverController;
	}
	
	public Joystick getDriverControllerRight() {
		return driverControllerRight;
	}

	public static void setDriverControllerRight(Joystick driverController) {
		OI.driverControllerRight = driverController;
	}
	public static Joystick getCopilotXboxController() {
		return copilotXboxController;
	}

	public static void setCopilotXboxController(Joystick copilotXboxController) {
		OI.copilotXboxController = copilotXboxController;
	}

	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button .= new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
