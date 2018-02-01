package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.OI;
import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drive the robot with a PS4 controller in tank mode
 */
public class PSTank extends Command {

	boolean arcade = true;
	boolean squaredInputs = true;

	public PSTank() {
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		/*
		 * if (OI.ps.getRawButton(12)) { arcade = !arcade; } if (arcade) arcade(); else
		 * tank();
		 */

		double lDamp = .75 + .25 * OI.ps.getRawAxis(3);
		double rDamp = .75 + .25 * OI.ps.getRawAxis(4);

		double lSpeed = OI.ps.getRawAxis(1) * lDamp;
		double rSpeed = OI.ps.getRawAxis(5) * rDamp;

		// OI.ps.setRumble(RumbleType.kLeftRumble, lSpeed);
		// OI.ps.setRumble(RumbleType.kRightRumble, rSpeed);

		Robot.driveTrain.tankDrive.tankDrive(lSpeed, rSpeed, true);
		SmartDashboard.putNumber("leftM-Enc", Robot.driveTrain.leftMotor.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("rightM-Enc", Robot.driveTrain.rightMotor.getSelectedSensorPosition(0));
	}

	/*
	 * private void tank() {
	 * 
	 * }
	 * 
	 * private void arcade() { // This isnt done. I needed to leave double lDamp =
	 * 1.0 - OI.ps.getRawAxis(3); double rDamp = 1.0 - OI.ps.getRawAxis(4);
	 * 
	 * double lSpeed = OI.ps.getRawAxis(1) * lDamp; double rSpeed =
	 * OI.ps.getRawAxis(5) * rDamp;
	 * 
	 * Robot.driveTrain.tankDrive.arcadeDrive(lSpeed, rSpeed, true);
	 * SmartDashboard.putNumber("leftM-Enc",
	 * Robot.driveTrain.leftMotor.getSelectedSensorPosition(0));
	 * SmartDashboard.putNumber("rightM-Enc",
	 * Robot.driveTrain.rightMotor.getSelectedSensorPosition(1));
	 * 
	 * }
	 */

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
/*
	
	
	*/