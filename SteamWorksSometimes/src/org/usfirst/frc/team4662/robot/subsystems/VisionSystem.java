package org.usfirst.frc.team4662.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class VisionSystem extends Subsystem {
	private boolean m_bGearForward;
	
	public VisionSystem() {
		m_bGearForward = true;
		
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
 
    public void toggleEnds(){
    	m_bGearForward = !m_bGearForward;
    }
    
    public boolean isGearForward() {
    	return m_bGearForward;
    }
    
}

