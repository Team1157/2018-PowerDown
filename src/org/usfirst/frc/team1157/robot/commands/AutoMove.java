package org.usfirst.frc.team1157.robot.commands;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoMove extends Command {
	double distance, speed, rotation;
	double encoderClicksPerIn = 56.81831468380663;
	double encoderPosR = 0;
	double encoderPosL = 0;
	double distanceTraveledL = 0;
	double distanceTraveledR = 0;
	boolean Finished = false;
	// julien = scrub
	public AutoMove(double distance, double speed, double rotation) {
		requires(Robot.driveTrain);
		this.distance = distance;
		this.speed = speed;
		this.rotation = rotation;
		// setTimeout(time);
		/* TODO: make this work with distance */
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	
		SmartDashboard.putNumber("leftM-Enc", Robot.driveTrain.leftMotor.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("rightM-Enc", Robot.driveTrain.rightMotor.getSelectedSensorPosition(0));
		Robot.driveTrain.leftMotor.setSelectedSensorPosition(0, 0, 500);
		Robot.driveTrain.rightMotor.setSelectedSensorPosition(0, 0, 500);
		Robot.driveTrain.tankDrive.arcadeDrive(speed, 0);

	}

	// Called repeatedly when this Command is scheduled to run
	// Cole = god
	protected void execute() {
		Robot.driveTrain.tankDrive.arcadeDrive(speed, rotation);
		encoderPosR = Robot.driveTrain.rightMotor.getSelectedSensorPosition(0);
		encoderPosL = (double) Robot.driveTrain.leftMotor.getSelectedSensorPosition(0);
		distanceTraveledL = encoderClicksPerIn/encoderPosL;
		distanceTraveledR = encoderClicksPerIn/encoderPosR;
		if(distanceTraveledR >= distance || distanceTraveledL >= distance) {
			Finished = true;
		}
			// bill da dest
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Finished;

	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	
	//programming suk
	}
}
