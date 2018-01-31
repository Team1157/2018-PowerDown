package org.usfirst.frc.team1157.robot.subsystems;

import org.usfirst.frc.team1157.robot.RobotMap;
import org.usfirst.frc.team1157.robot.commands.LiftDriveJoy;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {

	public WPI_TalonSRX liftMotor = new WPI_TalonSRX(RobotMap.lift);
	public Counter limitTop = new Counter(RobotMap.limitTop);
	public Counter limitBottom = new Counter(RobotMap.limitBottom);

	public void initDefaultCommand() {
		setDefaultCommand(new LiftDriveJoy());

		// Enable encoder
		liftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
	}

	public void stop() {
		liftMotor.set(0);
	}
}
