
package org.usfirst.frc.team4277.robot;

import org.usfirst.frc.team4277.robot.commands.AutoDriveForward;
import org.usfirst.frc.team4277.robot.commands.AutoDriveForwardToObstacle;
import org.usfirst.frc.team4277.robot.commands.AutoGearDeploy;
import org.usfirst.frc.team4277.robot.commands.DriveBackwardTimed;
import org.usfirst.frc.team4277.robot.commands.DriveForwardTimed;
import org.usfirst.frc.team4277.robot.subsystems.CamPan;
import org.usfirst.frc.team4277.robot.subsystems.Climber;
import org.usfirst.frc.team4277.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4277.robot.subsystems.GearSqueezer;
import org.usfirst.frc.team4277.robot.subsystems.GearTilter;
import org.usfirst.frc.team4277.robot.PortMap;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot implements PortMap{

	// Subsystems //
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final GearSqueezer gearSqueezer = new GearSqueezer();
	public static final GearTilter gearTilter = new GearTilter();
	public static final Climber climber = new Climber();
	public static final CamPan camPan = new CamPan();
	//public static final GearTilterPID gearTilterPID = new GearTilterPID();

	// OI //
	public static OI oi;
	// Autonomous //
	Command autonomousCommand;
	SendableChooser<Command> chooser;
	public UsbCamera cameraFront;
	public UsbCamera cameraBack;
	
	/**
	 * 
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		oi = new OI();
		
		chooser = new SendableChooser<>();
		chooser.addDefault("Drive forward timed", new DriveForwardTimed());
		chooser.addObject("Drive backward timed", new DriveBackwardTimed());
		chooser.addObject("Drive forward distance", new AutoDriveForward());
		chooser.addObject("Drive forward to obstacle", new AutoDriveForwardToObstacle());
		chooser.addObject("Gear deploy", new AutoGearDeploy());
		SmartDashboard.putData("Auto mode chooser", chooser);
		
		cameraFront = CameraServer.getInstance().startAutomaticCapture(0);
		cameraBack = CameraServer.getInstance().startAutomaticCapture(1);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = (Command) chooser.getSelected();
		autonomousCommand.start();

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();

	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
		LiveWindow.setEnabled(true);
	}
	
	@Override
	public void testInit() {
		LiveWindow.run();
	}
}
