package org.usfirst.frc.team1157.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1157.robot.RobotMap;
import org.usfirst.frc.team1157.robot.commands.DriveJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;


public class DriveTrain extends Subsystem {

	public WPI_TalonSRX rightMotor = new WPI_TalonSRX(RobotMap.rightMotor);
	public WPI_TalonSRX leftMotor = new WPI_TalonSRX(RobotMap.leftMotor);
	//public WPI_TalonSRX rightSlave = new WPI_TalonSRX(RobotMap.rightSlave);
	public WPI_TalonSRX leftSlave = new WPI_TalonSRX(RobotMap.leftSlave);

    public DifferentialDrive tankDrive = new DifferentialDrive(leftMotor, rightMotor);
    
    public void initDefaultCommand() 
    {
    	
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveJoystick());
        //rightSlave.set(ControlMode.Follower, RobotMap.rightMotor);
        leftSlave.set(ControlMode.Follower, RobotMap.leftMotor);
        
        leftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        
    }
    
    
   /* public void driveForwardConstant() 
    {
    	//displayEncoderVelocity();
    	double constantForward = SmartDashboard.getNumber("Forward Speed", 500);

    	rightMotor.set(constantForward);
    	leftMotor.set(constantForward);
    }	

    public void driveBackwardConstant() 
    {
    	//displayEncoderVelocity();
    	double constantBackward = SmartDashboard.getNumber("Backward Speed", -500);

    	rightMotor.set(constantBackward);
    	leftMotor.set(constantBackward);
    }
    public void turnLeftConstant()
    {
    	//displayEncoderVelocity();
    	double constantLeft = SmartDashboard.getNumber("Turn Speed", 500);
    	rightMotor.set(constantLeft);
    	leftMotor.set(-1*constantLeft);
    }
    public void turnRightConstant()
    {
    	//displayEncoderVelocity();
    	double constantRight = SmartDashboard.getNumber("Turn Speed", 500);
    	rightMotor.set(-1*constantRight);
    	leftMotor.set(constantRight);
    }*/


    public void stop() 
    {
    	rightMotor.set(0);
    	leftMotor.set(0);
    }
}


