package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Turning of robot during autonomous
 */
public class AutoTurn extends Command {

    double kDist = 70;
    double pointNoReturn = 45;
    double decell = .4;

    Timer timer;
    double currentV = 0;
    double minV = .2;
    double maxV = 1;
    double accel = .3;
    double currentTime = 0;
    double oldTime = 0;

    boolean finished = false;
   boolean turnLeft;
    double Kp = 0.3;
    double error;
    long count = 0;
    boolean initialized = false;

    public AutoTurn(boolean turnLeft) {
	requires(Robot.driveTrain);

	this.turnLeft = turnLeft;
	setTimeout(10);
	
	this.timer = new Timer();
	timer.start();

    }

    // Called just before this Command runs the first time
    protected void initialize() {
	Robot.gyro.reset();
	// TODO:remove
	// angle = 90;
	count = 0;
	finished = false;
	

	initialized = false;
	
	currentV = minV;
	oldTime = 0;
	currentTime = 0;
	
	
	
	// TODO:remove
	//distance = 70;
	//speed = 0.5;
	timer.reset();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	
	double currAngle = Math.abs(Robot.gyro.getAngle());
	
	if (initialized == false) {

	    if (Math.abs(currAngle) > 1) {
		// Do nothing
		SmartDashboard.putNumber("Gyro", currAngle);
	    } else {
		initialized = true;
	    }

	} else {
	    if (85 <= Math.abs(currAngle)) {
		finished = true;
	    }

	   
	    if (currAngle <= pointNoReturn) {
		if (Math.abs(currentV) < maxV) {
		    currentTime = timer.get();
		    currentV = Math.abs(currentV) + accel * (currentTime - oldTime);
		    oldTime = currentTime;
		    SmartDashboard.putNumber("Speed", currentV);

		}

	    } else {
		if (Math.abs(currentV) > minV) {
		    currentTime = timer.get();
		    currentV = Math.abs(currentV) - decell * (currentTime - oldTime);
		    oldTime = currentTime;
		}
	    }
	    if (turnLeft) {
		currentV = Math.abs(currentV) * -1;
	    }
	   Robot.driveTrain.tankDrive.arcadeDrive(0, currentV); 
	}

	 count += 1;
	 SmartDashboard.putNumber("Loop Counter", count);

	/*
	 * Robot.driveTrain.tankDrive.arcadeDrive(0, turnspeed); if
	 * (Math.abs(Robot.gyro.getAngle() - angle) >= 2.5){
	 * Robot.driveTrain.tankDrive.arcadeDrive(0, 0); } else { finished = true;
	 */
    }

    

    // Make this return true when this Command no longer needs to run execute()
    // notes notes notes notes notes notes notes notes notes notes notes notes
    // notesnotes notes notesnotes
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
