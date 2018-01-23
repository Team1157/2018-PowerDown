package org.usfirst.frc.team1157.robot.commands;

import org.omg.PortableServer.ImplicitActivationPolicyOperations;
import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTurn extends Command {

	double time, turnspeed, angle;
    public AutoTurn(double time, double turnspeed, double angle) {
    requires(Robot.driveTrain);
    this.time = time;
	this.turnspeed = turnspeed;
	this.angle = angle;
	setTimeout(time);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*Robot.driveTrain.tankDrive.arcadeDrive(0, turnspeed);
    	if (Math.abs(Robot.gyro.getAngle() - angle) >= 2.5){
    		Robot.driveTrain.tankDrive.arcadeDrive(0, 0);
    	}
    	else {
    	    finished = true;*/
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
