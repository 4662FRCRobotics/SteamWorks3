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
	
	private double r1PixelX;
	private double r1PixelY;
	private double r1PixelW;
	private double r1PixelH;
	private boolean m_bIsTargetFound;
	
	public VisionSystem() {
		VisionTable = NetworkTable.getTable("Vision");
		m_bGearForwardDrive = true;
		m_bGearForwardVision = true;
		m_bIsVisionOn = false;
		
		r1PixelX = -2;
		r1PixelY = -2;
		r1PixelW = -2;
		r1PixelH = -2;
		m_bIsTargetFound = true;
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
    
    public void VisionNTDisplay() {
    	m_bIsTargetFound = VisionTable.getBoolean("IsTargetFound", m_bIsTargetFound);
    	r1PixelX = VisionTable.getNumber("Target1X", r1PixelX);
    	r1PixelY = VisionTable.getNumber("Target1Y", r1PixelY);
    	r1PixelW = VisionTable.getNumber("Target1Width", r1PixelW);
    	r1PixelH = VisionTable.getNumber("Target1Height", r1PixelH);
    	    	
    	SmartDashboard.putBoolean("IsTargetFound", m_bIsTargetFound);
    	SmartDashboard.putNumber("Target1X", r1PixelX);
    	SmartDashboard.putNumber("Target1Y", r1PixelY);
    	SmartDashboard.putNumber("Target1Width", r1PixelW);
    	SmartDashboard.putNumber("Target1Height", r1PixelH);
    }
    
    public void takePic() {
    	VisionTable.putBoolean("Take Pic", true);
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

