package org.usfirst.frc.team1157.robot.commands;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team1157.robot.OI;
import org.usfirst.frc.team1157.robot.Robot;
import org.usfirst.frc.team1157.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveJoystick extends Command {
	double xSpeed = OI.stick1.getX();
	double zRotation = OI.stick1.getZ();
	boolean squaredInputs = true;
    public DriveJoystick() 
    {
        requires(Robot.DriveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
   
    }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Robot.driveTrain.tankDrive.arcadeDrive(xSpeed, zRotation, squaredInputs);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    }
}
