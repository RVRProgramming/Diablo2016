package org.usfirst.frc.team87.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team87.robot.subsystems.ExampleSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	public static Timer timer;
	int power = 100;
	
    SendableChooser startLocationChooser;
    SendableChooser barrierSelector;
    //SendableChooser startLocationChooser;
    //SendableChooser endLocationChooser;
    //SendableChooser barrierChooser;
    //AutonomousStepOne auto1;
    
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();

		this.startLocationChooser = new SendableChooser();
		this.startLocationChooser.addDefault("Position 1", 1);
		this.startLocationChooser.addObject("Position 2", 2);
		this.startLocationChooser.addObject("Position 3", 3);
		this.startLocationChooser.addObject("Position 4", 4);
		SmartDashboard.putData("Autonomous Start Location", this.startLocationChooser);
		
		this.barrierSelector = new SendableChooser();
		this.barrierSelector.addDefault("Barrier 1", 1);
		this.barrierSelector.addObject("Barrier 2", 2);
		this.barrierSelector.addObject("Barrier 3", 3);
		this.barrierSelector.addObject("Barrier 4", 4);
		this.barrierSelector.addObject("Barrier 5", 5);
		SmartDashboard.putData("Autonomous Barrier Selection", this.barrierSelector);		
    }
  
    
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	/**
	 * This function is called periodically while the robot is in Disabled mode
	 */
    
    
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
		if(oi.getLaunchPower() != 0){
    		this.power = oi.getLaunchPower();
    	}
		
		SmartDashboard.putString("Launch Power", Integer.toString(this.power));
        SmartDashboard.putString("Acceleration", oi.getAccelerationString());
        SmartDashboard.putString("Ultrasonic Sensor", Double.toString(oi.getDistanceInches()));

	}

	
	
	
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */

	public void autonomousInit() {
		
	}
	
    
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    //    Scheduler.getInstance().run();
    	//Debugging Information
    	SmartDashboard.putNumber("Start Location", (int) this.startLocationChooser.getSelected());
    	SmartDashboard.putNumber("Barrier Selection", (int) this.barrierSelector.getSelected());
    
    	//Modular autonomous system
    	int startLocation = (int) this.startLocationChooser.getSelected();
		int barrierSelection = (int) this.barrierSelector.getSelected();
		//Begin Starting Location Modularity
			if(startLocation == 1){
				//Do stuff for location 1
			}
			else if(startLocation == 2){
				//Do stuff for location 2
			}	
			else if(startLocation == 3){
				//Do stuff for location 3
			}	
			else if(startLocation == 4){
				//Do stuff for location 4
			}
			else{
				//Oops
			}
		//End Starting Location Modularity
			
		//Begin Barrier Selection Modularity
			if(barrierSelection == 1){
				//Do stuff for barrier 1
			}	
			else if(barrierSelection == 2){
				//Do stuff for barrier 2
			}	
			else if(barrierSelection == 3){
				//Do stuff for barrier 3
			}	
			else if(barrierSelection == 4){
				//Do stuff for barrier 4
			}
			else if(barrierSelection == 5){
				//Do stuff for barrier 5
			}
			else{
				//Oops
			}		
			//End Barrier Selection Modularity    
    }

    
    
    
    public void teleopInit() {
	// This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to 
    // continue until interrupted by another command, remove
    // this line or comment it out.
        //if (autonomousCommand != null) autonomousCommand.cancel();
    }

    
    
    
    /**
     * This function is called periodically during operator control
     */
    
    public void teleopPeriodic() {
    	Scheduler.getInstance().run();
    	
    	oi.drive();
    	
    	//Only changes the power of the launch wheel if a joystick button is being pressed
    	if(oi.getLaunchPower() != 0){
    		this.power = oi.getLaunchPower();  //power is defined at the beginning of the class
    	}
    	
    	
    	
    	oi.spinUp(this.power);
    	oi.intake();
    	oi.roller();
    	SmartDashboard.putString("Launch Power", Integer.toString(this.power));
        SmartDashboard.putString("Acceleration", oi.getAccelerationString());
        SmartDashboard.putString("Ultrasonic Sensor", Double.toString(oi.getDistanceInches()));
        SmartDashboard.putString("Launch Speed", Double.toString(oi.getSpinSpeed()));
        
       // SmartDashboard.putData("test", new TEST());
        //SmartDashboard.putString("Auto Mode", Integer.toString(oi.autoMode()));
    }
    
    
    
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
           
    }
}
