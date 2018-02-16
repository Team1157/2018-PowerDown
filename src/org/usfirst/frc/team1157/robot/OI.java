/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1157.robot;

import org.usfirst.frc.team1157.robot.commands.LiftGoto;
import org.usfirst.frc.team1157.robot.commands.ManBox;
import org.usfirst.frc.team1157.robot.commands.TurnBox;
import org.usfirst.frc.team1157.robot.commands.WinchDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	// Joysticks
	// private static boolean ps4 = true;
	public static Joystick stickSpin = new Joystick(0);
	public static Joystick stickNoSpin = new Joystick(1);
	public static Joystick ps = new Joystick(2);
/* klsjhflkjashdkl();
 *  lkdhfkajhgsku [-0,fj;las
 *  jkghajdghsfkh[q
 *  ' -[0949t6u jflkjh's
 *  */

	// Lift buttons
	public JoystickButton liftGotoTop = new JoystickButton(stickNoSpin, 4);
	public JoystickButton liftGotoMiddle = new JoystickButton(stickNoSpin, 2);
	public JoystickButton liftGotoBottom = new JoystickButton(stickNoSpin, 3);
	
	// Manipulator buttons
	public JoystickButton boxIn = new JoystickButton(stickSpin, 1);
	public JoystickButton boxOut = new JoystickButton(stickSpin, 2);
	public JoystickButton boxSpin = new JoystickButton(stickSpin, 3);
	public JoystickButton boxSpin2 = new JoystickButton(stickSpin, 4);
	public JoystickButton winchIn = new JoystickButton(stickNoSpin, 6);
	public JoystickButton winchOut = new JoystickButton(stickNoSpin, 7);
	
	// Initialize buttons
	public OI() {
		liftGotoTop.whenPressed(new LiftGoto(LiftGoto.LiftDestination.TOP));
		liftGotoMiddle.whenPressed(new LiftGoto(LiftGoto.LiftDestination.MIDDLE));
		liftGotoBottom.whenPressed(new LiftGoto(LiftGoto.LiftDestination.BOTTOM));
		boxIn.whileHeld(new ManBox(1));
		boxOut.whileHeld(new ManBox(-1));
		boxSpin.whileActive(new TurnBox(1));
		boxSpin2.whileActive(new TurnBox(-1));
		winchIn.whileHeld(new WinchDrive(1));
		winchOut.whileHeld(new WinchDrive(-1));
	}

}
