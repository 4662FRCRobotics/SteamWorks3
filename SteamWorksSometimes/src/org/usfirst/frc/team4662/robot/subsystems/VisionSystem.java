package org.usfirst.frc.team4662.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionSystem extends Subsystem {
	private NetworkTable VisionTable;
	private boolean m_bGearForward;
	private boolean m_bIsVisionOn = false;
	
	public VisionSystem() {
		VisionTable = NetworkTable.getTable("Vision");
		m_bGearForward = true;
		m_bIsVisionOn = true;
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
 
    public void toggleEnds(){
    	m_bGearForward = !m_bGearForward;
    	VisionTable.putBoolean("IsGearDrive", m_bGearForward);
    }
    
    public void isVisionOnToggle() {
    	m_bIsVisionOn = !m_bIsVisionOn;
    	SmartDashboard.putBoolean("IsVisionOn", m_bIsVisionOn);
    	VisionTable.putBoolean("IsVisionOn", m_bIsVisionOn);
    }
    
    public boolean isVisionOn() {
    	
    	return m_bIsVisionOn;
    }
    
    public boolean isGearForward() {
    	return m_bGearForward;
    }
    
}

