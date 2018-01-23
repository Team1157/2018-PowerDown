package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
enum Position {
	LEFT, MIDDLE, RIGHT
}

public class Autonomous extends Command {
	Position start;

	// If ours is left then true
	Boolean switchNear;
	Boolean scale;
	Boolean switchFar;
	//

	int target = 0;

	public Autonomous(Position pos, boolean goSwitch) {
		requires(Robot.driveTrain);
		this.start = pos;
/*`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````*/
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		switchNear = gameData.charAt(0) == 'L';
		scale = gameData.charAt(0) == 'L';
		switchFar = gameData.charAt(0) == 'L';

		if ((start == Position.LEFT && switchNear) || (start == Position.RIGHT && !switchNear)) {
			target = 0;
		}
		if (start == Position.MIDDLE) {

		}
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
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

