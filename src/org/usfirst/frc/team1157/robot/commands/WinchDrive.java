package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive the winch using a joystick
 */
public class WinchDrive extends Command {
	
	public WinchDrive() {
		requires(Robot.climber);
	}

	//TODO: Create a working drive command here
	protected boolean isFinished() {
		return false;
	}

}
