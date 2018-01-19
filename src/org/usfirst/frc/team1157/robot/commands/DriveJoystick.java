package org.usfirst.frc.team1157.robot.commands;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1157.robot.OI;
import org.usfirst.frc.team1157.robot.Robot;
import org.usfirst.frc.team1157.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveJoystick extends Command {

	
	boolean squaredInputs = true;
    public DriveJoystick() 
    {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
   
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	double xSpeed = OI.stickSpin.getX();
    	double zRotation = OI.stickSpin.getZ();
    	double liftSpeed = OI.stickNoSpin.getY();
    	Robot.driveTrain.tankDrive.arcadeDrive(xSpeed, zRotation, squaredInputs);
    	SmartDashboard.putNumber("rightM-Enc", Robot.driveTrain.leftMotor.getSelectedSensorPosition(0));
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
