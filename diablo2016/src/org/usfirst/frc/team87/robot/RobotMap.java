package org.usfirst.frc.team87.robot;

import edu.wpi.first.wpilibj.RobotDrive;

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
	
	static final int LEFT_THUMBSTICK = PLACEHOLDER;
	static final int RIGHT_THUMBSTICK = PLACEHOLDER;
	
	public static RobotDrive robotdrive;
	
	public RobotMap(){
		
		robotdrive = new RobotDrive(PLACEHOLDER, PLACEHOLDER, PLACEHOLDER, PLACEHOLDER);
		
	}
	
}
