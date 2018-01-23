package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.OI;
import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class LiftGoto extends Command {
	
	public enum Position {
		TOP,
		BOTTOM
	}
	
	private double speed;
	private Position target;

	public LiftGoto(Position tgt) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.lift);
		
		target = tgt;
		speed = 0.5;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}	

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch (target) {
		case TOP:
			Robot.lift.liftMotor.set(speed);
		
		case BOTTOM:
			Robot.lift.liftMotor.set(-speed);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Robot.lift.limitTop.get() || Robot.lift.limitBottom.get()) {
			return true;
		}
		return false;
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
