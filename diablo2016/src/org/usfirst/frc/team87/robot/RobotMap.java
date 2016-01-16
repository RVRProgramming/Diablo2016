package org.usfirst.frc.team87.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	static final int PLACEHOLDER = 0;
	static final int GAMEPAD = PLACEHOLDER;
	static final int EXTREME3D = PLACEHOLDER;
	
	//Map for buttons on joystick
	static final int TRIGGER = PLACEHOLDER;
	
	//Map for buttons on gamepad
	static final int LEFT_THUMBSTICK = PLACEHOLDER;
	static final int RIGHT_THUMBSTICK = PLACEHOLDER;
	static final int A_BUTTON = PLACEHOLDER;
	
	static final int LEFT_PISTON = PLACEHOLDER;
	static final int RIGHT_PISTON = PLACEHOLDER;
	
	
	public static RobotDrive robotdrive = new RobotDrive(PLACEHOLDER, PLACEHOLDER, PLACEHOLDER, PLACEHOLDER);
	public static Solenoid leftPiston = new Solenoid(LEFT_PISTON);
	public static Solenoid rightPiston = new Solenoid(RIGHT_PISTON);
	
}
