package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.OI;
import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drive the robot in arcade mode
 */
public class ArcadeJoy extends Command {

    boolean rot = true;
    boolean squaredInputs = true;

    public ArcadeJoy() {
	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    /*
     * protected void execute() { double damp = 1.0 - (OI.stickSpin.getThrottle() /
     * 2); double ySpeed = OI.stickSpin.getY() * damp; double zRotation =
     * OI.stickSpin.getZ() * .75 * damp;
     * 
     * Robot.driveTrain.tankDrive.arcadeDrive(ySpeed, zRotation, squaredInputs);
     * SmartDashboard.putNumber("leftM-Enc",
     * Robot.driveTrain.leftMotor.getSelectedSensorPosition(0));
     * SmartDashboard.putNumber("rightM-Enc",
     * Robot.driveTrain.rightMotor.getSelectedSensorPosition(1)); }
     */
    protected void execute() {
	double encoderPos = Robot.lift.liftMotor.getSelectedSensorPosition(0);
	double damp = 1.0 - (OI.stickSpin.getThrottle() / 2);
	if (encoderPos > 4500 && encoderPos < 9000) {
	    damp = map(4500, 9000, 1.0, .5, Robot.lift.liftMotor.getSelectedSensorPosition(0));
	} else if (encoderPos >= 9000) {
	    damp = .5;
	}
	double ySpeed = -OI.stickSpin.getY() * damp;
	double xAxis = OI.stickSpin.getX() * .75 * damp;
	double zRotation = OI.stickSpin.getZ() * damp;

	Robot.driveTrain.tankDrive.arcadeDrive(ySpeed, zRotation, squaredInputs);
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

    double map(double oldMin, double oldMax, double newMin, double newMax, double oldValue) {
	double oldRange = (oldMax - oldMin);
	double newRange = (newMax - newMin);
	double newValue = (((oldValue - oldMin) * newRange) / oldRange) + newMin;
	return newValue;
    }
}