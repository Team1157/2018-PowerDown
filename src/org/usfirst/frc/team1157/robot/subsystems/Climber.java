package org.usfirst.frc.team1157.robot.subsystems;

import org.usfirst.frc.team1157.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The winch/climber mechanism for climbing up the scale at the end Default
 * command: WinchDrive
 */
public class Climber extends Subsystem {

	public Spark winchMotor = new Spark(RobotMap.winchMotor);

	public Climber() {

	}

	public void initDefaultCommand() {
		// setDefaultCommand(new WinchDrive());
	}

	public void stop() {
		winchMotor.set(0);
	}
}