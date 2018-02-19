package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Movement during autonomous mode.
 */
public class AutoMove extends Command {
    double kDist = 70;
    double pointNoReturn = 0;
    double decell = 0.35;
    boolean backwards = false;
    Timer timer;
    double currentV = 0;
    double minV = .3;
    double maxV = 1;
    double accel = .2;
    double currentTime = 0;
    double oldTime = 0;
    
    double distance, speed = 0.5;
    double rotation;
    double encoderClicksPerIn = 75.757333333;// 56.81831468380663;

    double encoderPosR = 0;
    double encoderPosL = 0;

    double distanceTraveledL = 0;
    double distanceTraveledR = 0;

    boolean Finished = false;
    boolean initialized = false;
    long count = 0;

    public AutoMove(double distance, boolean backwards) {
	
	requires(Robot.driveTrain);
	
	this.backwards = backwards;
	this.distance = Math.abs(distance);
	this.timer = new Timer();
	timer.start();
	setTimeout(distance/2);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	currentV = minV;
	oldTime = 0;
	currentTime = 0;
	
	pointNoReturn = 0;
	
	// TODO:remove
	//distance = 70;
	//speed = 0.5;
	timer.reset();
	
	Finished = false;

	initialized = false;
	
	Robot.driveTrain.leftMotor.setSelectedSensorPosition(0, 0, 500);
	Robot.driveTrain.rightMotor.setSelectedSensorPosition(0, 0, 500);
	count = 0;
	Robot.gyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    // Cole = god
    protected void execute() {

	if (initialized == false) {

	    if (Robot.driveTrain.rightMotor.getSelectedSensorPosition(0) != 0
		    || Robot.driveTrain.leftMotor.getSelectedSensorPosition(0) != 0) {
		Robot.driveTrain.leftMotor.setSelectedSensorPosition(0, 0, 500);
		Robot.driveTrain.rightMotor.setSelectedSensorPosition(0, 0, 500);
		// Do nothing
	    } else {
		initialized = true;
	    }

	} else {
	    encoderPosR = (double) Robot.driveTrain.rightMotor.getSelectedSensorPosition(0);
	    encoderPosL = (double) Robot.driveTrain.leftMotor.getSelectedSensorPosition(0);


	    distanceTraveledR = Math.abs(encoderPosR / encoderClicksPerIn);
	    distanceTraveledL = Math.abs(encoderPosL / encoderClicksPerIn);

	    if (distanceTraveledR >= distance || distanceTraveledL >= distance) {
		Finished = true;
	    }
	    
	    pointNoReturn = distance - ((223.9*Math.pow(Math.abs(currentV), 2)) - (85.3*Math.abs(currentV)) - 2);
	    if (distanceTraveledL <= pointNoReturn) {
		if (Math.abs(currentV) < maxV) {
		    currentTime = timer.get();
		    currentV = Math.abs(currentV) + accel*(currentTime - oldTime);
		    oldTime = currentTime;
		    
		} 
		
	    } else {
		if (Math.abs(currentV) > minV) {
		    currentTime = timer.get();
		    currentV = Math.abs(currentV) - decell*(currentTime - oldTime);
		    oldTime = currentTime;
		}
	    }
	    
	    if(backwards) {
		currentV = Math.abs(currentV) * -1;
	    }
	    rotation = (0 - Robot.gyro.getAngle()) / 10;
	    Robot.driveTrain.tankDrive.arcadeDrive(currentV, rotation);
	    
	    
	    
	    /*
	     * encoder corection double dif = Math.abs(Math.abs(encoderPosR) -
	     * Math.abs(encoderPosL)); SmartDashboard.putNumber("dif", dif); if (dif > 10) {
	     * if(Math.abs(encoderPosR) > Math.abs(encoderPosL)) {
	     * Robot.driveTrain.tankDrive.tankDrive(speed + dif/200, speed); } else {
	     * Robot.driveTrain.tankDrive.tankDrive(speed, speed + dif/200); } } else {
	     * Robot.driveTrain.tankDrive.tankDrive(speed, speed); }
	     */
	}

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	// print("done");
	return Finished || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same subsystems
    // is scheduled to run
    protected void interrupted() {
	end();
    }
}
