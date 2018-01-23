package org.usfirst.frc.team1157.robot.subsystems;

import org.usfirst.frc.team1157.robot.RobotMap;
import org.usfirst.frc.team1157.robot.commands.LiftDriveJoy;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {

	public Spark liftMotor = new Spark(RobotMap.lift);
	public DigitalInput limitTop = new DigitalInput(RobotMap.limitTop);
	public DigitalInput limitBottom = new DigitalInput(RobotMap.limitBottom);

	public void initDefaultCommand() {
		setDefaultCommand(new LiftDriveJoy());

		// liftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
		// 0, 10);
		// liftMotor;
	}

	public void stop() {
		liftMotor.set(0);
	}
}
