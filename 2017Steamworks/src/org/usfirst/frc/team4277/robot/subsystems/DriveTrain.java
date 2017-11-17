package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.PortMap;
import org.usfirst.frc.team4277.robot.Robot;
import org.usfirst.frc.team4277.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */
public class DriveTrain extends Subsystem implements PortMap {

	// Subsystem devices
	private SpeedController frontLeftMotor = new Talon(LEFT_FRONT_DRIVE_PWM);
	private SpeedController frontRightMotor = new Talon(RIGHT_FRONT_DRIVE_PWM);
	private SpeedController rearLeftMotor = new Talon(LEFT_BACK_DRIVE_PWM);
	private SpeedController rearRightMotor = new Talon(RIGHT_BACK_DRIVE_PWM);

	private RobotDrive drive = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
	private Encoder leftEncoder = new Encoder(LEFT_ENCODER_A, LEFT_ENCODER_B, true, Encoder.EncodingType.k4X);
	private Encoder rightEncoder = new Encoder(RIGHT_ENCODER_A, RIGHT_ENCODER_B, false, Encoder.EncodingType.k4X);
	private AnalogInput rangefinder = new AnalogInput(2);
	private AnalogGyro gyro = new AnalogGyro(1);
	
	public DriveTrain() {
		super();

		// Encoders may measure differently in the real world and in
		// simulation. In this example the robot moves 0.042 barleycorns
		// per tick in the real world, but the simulated encoders
		// simulate 360 tick encoders. This if statement allows for the
		// real robot to handle this difference in devices.
	if (Robot.isReal()) {
			leftEncoder.setDistancePerPulse(0.042);
			rightEncoder.setDistancePerPulse(0.042);

		} else {
			// Circumference in ft = 4in/12(in/ft)*PI
			leftEncoder.setDistancePerPulse((4.0 / 12.0 * Math.PI) / 360.0);
			rightEncoder.setDistancePerPulse((4.0 / 12.0 * Math.PI) / 360.0);
		}

		// Let's show everything on the LiveWindow
		LiveWindow.addActuator("Drive Train", "Front_Left Motor", (Talon) frontLeftMotor);
		LiveWindow.addActuator("Drive Train", "Back Left Motor", (Talon) rearLeftMotor);
		LiveWindow.addActuator("Drive Train", "Front Right Motor", (Talon) frontRightMotor);
		LiveWindow.addActuator("Drive Train", "Back Right Motor", (Talon) rearRightMotor);
		LiveWindow.addSensor("Drive Train", "Left Encoder", leftEncoder);
		LiveWindow.addSensor("Drive Train", "Right Encoder", rightEncoder);
		LiveWindow.addSensor("Drive Train", "Rangefinder", rangefinder);
		LiveWindow.addSensor("Drive Train", "Gyro", (AnalogGyro) gyro);
	}

	/**
	 * When no other command is running let the operator drive around using the
	 * xbox controller joysticks.
	 */
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	public void log() {
		SmartDashboard.putNumber("Left Distance", leftEncoder.getDistance());
		SmartDashboard.putNumber("Right Distance", rightEncoder.getDistance());
		SmartDashboard.putNumber("Left Speed", leftEncoder.getRate());
		SmartDashboard.putNumber("Right Speed", rightEncoder.getRate());
		SmartDashboard.putNumber("Gyro", gyro.getAngle());
	}

	/**
	 * Tank style driving for the DriveTrain.
	 * 
	 * @param left
	 *            Speed in range [-1,1]
	 * @param right
	 *            Speed in range [-1,1]
	 */
	public void drive(double left, double right) {
		drive.tankDrive(left, right);
		
	}
	/**
	 * @param joy
	 *            The xbox style joystick to use to drive tank style.
	 */
	public void drive(Joystick ControllerTwo , Joystick ControllerOne) {
	    boolean sensitivityLock = false;
	    double sensitivity = 0.0;
	    boolean prevBtn7 = false;
	    double Controller1= ControllerOne.getY();
	    double Controller2= ControllerTwo.getY();
	    
		double rightInput1 = Math.pow((( Controller1* 1.7* Math.abs(0.95*Controller1))/2),2)+0.35;
		double leftInput1 = Math.pow(( Controller2* 1.7* Math.abs(0.95*Controller2))/2,2)+0.35;
		
	    double rightInput3= rightInput1*1.0;
	    double leftInput3= leftInput1*1.0;
	    
	    if (Controller1 < 0.0 ){
        	rightInput3 = rightInput1 * (-1.0);
        }
        if (Math.abs(rightInput3) < 0.36){
        	rightInput3= 0;
        	
        }
        
        if (Controller2 < 0.0 ){
        	leftInput3 = leftInput1 * (-1.0);
        }
        if (Math.abs(leftInput3) <0.36){
        	leftInput3= 0;
        }
        
		drive.tankDrive(leftInput3 * -1.0, rightInput3 * -1.0);
				
        	  if (!sensitivityLock) {
                  sensitivity = -0.5 * (ControllerOne.getZ() - 1);
          	if (prevBtn7 != ControllerOne.getRawButton(7)) {
                  sensitivityLock = ControllerOne.getRawButton(7);
          	}
        }
        
        }

        




	public void reversedrive(Joystick ControllerTwo , Joystick ControllerOne) {
	    boolean sensitivityLock = false;
	    double sensitivity = 0.0;
	    boolean prevBtn7 = false;

	    
		double leftInput1 = ControllerOne.getY();
		double rightInput1 = ControllerTwo.getY();
		drive.tankDrive(Math.pow(leftInput1, 3), Math.pow(rightInput1, 3));
	    if (!sensitivityLock) {
            sensitivity = -0.5 * (ControllerOne.getZ() - 1);
        }
	    if (prevBtn7 != ControllerOne.getRawButton(7)) {
            sensitivityLock = ControllerOne.getRawButton(7);
        }

	}
	
	/**
	 * @return The distance driven (average of left and right encoders).
	 */
	public double getDistance() {
		return (leftEncoder.getDistance() + rightEncoder.getDistance())/2;
	}
	
//	/**
//	 * @return The robots heading in degrees.
//	 */
	public double getHeading() {
		return gyro.getAngle();
	}

	public void stop() {
		drive.tankDrive(0, 0);
	}
	
	public void driveForwardAtPower(double power) {
		drive.tankDrive(power,1.08*power);
	}
	public void driveBackwardAtPower(double power) {
		drive.tankDrive(0.98*power,power);
	}

/**
* Reset the robots sensors to the zero states.
*/
	public void reset() {
		gyro.reset();
		leftEncoder.reset();
		rightEncoder.reset();
	}

/**
 @return The distance to the obstacle detected by the rangefinder.
  */
	
	public boolean isAtStopPosition() {
		return getDistanceToObstacle() < 12;
	}
	public double getDistanceToObstacle() {
		// Really meters in simulation since it's a rangefinder
		return rangefinder.getAverageVoltage();
	}
}
