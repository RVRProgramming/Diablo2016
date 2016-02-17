package org.usfirst.frc.team87.robot;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	final int PLACEHOLDER = 0;

	RobotDrive robotDrive;
	static Joystick gamePad;
	static Joystick joyStick;
	static Victor launchWheel;
	static Relay wheelRollLeft;
	static Relay wheelRollRight;
	static Relay stickRoll;
	static Accelerometer accel;
	static Ultrasonic ultrasonic;
	static Timer timer;
	static Encoder encoder;
	
	private double oldAcc = 0;
	private double newAcc = 0;
	private double oldVel = 0;
	private double newVel = 0;
	public int power = 100;
	public int holder = 0;
	/**
	 * Initializes the OI class and sets the controllers
	 */
	public OI(){
		
		robotDrive = new RobotDrive(RobotMap.FRONT_LEFT_WHEEL, RobotMap.BACK_LEFT_WHEEL, RobotMap.FRONT_RIGHT_WHEEL, RobotMap.BACK_RIGHT_WHEEL);
		gamePad = new Joystick(RobotMap.GAMEPAD);
		joyStick = new Joystick(RobotMap.EXTREME3D);
		launchWheel = new Victor(RobotMap.LAUNCH_WHEEL);
		wheelRollLeft = new Relay(RobotMap.WHEEL_ROLLERS_LEFT);
		wheelRollRight = new Relay(RobotMap.WHEEL_ROLLERS_RIGHT);
		stickRoll = new Relay(RobotMap.STICK_ROLLER);
		accel = new BuiltInAccelerometer(Accelerometer.Range.k4G);
		ultrasonic = new Ultrasonic(RobotMap.ULTRASONIC_PING, RobotMap.ULTRASONIC_ECHO);
		ultrasonic.setAutomaticMode(true);
		timer = new Timer();
		timer.reset();
		timer.start();
		encoder = new Encoder(RobotMap.ENCODER_A, RobotMap.ENCODER_B);
	}
	
	
	
	/** 
	 * Gets the values of the thumb sticks on the controller
	 * and drives the robot using tank drive
	 */
	public void drive(){
		double leftDrive = gamePad.getRawAxis(RobotMap.LEFT_THUMBSTICK);
		double rightDrive = gamePad.getRawAxis(RobotMap.RIGHT_THUMBSTICK);
		
		robotDrive.tankDrive(leftDrive, rightDrive);
		}
		
	/**
	 * Activates when the the joystick trigger is pulled, sets the power of the launch wheel
	 * to the percent of the slider
	 */
	public void spinUp(int power){
		if(joyStick.getRawButton(RobotMap.THUMB_BUTTON) == true){
			launchWheel.set(power / 100.0);
		} else{
			launchWheel.set(0);
		}
	}
	
	public double getSpinSpeed(){
		return encoder.getRate();
	}
	
	/**
	 * returns a value depending on the button pressed on the joystick
	 * @return
	 */
	

	public int getLaunchPower(){
		

		if(joyStick.getRawButton(RobotMap.JOY_5) && power >=5){
			if(holder==0){
				power = power - 5;
				holder = 1;
			}
		}  else if(joyStick.getRawButton(RobotMap.JOY_6)&& power <=95){
			if(holder==0){
				power = power + 5;
				holder = 1;
			}  
		}else if (joyStick.getRawButton(RobotMap.JOY_9)){
			power = 100;
			holder = 0;
		}else if (joyStick.getRawButton(RobotMap.JOY_10)){
			power = 50;
			holder = 0;
		}else{
			power = power;
			holder=0;
		}
		return power;
		}
	
	/**
	 * Activates all wheels and rollers to push the ball onto the launch wheel
	 * or push ball out of intake
	 */
	public void intake(){
		if(joyStick.getRawButton(RobotMap.TRIGGER) == true){
			wheelRollLeft.set(Relay.Value.kForward);
			wheelRollRight.set(Relay.Value.kForward);
			
		} else if(joyStick.getRawButton(RobotMap.JOY_4) == true){
			wheelRollLeft.set(Relay.Value.kReverse);
			wheelRollRight.set(Relay.Value.kReverse);
		} 
		else{
			wheelRollLeft.set(Relay.Value.kOff);
			wheelRollRight.set(Relay.Value.kOff);
			
		}	
	}
public void roller(){
	 if ( joyStick.getRawButton(RobotMap.JOY_12) == true){
		stickRoll.set(Relay.Value.kReverse);
	} else if ( joyStick.getRawButton(RobotMap.JOY_3) == true){
		stickRoll.set(Relay.Value.kForward);
	} else {
		stickRoll.set(Relay.Value.kOff);
	}
}
	
	/**
	 * Gets the acceleration of the x value of the roborio's built in accelerometer
	 */
	public double getXAcceleration(){
		return Math.round(accel.getX() * 1000.0) / 1000.0;	
	}
	
	/**
	 * Returns x acceleration as a string
	 * @return
	 */
	public String getAccelerationString(){
		return Double.toString(getXAcceleration());
	}

	
double velocity = 0.0;
double secondsPerSample = 0.1;
	/**
	 * Attempts to calculate velocity from the acceleration
	 * @return
	 */
	public double getXVelocity(){
		if(timer.get() > secondsPerSample){
			velocity += getXAcceleration() * secondsPerSample;
			timer.reset();
			return (Math.round(velocity * 10000.0) / 10000.0);
		} else{
			return velocity;
		}
	}

	/**
	 * Returns velocity as a string
	 * @return
	 */
	public String getXVelocityString(){
		return Double.toString(getXVelocity());
	}
	
	
	public double getDistanceMeters(){
		return Math.round((ultrasonic.getRangeMM() / 1000.0) * 1000 )/ 1000.0;
	}
	
	public double getDistanceInches(){
		return Math.round(ultrasonic.getRangeInches() * 100.0) / 100.0;
	}
}

