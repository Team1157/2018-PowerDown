package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.OI;
import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Control the lift with a joystick
 */
public class LiftDriveJoy extends Command {

    int position = 0;

    public LiftDriveJoy() {
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		int currentPosition = Robot.lift.liftMotor.getSelectedSensorPosition(0);
		SmartDashboard.putNumber("Lift Position", currentPosition);
		double yAxis = OI.stickNoSpin.getY();
	
		if (Math.abs(yAxis) < 0.1) {
		    if (currentPosition < position || currentPosition > 1000) {
			//yAxis = -(position - currentPosition)/2500;
			yAxis = -0.075;
			Robot.lift.liftMotor.set(yAxis);
		    }
		} else {
		    position = currentPosition;
		
		if (yAxis < 0) {
			Robot.lift.liftMotor.set(yAxis);
		} else if (!Robot.lift.limitBottom.get()) {
			Robot.lift.liftMotor.set(0);
		} else {
			Robot.lift.liftMotor.set(yAxis);
		}
    }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {

    }

    // why this code no work
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {

    }
}
