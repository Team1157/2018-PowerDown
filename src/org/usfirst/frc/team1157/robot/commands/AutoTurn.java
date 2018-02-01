package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Turning of robot during autonomous
 */
public class AutoTurn extends Command {
	
	boolean finished = false;
	double turnSpeed, angle;
	double Kp = 0.6;
	double error;

	public AutoTurn(double angle) {
		requires(Robot.driveTrain);

		this.angle = angle;
		setTimeout(1);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		// 
		error = Math.abs(angle) - Robot.gyro.getAngle() / 90.0;
		turnSpeed = Kp * (error);

		if (Math.abs(Robot.gyro.getAngle() - angle) >= 2.5) {
			Robot.driveTrain.tankDrive.arcadeDrive(0, turnSpeed);
		} else {
			finished = true;
		}

		/*
		 * Robot.driveTrain.tankDrive.arcadeDrive(0, turnspeed); if
		 * (Math.abs(Robot.gyro.getAngle() - angle) >= 2.5){
		 * Robot.driveTrain.tankDrive.arcadeDrive(0, 0); } else { finished = true;
		 */
	}

	// Make this return true when this Command no longer needs to run execute()
	// notes notes notes notes notes notes notes notes notes notes notes notes notesnotes notes notesnotes
	protected boolean isFinished() {
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
