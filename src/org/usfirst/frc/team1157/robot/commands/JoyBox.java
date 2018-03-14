package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.OI;
import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoyBox extends Command {

	public JoyBox() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.manipulator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.manipulator.manipulateBox(OI.stickSpin.getY() - (OI.stickSpin.getTwist() * 0.5),
				OI.stickSpin.getY() + (OI.stickSpin.getTwist() * 0.5));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
