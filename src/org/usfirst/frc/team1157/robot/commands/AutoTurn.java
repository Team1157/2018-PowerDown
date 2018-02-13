package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Turning of robot during autonomous
 */
public class AutoTurn extends Command {
	
	boolean finished = false;
	double turnSpeed, angle;
	double Kp = 0.4;
	double error;
	long count = 0;
	boolean initialized = false;

	public AutoTurn(double angle) {
		requires(Robot.driveTrain);

		this.angle = angle;
		setTimeout(2);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
	    Robot.gyro.reset();
	    //TODO:remove
	    angle = 90;
	    count = 0;
	    finished = false;
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	    
	    if(initialized == false) {
	            
    		if(Math.abs(Robot.gyro.getAngle()) > 1){
    		    //Do nothing
    		    SmartDashboard.putNumber("Gyro", Robot.gyro.getAngle());
    		}else {
    		    initialized = true;
    		}
    	    
    	}else {  

		// 
		error = (Math.abs(angle) - Robot.gyro.getAngle()) / 30.0;
		turnSpeed = Kp * (error);

		if (Math.abs(Robot.gyro.getAngle() - angle) >= 2.5) {
			Robot.driveTrain.tankDrive.arcadeDrive(0, turnSpeed);
			SmartDashboard.putNumber("turnSpeed", turnSpeed);
			SmartDashboard.putNumber("Gyro", Robot.gyro.getAngle());
			SmartDashboard.putNumber("turnError", error);
		} else {
			finished = true;
		}
		
		count = count + 1;
    		SmartDashboard.putNumber("Loop Counter", count);

		/*
		 * Robot.driveTrain.tankDrive.arcadeDrive(0, turnspeed); if
		 * (Math.abs(Robot.gyro.getAngle() - angle) >= 2.5){
		 * Robot.driveTrain.tankDrive.arcadeDrive(0, 0); } else { finished = true;
		 */
    	}
	}

	// Make this return true when this Command no longer needs to run execute()
	// notes notes notes notes notes notes notes notes notes notes notes notes notesnotes notes notesnotes
	protected boolean isFinished() {
	    SmartDashboard.putBoolean("turn done", finished);
		return finished || isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
