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
	static final int GAMEPAD = 0;
	static final int EXTREME3D = 1;
	//Map for buttons and axiseseses on joystick
	static final int TRIGGER = 1;
	static final int THUMB_BUTTON = 2;
	static final int JOY_3 = 3;
	static final int JOY_4 = 4;
	static final int JOY_5 = 5;
	static final int JOY_6 = 6;
	static final int JOY_7 = 7;
	static final int JOY_8 = 8;
	static final int JOY_9 = 9;
	static final int JOY_10 = 10;
	static final int JOY_11 = 11;
	static final int JOY_12 = 12;
	
	static final int SLIDER = 3;
	
	//Map for buttons on gamepad
	static final int LEFT_THUMBSTICK = 1;
	static final int RIGHT_THUMBSTICK = 5;
	static final int A_BUTTON = 1;
	
	//Motors
	static final int FRONT_LEFT_WHEEL = 2;
	static final int BACK_LEFT_WHEEL = 3;
	static final int FRONT_RIGHT_WHEEL = 4;
	static final int BACK_RIGHT_WHEEL = 5;
	
	static final int LAUNCH_WHEEL = 0;
	static final int WHEEL_ROLLERS_LEFT = 0;
	static final int WHEEL_ROLLERS_RIGHT = 2;
	static final int STICK_ROLLER = 1;
	
	//Sensors
	static final int ULTRASONIC_PING = 0;
	static final int ULTRASONIC_ECHO = 1;
	static final int ENCODER_A = PLACEHOLDER + 3;
	static final int ENCODER_B = PLACEHOLDER + 4;
}
