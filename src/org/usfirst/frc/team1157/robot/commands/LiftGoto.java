package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Goto a pre-programmed position on the lift
 */
public class LiftGoto extends Command {
//TODO: get proper encoder values
	public enum LiftDestination {
		TOP(100),
		BOTTOM(0),
		MIDDLE(50);
		
		public final int position;
		LiftDestination(int pos) {
			position = pos;
		}
	}

	private LiftDestination target;
	private int startingPosition;
	private boolean direction; // true = up

	public LiftGoto(LiftDestination tgt) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.lift);

		target = tgt;
		
		// Reset limit counters
		Robot.lift.limitTop.reset();
		Robot.lift.limitBottom.reset();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		startingPosition = Robot.lift.liftMotor.getSelectedSensorPosition(0);
		direction = startingPosition < target.position;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (direction) {
			Robot.lift.liftMotor.set(0.5);
		}
		else {
			Robot.lift.liftMotor.set(-0.5);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		// Exit if limit switches are pressed
		if (Robot.lift.limitTop.get() > 0 || Robot.lift.limitBottom.get() > 0) {
			return true;
		}
		// Exit if goals are reached
		if (direction) {
			return Robot.lift.liftMotor.getSelectedSensorPosition(0) >= target.position;
		}
		else {
			return Robot.lift.liftMotor.getSelectedSensorPosition(0) <= target.position;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.lift.liftMotor.set(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
