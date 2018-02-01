package org.usfirst.frc.team1157.robot.subsystems;

import org.usfirst.frc.team1157.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Box grabber
 * Default command: none
 */
public class Manipulator extends Subsystem {
	
	public Spark rightWheel = new Spark(RobotMap.manRightWheel);
	public Spark leftWheel = new Spark(RobotMap.manLeftWheel);

	public Manipulator() {
		leftWheel.setInverted(true);
	}
	
    public void initDefaultCommand() {
        // setDefaultCommand(new ManBox(1));
    }
    
    /**
     * Sets the motor to pick up or drop box.
     * @param speed The speed to go at
     */
    public void manipulateBox(double speed) {
	  leftWheel.set(speed);
	  rightWheel.set(speed);
   }
   
}

