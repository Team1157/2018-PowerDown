package org.usfirst.frc.team1157.robot.subsystems;

import org.usfirst.frc.team1157.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class Manipulator extends Subsystem {
	
	public Spark wheelR = new Spark(RobotMap.wheelR);
	public Spark wheelL = new Spark(RobotMap.wheelL);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Manipulator() {
		wheelL.setInverted(true);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
   public void manipulateBlock(double speed) {
	  wheelL.set(speed);
	   wheelR.set(speed);
   }
   
}

