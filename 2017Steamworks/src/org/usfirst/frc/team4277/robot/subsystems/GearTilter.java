package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.PortMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class GearTilter extends Subsystem implements PortMap{

	private static final double UP_POSITION = 901.5;
			//326.6;
	private static final double UP_SLOW_SPEED_POSITION = UP_POSITION -10.0;
	private static final double PEG_POSITION = UP_POSITION - 5.2;
	private static final double DOWN_POSITION = 884.4;
	private static final double DOWN_SLOW_SPEED_POSITION = DOWN_POSITION +10.0;

	private Talon tilter = new Talon(TILTER_PWM);
	private AnalogPotentiometer pot = new AnalogPotentiometer(TILTER_ANALOG, 1000, 0);
	boolean goingUp = false;
	private boolean beforeSlowSpeedDown = false;
	private boolean beforeSlowSpeedUp = false;
	
	public GearTilter() {
		super();
		LiveWindow.addSensor("Tilter", "Tilter Pot", (AnalogPotentiometer) pot);
		LiveWindow.addActuator("Tilter", "Tilter Motor", (Talon) tilter);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void tiltUpFast() {
    	tilt(0.2, -1.0);
    }
    public void tiltUpSlow() {
    	tilt(0.1, -1.0);
    }
    public void tiltDownFast() {
    	tilt(0.2, 1.0);
    } 
    public void tiltDownSlow() {
    	tilt(0.1, 1.0);
    }
    
	public void tiltUpToPeg() {
		setGoingUp(true);
		if ( beforeSlowSpeedUp = true){
		tiltUpFast();
		}else {
		tiltUpSlow();
		}
	}
	
	
	public void tiltDownToPeg() {
		setGoingUp(true);

		if ( beforeSlowSpeedDown = true){
		tiltUpFast();
		}else {
		tiltUpSlow();		
		}
	}
    public void tiltToPeg() {
    	double currentPosition = getTilterPosition();
    	if (currentPosition > PEG_POSITION)
    		tiltUpToPeg();
    else
    		tiltDownToPeg();
   }
    
    public void tilt(double speed, double direction){
    	SmartDashboard.putString("Tilter position", Double.toString(getTilterPosition()));
    	SmartDashboard.putString("Tilter direction", Double.toString(direction));
    	System.out.println("Tilter position" + Double.toString(getTilterPosition()));
    	if (direction < 0) {
    		System.out.println("tiltUp at speed" + Double.toString(direction));
    	} else {
    		System.out.println("tiltDown at speed" + Double.toString(direction));
    	}
    	getTilter().set(speed * direction);
    }
    
    public void stop() {
    	System.out.println("tilter stop");
    	SmartDashboard.putString("Tilter position", Double.toString(getTilterPosition()));
    	getTilter().set(0);
    }
    
	
    public boolean isAtPickUpPosition() {
    	return getTilterPosition() < DOWN_POSITION;
    }
    public boolean isAtTopPosition() {
    	return getTilterPosition() > UP_POSITION;
    }
    
    public boolean isAtPegPosition() {
    	if (isGoingUp())
    		return  getTilterPosition() < PEG_POSITION;
    	else
    		return  getTilterPosition() < PEG_POSITION;
    } 
    
    
    public double getTilterPosition() {
    	return getPot().get();
    }

	public Talon getTilter() {
		return tilter;
	}

	public void setTilter(Talon tilter) {
		this.tilter = tilter;
	}

	public AnalogPotentiometer getPot() {
		return pot;
	}

	public void setPot(AnalogPotentiometer pot) {
		this.pot = pot;
	}

	public boolean beforeSlowSpeedDown() {
		return beforeSlowSpeedDown;
	}
	public boolean beforeSlowSpeedUp() {
		return beforeSlowSpeedUp;
	}
	public boolean isGoingUp() {
		return goingUp;
	}
	public void setGoingUp(boolean goingUp) {
		this.goingUp = goingUp;
	}
	public void setBeforeSlowSpeedUp(boolean beforeSlowSpeedUp) {
		this.beforeSlowSpeedUp = beforeSlowSpeedUp; 
	}
	public void setBeforeSlowSpeedDown(boolean beforeSlowSpeedDown) {
		this.beforeSlowSpeedDown = beforeSlowSpeedDown; 
	}
    
}

