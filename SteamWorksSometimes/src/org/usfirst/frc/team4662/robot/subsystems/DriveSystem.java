package org.usfirst.frc.team4662.robot.subsystems;

import org.usfirst.frc.team4662.robot.RobotMap;
import org.usfirst.frc.team4662.robot.commands.ArcadeDrive;
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 *
 */
public class DriveSystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	
	
	private CANTalon ControllerLeft1;
	private CANTalon ControllerLeft2;
	private CANTalon ControllerRight1;
	private CANTalon ControllerRight2;
	
	private Boolean m_bThrottleSwitch;
	
	private RobotDrive steamDrive; //makes the drive
	
	private double m_dThrottleDirection;
	private PIDController driveDistance;
	
	private double m_dWheelDiameter;
	
	private double m_dDriveP;
	private double m_dDriveI;
	private double m_dDriveD;
	
	private int m_iDriveError;
	
	public DriveSystem(){
		
		ControllerLeft1 = new CANTalon(RobotMap.leftMotor1);
		ControllerLeft2 = new CANTalon(RobotMap.leftMotor2);
		ControllerLeft1.setInverted(false);
		ControllerRight1 = new CANTalon(RobotMap.rightMotor1);
		ControllerRight2 = new CANTalon(RobotMap.rightMotor2);
		ControllerRight1.setInverted(true);
		
		ControllerLeft2.changeControlMode(CANTalon.TalonControlMode.Follower); //makes the controllers followers
		ControllerRight2.changeControlMode(CANTalon.TalonControlMode.Follower);
		
		ControllerLeft2.set(ControllerLeft1.getDeviceID()); //tells them who to follow
		ControllerRight2.set(ControllerRight1.getDeviceID());
		
		ControllerLeft1.configNominalOutputVoltage(+0f, -0f);
		ControllerRight1.configNominalOutputVoltage(+0f, -0f);
		
		ControllerLeft1.configPeakOutputVoltage(+4f, -4f);
		ControllerRight1.configPeakOutputVoltage(+4f, -4f);
		
		ControllerRight1.configEncoderCodesPerRev(2048);
		
		ControllerLeft1.enable();
		ControllerRight1.enable();
		
		
		steamDrive = new RobotDrive(ControllerLeft1, ControllerRight1);
		
//		SmartDashboard.putString("DriveSytem", "ConstructorMethod");
	    
		driveDistance = new PIDController(0.7, 0.0, 0.0, new EncoderWrapper(), new DriveDistancePID());
		m_dThrottleDirection = 1;
		m_bThrottleSwitch = false;
		
		m_dWheelDiameter = 7.75;
		m_dDriveP = 0.7;
		m_dDriveI = 0.0;
		m_dDriveD = 0.0;
		m_iDriveError = 50;
	}
	
	public void ArcadeDrive(double stickX, double stickY){
		stickY = stickY * m_dThrottleDirection;
//		stickX = stickX * m_dThrottleDirection; tried this and it is backwards when reversed 
		steamDrive.arcadeDrive(stickX, stickY);		
		
		logDashboard(stickY, stickX);
	}
	
	public void ToggleEnds()  {
// this still needs better safety code 
// turning the drive off will take time to spin down
// how about putting a check for rotation speed by an encoder and only allowing the switch once it's below a threshold
// the command will execute until the direction is toggled
// need to add an indicator (boolean) that is false unless the toggle is done
// then a method to return the boolean to the toggle command
		ArcadeDrive(0,0);
		m_bThrottleSwitch = false;
		if ( ControllerRight1.getSpeed() > -5 && ControllerRight1.getSpeed() < 5 ) {
			m_dThrottleDirection = m_dThrottleDirection * -1;
			m_bThrottleSwitch = true;
		}
	}
	
	public boolean isToggled()  {
		return m_bThrottleSwitch;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ArcadeDrive());
    }
    
    public void initEncoder (double distance){
    	
    	driveDistance.reset();
    	double rotations = distance / m_dWheelDiameter * Math.PI;
    	ControllerRight1.setAllowableClosedLoopErr(m_iDriveError);
    	ControllerRight1.setPosition(0.0);
    	driveDistance.setInputRange(-rotations * 1.5, rotations * 1.5);
    	driveDistance.setOutputRange(-1.0, 1.0);
    	driveDistance.setAbsoluteTolerance(5.0);
    	driveDistance.setSetpoint(rotations);
    	driveDistance.enable();
    	
    }
    
    public void logDashboard (double Y, double X){
    	
    	SmartDashboard.putNumber("DriverStickY", Y);
    	SmartDashboard.putNumber("DriverStickX", X);
    	SmartDashboard.putNumber("Left1Temp", ControllerLeft1.getTemperature());
    	SmartDashboard.putNumber("Left1Amps", ControllerLeft1.getOutputCurrent());
    	SmartDashboard.putNumber("Left1VFactor", ControllerLeft1.getOutputVoltage()/ControllerLeft1.getBusVoltage());
    	SmartDashboard.putNumber("LeftVBus", ControllerLeft1.getBusVoltage());
    	SmartDashboard.putNumber("LeftVOut", ControllerLeft1.getOutputVoltage());
    	
    	
    	SmartDashboard.putNumber("Right1Temp", ControllerRight1.getTemperature());
    	SmartDashboard.putNumber("Right1Amps", ControllerRight1.getOutputCurrent());
    	
    	SmartDashboard.putNumber("Right1Encoder", ControllerRight1.getSpeed());
    	
    	SmartDashboard.putNumber("RightEncoderPos", ControllerRight1.getPosition());
    	
    	SmartDashboard.putNumber("DriveYToggle", m_dThrottleDirection);
    }
   
    private class EncoderWrapper implements PIDSource{

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			// TODO Auto-generated method stub
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			// TODO Auto-generated method stub
			return PIDSourceType.kDisplacement;
		}

		@Override
		public double pidGet() {
			// TODO Auto-generated method stub
			return ControllerRight1.getPosition();
		}
    	
    }
    
    private class DriveDistancePID implements PIDOutput{

		@Override
		public void pidWrite(double output) {
			// TODO Auto-generated method stub
			ArcadeDrive(0, output);
		}
    	
    }
    
}



