package org.usfirst.frc.team4662.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import org.usfirst.frc.team4662.robot.Robot;
/**
 *
 */
public class Autonomous extends CommandGroup {
	private String readFile( String file ) throws IOException {
		BufferedReader reader = new BufferedReader( new FileReader (file));
		String	       line = null;
		StringBuilder   stringBuilder = new StringBuilder();
		boolean bFirstTime = true;
		while( ( line = reader.readLine())!= null){
			line = line.trim();
			if (bFirstTime == true) {
				bFirstTime = false;
			} else {
				stringBuilder.append( "|" );
			}
			stringBuilder.append( line );
		}
		reader.close();
		return stringBuilder.toString();
	}
    public Autonomous() {
    	requires (Robot.driveSystem);
    	double dDistance = 0;
    	double dSpeed = 1.0;
    	double dWaitDuration = 0;
    	double dAngle = 0;
    	
    	//Preferences prefs = Preferences.getInstance();
    	SmartDashboard.putString("AutoCommandGroup", "Start");
    	
        	String strFileName = "/home/lvuser/Autonomous/SteamDefault.txt";
        	
        	String autoCommandList ="";
			try {
				autoCommandList = readFile(strFileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	String[] autoArray = autoCommandList.split("\\|");
	    	
	    	//SmartDashboard.putNumber ("NumNodes", iNumNodes);
	    	//for(i = 0; i < iNumNodes; i++) {
	    	for(int i =0; i < autoArray.length; i = i + 2){
	    		//node = nlAutoAttacks.item(i);
	    		//String strAutoCommand = node.getNodeName();
	    		String strAutoCommand = autoArray[i];
	    		SmartDashboard.putString("Direction", strAutoCommand);
	    		switch (strAutoCommand) {
	    			case "throttle":
	    				dSpeed = Double.valueOf(autoArray[i + 1]);
	    				SmartDashboard.putNumber("Speed", dSpeed);
	    				break;
	    			case "forward": 
	    	    		dDistance = Double.valueOf(autoArray[i + 1]);
	    	    		SmartDashboard.putNumber("FDistance", dDistance);
	    				addSequential (new DriveDistancePID(-dDistance, dSpeed));
	    				break;
	    			case "reverse": 
	    	    		dDistance = Double.valueOf(autoArray[i + 1]);
	    	    		SmartDashboard.putNumber("RDistance", dDistance);
	    				addSequential (new DriveDistancePID(dDistance, dSpeed));
	    				break;
	    			case "rotate":
	    	    		dAngle = Integer.valueOf(autoArray[i + 1]);
	    	    		SmartDashboard.putNumber("RAngle", dDistance);
	    				addSequential (new GyroRotatePID(dAngle));
	    				break;
	    			/*case "locatetarget":
	    	    		iDistance = Integer.valueOf(autoArray[i + 1]);
	    	    		//SmartDashboard.putNumber("Distance", iDistance);
	    				addSequential (new LocateTarget());
	    				break;
	    			case "shoot":
	    				addSequential (new Shoot());
	    				break;
	    			case "wait":
	    				dWaitDuration = Double.valueOf(autoArray [i + 1]);
	    				addSequential (new AutoWait(dWaitDuration));
	    				break;
	    		*/			    			
	    			default:
	    				SmartDashboard.putString("HI", "Default");
	    				
	    		}
	    		SmartDashboard.putString("AutoCommandGroup", "looping" + i);
	    	}
    	
    }
}
