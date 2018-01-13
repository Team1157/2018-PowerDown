package org.usfirst.frc.team1157.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.*;


public class DriveTrain extends Subsystem {



    public DifferentialDrive tankDrive;
	WPI_TalonSRX rightMotor;
    WPI_TalonSRX leftMotor;
    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void driveForwardConstant() 
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
    }


    public void stop() 
    {
	rightMotor.set(0);
	leftMotor.set(0);
    }
}


