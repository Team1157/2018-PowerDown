package org.usfirst.frc.team1157.robot.subsystems;

import org.usfirst.frc.team1157.robot.RobotMap;
import org.usfirst.frc.team1157.robot.commands.ManBox;
import org.xml.sax.SAXNotSupportedException;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Box grabber
 * Default command: none
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
        setDefaultCommand(new ManBox(1));
    }
    
   public void manipulateBlock(double speed) {
	  wheelL.set(speed);
	  wheelR.set(speed);
   }
   
}

