package org.usfirst.frc.team4662.robot.subsystems;

import org.usfirst.frc.team4662.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RopeSystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private SpeedController Controller1;
	private SpeedController Controller2;
	
	private double m_dClimbSpeed;
	
	public RopeSystem() {
		
		Controller1 = new VictorSP(RobotMap.ropeMotor1);
		Controller2 = new VictorSP(RobotMap.ropeMotor2);
		
		m_dClimbSpeed = -
				0.5;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void ropeDrive(double speed) {
    	
    	Controller1.set(speed);
    	Controller2.set(speed);
    	
    	logDashboard();
    }
    
    public void ropeClimb() {
    	ropeDrive(m_dClimbSpeed);
    }
    
    public void ropeStop() {
    	ropeDrive(0);
    }
    
    public double ropeSpeed() {
    	return Controller1.get();
    }
    
    @SuppressWarnings("deprecation")
	public void logDashboard() {
    	SmartDashboard.putNumber("ClimbSpeed", ropeSpeed());
    }
    
}

