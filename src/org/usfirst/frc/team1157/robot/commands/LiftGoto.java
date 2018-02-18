package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Goto a pre-programmed position on the lift
 */
public class LiftGoto extends Command {

    /**
     * The list of pre-programmed positions for the lift NAME(ENCODER_POS),
     */
    public enum LiftDestination {
	TOP(34_250), MIDDLE(18_000), BOTTOM(400), WARNING(0);

	public final int position;

	LiftDestination(int pos) {
	    position = pos;
	}
    }

    private LiftDestination target;
    private int startingPosition;
    private boolean direction; // true = up

    public LiftGoto(LiftDestination tgt) {
		requires(Robot.lift);
	
		target = tgt;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		startingPosition = Robot.lift.liftMotor.getSelectedSensorPosition(0);
		direction = startingPosition < target.position;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		SmartDashboard.putNumber("Target:", target.position);
		SmartDashboard.putNumber("Current", Robot.lift.liftMotor.getSelectedSensorPosition(0));
		SmartDashboard.putString("dir", (direction)?"up":"down");
		if (direction) {
		    Robot.lift.liftMotor.set(-0.6);
		} else {
		    Robot.lift.liftMotor.set(0.25);
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		// Exit if goals are reached
		if (direction) {
		    return Robot.lift.liftMotor.getSelectedSensorPosition(0) >= target.position;
		} else {
		    return Robot.lift.liftMotor.getSelectedSensorPosition(0) <= target.position;
		}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lift.liftMotor.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	end();
    }
}
