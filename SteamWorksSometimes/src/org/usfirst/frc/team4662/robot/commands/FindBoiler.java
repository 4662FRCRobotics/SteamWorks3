package org.usfirst.frc.team4662.robot.commands;

import org.usfirst.frc.team4662.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FindBoiler extends Command {

    public FindBoiler() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.visionSystem);
    	requires(Robot.driveSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(1.5);
    	Robot.visionSystem.LightOn();
    	Robot.driveSystem.ShooterForward();
    	Robot.visionSystem.ShooterCam();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.visionSystem.isTargetFound() || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (Robot.visionSystem.isTargetFound()) {
    		Command commandGroup = (Command) new MoveToBoilerGroup();
    		commandGroup.start();
    	}
    	Robot.visionSystem.LightOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.visionSystem.LightOff();
    	}
}
