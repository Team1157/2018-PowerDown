package org.usfirst.frc.team1157.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.CANTalon;
/**
 *
 */
public class DriveTrain extends Subsystem {



    CANTalon RightMotor;
    CANTalon LeftMotor;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

