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

	// Lift buttons
	// public JoystickButton liftGotoTop = new JoystickButton(stickNoSpin, 4);
	public JoystickButton boxOut2 = new JoystickButton(stickNoSpin, 3);
	public JoystickButton boxIn2 = new JoystickButton(stickNoSpin, 1);
	public JoystickButton liftGotoBottom = new JoystickButton(stickNoSpin, 2);
	public JoystickButton boxSpin3 = new JoystickButton(stickNoSpin, 4);
	public JoystickButton boxSpin4 = new JoystickButton(stickNoSpin, 5);

	// Manipulator buttons
	public JoystickButton liftGotoBottom2 = new JoystickButton(stickSpin, 7);
	public JoystickButton liftGotoMiddle2 = new JoystickButton(stickSpin, 6);
	public JoystickButton liftGotoTop2 = new JoystickButton(stickSpin, 5);
	// public JoystickButton boxIn = new JoystickButton(stickSpin, 1);
	public JoystickButton boxOut = new JoystickButton(stickSpin, 2);
	public JoystickButton boxSpin = new JoystickButton(stickSpin, 3);
	public JoystickButton boxSpin2 = new JoystickButton(stickSpin, 4);
	public JoystickButton winchIn = new JoystickButton(stickNoSpin, 6);
	public JoystickButton winchIn2 = new JoystickButton(stickNoSpin, 7);
	public JoystickButton winchOut = new JoystickButton(stickNoSpin, 10);
	public JoystickButton winchOut2 = new JoystickButton(stickNoSpin, 11);

	// Initialize buttons
	public OI() {
		// liftGotoTop.whenPressed(new LiftGoto(LiftGoto.LiftDestination.TOP));
		// liftGotoMiddle.whenPressed(new LiftGoto(LiftGoto.LiftDestination.MIDDLE));
		liftGotoBottom.whenPressed(new LiftGoto(LiftGoto.LiftDestination.WARNING));
		liftGotoTop2.whenPressed(new LiftGoto(LiftGoto.LiftDestination.TOP));
		liftGotoMiddle2.whenPressed(new LiftGoto(LiftGoto.LiftDestination.MIDDLE));
		liftGotoBottom2.whenPressed(new LiftGoto(LiftGoto.LiftDestination.WARNING));
		// boxIn.whileHeld(new ManBox(1));
		boxIn2.whileHeld(new ManBox(1));
		boxOut.whileHeld(new ManBox(-1));
		boxOut2.whileHeld(new ManBox(-1));
		boxSpin.whileActive(new TurnBox(1));
		boxSpin2.whileActive(new TurnBox(-1));
		boxSpin3.whileActive(new TurnBox(1));
		boxSpin4.whileActive(new TurnBox(-1));
		winchIn.whileHeld(new WinchDrive(1));
		winchIn2.whileHeld(new WinchDrive(1));
		winchOut.whileHeld(new WinchDrive(-1));
		winchOut2.whileHeld(new WinchDrive(-1));
	}

}
