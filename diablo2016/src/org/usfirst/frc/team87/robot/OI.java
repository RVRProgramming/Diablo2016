package org.usfirst.frc.team87.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import org.usfirst.frc.team87.robot.commands.ExampleCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	final int PLACEHOLDER = 0;

	static Joystick gamePad;
	static Joystick joyStick;
	
	/**
	 * Initializes the OI class and sets the controllers
	 */
	public OI(){
		
		gamePad = new Joystick(RobotMap.GAMEPAD);
		joyStick = new Joystick(RobotMap.EXTREME3D);
		
	}
	
	
	/** 
	 * Gets the values of the thumb sticks on the controller
	 * and drives the robot using tank drive
	 */
	public void drive(){
		double leftDrive = gamePad.getRawAxis(RobotMap.LEFT_THUMBSTICK);
		double rightDrive = gamePad.getRawAxis(RobotMap.RIGHT_THUMBSTICK);
		
		RobotMap.robotdrive.tankDrive(leftDrive, rightDrive);
		}
	
	/**
	 * Throws the ball really far 
	 */
	public void throwTheBallReallyFar(){
		
		if(gamePad.getRawButton(RobotMap.A_BUTTON)){
			RobotMap.leftPiston.set(true);
			RobotMap.rightPiston.set(true);
		} else {
			RobotMap.leftPiston.set(false);
			RobotMap.rightPiston.set(false);
		}
		
	}
	
}

