package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.OI;
import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftDriveJoy extends Command {

	public LiftDriveJoy() {

		// Use requires() here to declare subsystem dependencies
		requires(Robot.lift);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Reset limit counters
		Robot.lift.limitTop.reset();
		Robot.lift.limitBottom.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// SmartDashboard.putNumber("LiftM-Enc",
		// Robot.lift.liftMotor.getSelectedSensorPosition(2));
		
		// Get joystick value
		double liftSpeed = OI.stickNoSpin.getY();
		// Check if top limit is pressed
		if (Robot.lift.limitTop.get() > 0) {
			// Only allow to move down
			if (liftSpeed < 0) {
				Robot.lift.liftMotor.set(liftSpeed);
				Robot.lift.limitTop.reset();
			}
			else {
				Robot.lift.liftMotor.set(0);
			}
		}
		// Check if bottom limit is pressed
		else if (Robot.lift.limitBottom.get() > 0) {
			// Only allow to move up
			if (liftSpeed > 0) {
				Robot.lift.liftMotor.set(liftSpeed);
				Robot.lift.limitBottom.reset();
			}
			else {
				Robot.lift.liftMotor.set(0);
			}
		}
		// Run as normal
		else {
			Robot.lift.liftMotor.set(liftSpeed);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}
	// why this code no work
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
