package org.usfirst.frc.team1157.robot.subsystems;

import org.usfirst.frc.team1157.robot.RobotMap;
import org.usfirst.frc.team1157.robot.commands.PSTank;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Main drive chain for robot
 * Default command: PSTank
 */
public class DriveTrain extends Subsystem {

	public WPI_TalonSRX rightMotor = new WPI_TalonSRX(RobotMap.rightMotor);
	public WPI_TalonSRX leftMotor = new WPI_TalonSRX(RobotMap.leftMotor);
	public WPI_TalonSRX rightSlave = new WPI_TalonSRX(RobotMap.rightSlave);
	public WPI_TalonSRX leftSlave = new WPI_TalonSRX(RobotMap.leftSlave);

	public DifferentialDrive tankDrive = new DifferentialDrive(leftMotor, rightMotor);

	public DriveTrain() {
		rightSlave.set(ControlMode.Follower, RobotMap.rightMotor);
		leftSlave.set(ControlMode.Follower, RobotMap.leftMotor);

		leftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		tankDrive.setSafetyEnabled(false);

	}
	public void initDefaultCommand() {
		
		// Set the default command for a subsystem here.
		// setDefaultCommand(new ArcadeJoy());
		setDefaultCommand(new PSTank());

	}

	public void stop() {
		rightMotor.set(0);
		leftMotor.set(0);
	}
}
