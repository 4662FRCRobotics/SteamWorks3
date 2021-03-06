package org.usfirst.frc.team4662.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	
	//CAN
	public static int leftMotor1;
	public static int leftMotor2;
	public static int rightMotor1;
	public static int rightMotor2;
	
	//PWM
	public static int ropeMotor1;
	public static int ropeMotor2;
	
	public static int shooterMotor1;
	public static int gateServo;
	
	public static int loaderMotor;
		//not in init yet 
	
	public static void init(){
		//CAN
		leftMotor1 = 4;
		leftMotor2 = 5;
		rightMotor1 = 2;
		rightMotor2 = 3;
		
		//PWM
		ropeMotor1 = 0;
		ropeMotor2 = 1;
		
		shooterMotor1 = 2;
			//previously boilerLoaderMotor1
		gateServo = 3;
		
		loaderMotor = 4;
	}
	
}
