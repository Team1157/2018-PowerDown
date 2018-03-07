package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoMoveBad extends Command {
	double distance, speed, rotation = 0;
	double encoderClicksPerIn = 56.81831468380663;

	double encoderPosR = 0;
	double encoderPosL = 0;
	double distanceTraveledR = 0;
	double distanceTraveledL = 0;

	boolean finished = false;
	boolean initialized = false;
	long count = 0;

	public AutoMoveBad(double distance, double speed) {
	 		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	    distance = 72;
 		SmartDashboard.putNumber("leftM-Enc", Robot.driveTrain.leftMotor.getSelectedSensorPosition(0));
 		SmartDashboard.putNumber("rightM-Enc", Robot.driveTrain.rightMotor.getSelectedSensorPosition(0));
 		Robot.driveTrain.leftMotor.setSelectedSensorPosition(0, 0, 500);
 		Robot.driveTrain.rightMotor.setSelectedSensorPosition(0, 0, 500);
 		Robot.driveTrain.tankDrive.arcadeDrive(speed, 0);
		count = 0;
 	}

	// Called repeatedly when this Command is scheduled to run
	// Cole = god
	protected void execute() {
		Robot.driveTrain.tankDrive.arcadeDrive(speed, rotation);
        if(initialized == false) {
            
    		if(Robot.driveTrain.rightMotor.getSelectedSensorPosition(0) != 0 || Robot.driveTrain.leftMotor.getSelectedSensorPosition(0) != 0){
    		    //Do nothing
    		} else {
    		    initialized = true;
    		}
    	    
    	} else {
    		Robot.driveTrain.tankDrive.arcadeDrive(0.5, rotation);
    		
    		encoderPosR = (double) Robot.driveTrain.rightMotor.getSelectedSensorPosition(0);
    		encoderPosL = (double) Robot.driveTrain.leftMotor.getSelectedSensorPosition(0);
    		
    		SmartDashboard.putNumber("leftM-Enc - AM", encoderPosR);
    		SmartDashboard.putNumber("rightM-Enc - AM", encoderPosL);
		
    		distanceTraveledR = Math.abs(encoderPosR/encoderClicksPerIn);
    		distanceTraveledL = Math.abs(encoderPosL/encoderClicksPerIn);
    
    		SmartDashboard.putNumber("Left Distance (in)", distanceTraveledR);
    		SmartDashboard.putNumber("Right Distance (in)", distanceTraveledL);
    		
    		SmartDashboard.putNumber("Target Distance (in)", distance);
    		
    		if(distanceTraveledR >= distance || distanceTraveledL >= distance) {
    			finished = true;
    		}
		}
        		
		count = count + 1;
		SmartDashboard.putNumber("Loop Counter", count);
	    		
    }

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.manipulator.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}