package org.usfirst.frc.team1157.robot.subsystems;

import org.usfirst.frc.team1157.robot.RobotMap;
import org.usfirst.frc.team1157.robot.commands.DriveJoystick;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {

    public WPI_TalonSRX lyftMotar = new WPI_TalonSRX(RobotMap.lift);

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveJoystick());
    	
    	lyftMotar.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    }
    
    public void stop() {
    	lyftMotar.set(0);
    }
}

