package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.OI;
import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveJoystick extends Command {

	boolean rot = true;

	boolean squaredInputs = true;

	public DriveJoystick() {
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double damp = 1.0 - (OI.stickSpin.getThrottle() / 2);
		double ySpeed = OI.stickSpin.getY() * damp;
		double xAxis = OI.stickSpin.getX() * .75 * damp;
		double zRotation = OI.stickSpin.getZ() * .75 * damp;

		if (OI.stickSpin.getRawButton(7))
			rot = !rot;

		Robot.driveTrain.tankDrive.arcadeDrive(ySpeed, (rot) ? zRotation : xAxis, squaredInputs);
		SmartDashboard.putNumber("leftM-Enc", Robot.driveTrain.leftMotor.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("rightM-Enc", Robot.driveTrain.rightMotor.getSelectedSensorPosition(1));

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