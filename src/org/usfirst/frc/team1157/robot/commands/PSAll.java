package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.OI;
import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive the robot with a PS4 controller in tank mode
 */
public class PSAll extends Command {

    boolean arcade = true;
    boolean squaredInputs = true;

    public PSAll() {
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

	double lDamp = .6;// + .25 * OI.ps.getRawAxis(3);
	double rDamp = .6;// + .25 * OI.ps.getRawAxis(4);

	double lSpeed = -OI.ps.getRawAxis(1) * lDamp;
	double rSpeed = -OI.ps.getRawAxis(5) * rDamp;

	// OI.ps.setRumble(RumbleType.kLeftRumble, lSpeed);
	// OI.ps.setRumble(RumbleType.kRightRumble, rSpeed);

	Robot.driveTrain.tankDrive.tankDrive(lSpeed, rSpeed, true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	return false;
    }

    // Called once after isFinished returns true
    protected void end() {

    }

    protected void interrupted() {
	end();
    }
}