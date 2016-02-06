package org.usfirst.frc.team87.robot;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Joystick;
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
	static Relay wheelRoll;
	static Relay stickRoll;
	static Accelerometer accel;
	static Ultrasonic ultrasonic;
	static Timer timer;
	
	private double oldAcc = 0;
	private double newAcc = 0;
	private double oldVel = 0;
	private double newVel = 0;
	/**
	 * Initializes the OI class and sets the controllers
	 */
	public OI(){
		
		robotDrive = new RobotDrive(RobotMap.FRONT_LEFT_WHEEL, RobotMap.BACK_LEFT_WHEEL, RobotMap.FRONT_RIGHT_WHEEL, RobotMap.BACK_RIGHT_WHEEL);
		gamePad = new Joystick(RobotMap.GAMEPAD);
		joyStick = new Joystick(RobotMap.EXTREME3D);
		launchWheel = new Victor(RobotMap.LAUNCH_WHEEL);
		wheelRoll = new Relay(RobotMap.WHEEL_ROLLERS);
		stickRoll = new Relay(RobotMap.STICK_ROLLER);
		accel = new BuiltInAccelerometer(Accelerometer.Range.k4G);
		ultrasonic = new Ultrasonic(RobotMap.ULTRASONIC_PING, RobotMap.ULTRASONIC_ECHO);
		ultrasonic.setAutomaticMode(true);
		timer = new Timer();
		timer.reset();
		timer.start();
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
	public void spinUp(){
		if(joyStick.getRawButton(RobotMap.TRIGGER) == true){
			double launchPower = (joyStick.getRawAxis(RobotMap.SLIDER) - 1) / 2;
			launchWheel.set(launchPower);//ERiCH MASS IS THE BEST PROGRFAMMERFDING LEAD EAVVAE
		} else{
			launchWheel.set(0);
		}
	}
	
	/**
	 * Activates all wheels and rollers to push the ball onto the launch wheel
	 */
	public void launch(){
		if(joyStick.getRawButton(RobotMap.THUMB_BUTTON) == true){
			wheelRoll.set(Relay.Value.kForward);
			stickRoll.set(Relay.Value.kReverse);
		} else{
			wheelRoll.set(Relay.Value.kOff);
			stickRoll.set(Relay.Value.kOff);
		}	
	}
	
	/**
	 * Returns value of launch power in a string format
	 */
	public String launchPower(){
		double slider = (joyStick.getRawAxis(RobotMap.SLIDER) - 1) / 2;
		int percent = (int) (slider * -100);
		return Integer.toString(percent);
	}
	
	/**
	 * Gets the acceleration of the x value of the roborio's built in accelerometer
	 */
	public double getXAcceleration(){
		if(accel.getX() < 0.05 && accel.getX() > -0.05){
			System.out.println(0);
			
			
			return 0;
		} else{
			System.out.println(Math.round(accel.getX() * 1000.0) / 1000.0);
			return Math.round(accel.getX() * 10000.0) / 10000.0;
		}	
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
		return (double)ultrasonic.getRangeMM() / 1000.0;
	}
	public int autoMode(){
		int mode = 0;
		//if(SmartDashboard.getBoolean("DB/Button 0") == true){
		//	mode += 1;
		//}
		if(SmartDashboard.getBoolean("DB/Button 1") == true){
			mode += 1;
		}
		if(SmartDashboard.getBoolean("DB/Button 2") == true){
			mode += 2;
		}
		if(SmartDashboard.getBoolean("DB/Button 3") == true){
			mode += 4;
		}
		return mode;
	}

}

