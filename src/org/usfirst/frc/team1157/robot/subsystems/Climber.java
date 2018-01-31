package org.usfirst.frc.team1157.robot.subsystems;

import org.usfirst.frc.team1157.robot.RobotMap;
import org.usfirst.frc.team1157.robot.commands.WinchDrive;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	public Relay winchMotor = new Relay(RobotMap.winchMotor);
	
	public void initDefaultCommand() {
		setDefaultCommand(new WinchDrive());
	}
	
	public void stop() {
		winchMotor.set(Relay.Value.kOff);
	}
}