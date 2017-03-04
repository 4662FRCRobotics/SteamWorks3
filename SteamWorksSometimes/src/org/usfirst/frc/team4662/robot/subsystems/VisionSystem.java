package org.usfirst.frc.team4662.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionSystem extends Subsystem {
	private NetworkTable VisionTable;
	private boolean m_bGearForwardDrive;
	private boolean m_bGearForwardVision;
	private boolean m_bIsVisionOn;
	
	public VisionSystem() {
		VisionTable = NetworkTable.getTable("Vision");
		m_bGearForwardDrive = true;
		m_bGearForwardVision = true;
		m_bIsVisionOn = false;
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
 
    public void toggleEnds(){
    	m_bGearForwardDrive = !m_bGearForwardDrive;
       	m_bGearForwardVision = !m_bGearForwardVision;
    	VisionTable.putBoolean("IsGearDrive", m_bGearForwardVision);
    }
    
    public void cameraFix() {
    	m_bGearForwardDrive = !m_bGearForwardDrive;
    	VisionTable.putBoolean("IsGearDrive", m_bGearForwardVision);
    }
    
    public void GearCam(){
    	m_bGearForwardDrive = true;
    	m_bGearForwardVision = true;
    	VisionTable.putBoolean("IsGearDrive", m_bGearForwardVision);
    }
    
    public void ShooterCam(){
    	m_bGearForwardDrive = false;
    	m_bGearForwardVision = false;
    	VisionTable.putBoolean("IsGearDrive", m_bGearForwardVision);
    }
    
    public void isVisionOnToggle() {
    	m_bIsVisionOn = !m_bIsVisionOn;
    	SmartDashboard.putBoolean("IsVisionOn", m_bIsVisionOn);
    	VisionTable.putBoolean("IsVisionOn", m_bIsVisionOn);
    }
    
    public boolean isVisionOn() {
    	return m_bIsVisionOn;
    }
    
    public boolean isGearForwardDrive() {
    	return m_bGearForwardDrive;
    }
    
    public boolean isGearForwardVision() {
    	return m_bGearForwardVision;
    }
}

