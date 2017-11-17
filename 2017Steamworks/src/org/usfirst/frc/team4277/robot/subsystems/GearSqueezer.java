package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.PortMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearSqueezer extends Subsystem implements PortMap {

	public static final double IN_POINT = 33;
	public static final double MID_POINT = 0;
	public static final double OUT_POINT = 4.25;

	private Talon squeezer = new Talon(SQUEEZER_PWM);
	private Encoder squeezerEncoder = new Encoder(SQUEEZER_ONE_IO, SQUEEZER_TWO_IO, false, Encoder.EncodingType.k4X);
	private DigitalInput zero = new  DigitalInput(SQUEEZER_ZEROER_IO);
	
	private boolean released = false;
	private boolean squeezed = false;
	

	public GearSqueezer() {
		super();
		LiveWindow.addSensor("Squeezer", "Encoder", (Encoder) squeezerEncoder);
		LiveWindow.addActuator("Squeezer", "Motor", (Talon) squeezer);
		LiveWindow.addSensor("Squeezer", "Zeroer", (DigitalInput) zero);
		
	}

	public void initDefaultCommand() {
	}
	
	public void log(String direction, double speed) {
		System.out.println(direction+ " at speed:" + Double.toString(speed));
		System.out.println(direction+ " squeezer position:" + Double.toString(getSqueezePosition()));
		System.out.println(direction+ " release:" + released);
		System.out.println(direction+ " squeeze:" + squeezed);
		System.out.println(direction+ " zero:" + isAtZero());
		SmartDashboard.putBoolean(direction+ "  zero", isAtZero());
		SmartDashboard.putString(direction+ " position", Double.toString(getSqueezePosition()));
	}
	
	public void protectedSqueeze() {
		System.out.println("squeeze please");
		if (!isSqueezed()) {
		System.out.println("here's your check sir");
			squeeze();
		System.out.println("post Squeezed");
		}
	}

	public void protectedRelease() {
		System.out.println("release please");
		if (!isReleased()) {
			System.out.println("here's your release sir");
			release();
			System.out.println("post release");
		}
	}
	
	public void squeeze() {
		squeezeAtSpeed(0.15);
	}
	
	public void slowSqueeze() {
		setReleased(false);
		setSqueezed(false);
		squeezeAtSpeed(0.15);
	}

	public void release() {
		releaseAtSpeed(0.15);
	}
	
	public void slowRelease() {
		setReleased(false);
		setSqueezed(false);
		releaseAtSpeed(0.15);
	}
	
	public void squeezeAtSpeed(double speed) {
		runAtSpeed("squeeze", -speed);
	}
	public void completeSqueeze() {
		setReleased(false);
		setSqueezed(true);
		log("complete squeeze", 0);
	}
	
	public void releaseAtSpeed(double speed) {
		runAtSpeed("release", speed);
	}
	public void completeRelease() {
		setReleased(true);
		setSqueezed(false);
		log("complete release", 0);
	}
	
	private void runAtSpeed(String command, double speed){
		log(command, speed);
		getSqueezer().set(speed);
	}

	public void stop() {
		runAtSpeed("stop", 0);
	}

	public boolean isAtSqueezerPosition() {
		System.out.println("squeezer position" + Double.toString(getSqueezePosition()));
		return getSqueezePosition() > IN_POINT;
	}

	public boolean isAtReleasePosition() {
		System.out.println("release position" + Double.toString(getSqueezePosition()));
		//The out position is a negative number
		return getSqueezePosition() < -OUT_POINT;
	}

	public void reset() {
		squeezerEncoder.reset();
		System.out.println("reset");
		SmartDashboard.putString("Squeezer position", Double.toString(getSqueezePosition()));
	}

	public double getSqueezePosition() {
		return getSqueezerEncoder().getDistance();
	}

	public Talon getSqueezer() {
		return squeezer;
	}

	public void setSqueezer(Talon squeezer) {
		this.squeezer = squeezer;
	}

	public Encoder getSqueezerEncoder() {
		return squeezerEncoder;
	}

	public void setSqueezerEncoder(Encoder squeezerEncoder) {
		this.squeezerEncoder = squeezerEncoder;
	}

	public boolean isReleased() {
		return released;
	}

	public void setReleased(boolean released) {
		this.released = released;
		System.out.println("Checking...");

	}

	public boolean isSqueezed() {
		return squeezed;
	}

	public void setSqueezed(boolean squeeze) {
		this.squeezed = squeeze;
		System.out.println("Checking...");

	}
			
	public boolean isAtZero() {
		return !zero.get();
	}
}


