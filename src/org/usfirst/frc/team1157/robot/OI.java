/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1157.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	// private static boolean ps4 = true;
	public static Joystick stickSpin = new Joystick(0);
	public static Joystick stickNoSpin = new Joystick(1);
	public static Joystick ps = new Joystick(2);

}
