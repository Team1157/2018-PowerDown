package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Movement during autonomous mode.
 */
public class AutoMove extends Command {
    double kDist = 42;
    double pointNoReturn = 0;
    double decell = 0;
    
    Timer timer;
    double currentV = 0;
    double minV = .3;
    double maxV = 1;
    double accel = .1;
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

    public AutoMove(double distance, double speed) {
	
	requires(Robot.driveTrain);
	
	
	this.distance = distance;
	this.speed = speed;
	this.timer = new Timer();
	timer.start();
	// setTimeout(time);
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
	
	distance -= 2;
	Finished = false;

	initialized = false;
	
	SmartDashboard.putNumber("leftM-Enc", Robot.driveTrain.leftMotor.getSelectedSensorPosition(0));
	SmartDashboard.putNumber("rightM-Enc", Robot.driveTrain.rightMotor.getSelectedSensorPosition(0));
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
		// Do nothing
	    } else {
		initialized = true;
	    }

	} else {
	    encoderPosR = (double) Robot.driveTrain.rightMotor.getSelectedSensorPosition(0);
	    encoderPosL = (double) Robot.driveTrain.leftMotor.getSelectedSensorPosition(0);

	    SmartDashboard.putNumber("leftM-Enc - AM", encoderPosR);
	    SmartDashboard.putNumber("rightM-Enc - AM", encoderPosL);
	    SmartDashboard.putNumber("Gyro", Robot.gyro.getAngle());

	    distanceTraveledR = Math.abs(encoderPosR / encoderClicksPerIn);
	    distanceTraveledL = Math.abs(encoderPosL / encoderClicksPerIn);

	    SmartDashboard.putNumber("Left Distance (in)", distanceTraveledR);
	    SmartDashboard.putNumber("Right Distance (in)", distanceTraveledL);

	    SmartDashboard.putNumber("Target Distance (in)", distance);

	    if (distanceTraveledR >= distance || distanceTraveledL >= distance) {
		Finished = true;
	    }
	    
	    pointNoReturn = distance - kDist*currentV;
	    if (distanceTraveledL <= pointNoReturn) {
		if (currentV < maxV) {
		    currentTime = timer.get();
		    currentV = currentV + accel*(currentTime - oldTime);
		    oldTime = currentTime;
		} 
		
	    } else {
		if (currentV > minV) {
		    currentTime = timer.get();
		    currentV = currentV - decell*(currentTime - oldTime);
		    oldTime = currentTime;
		} 
	    }
	    //count = count + 1;
	    //SmartDashboard.putNumber("Loop Counter", count);
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
	return Finished;
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
