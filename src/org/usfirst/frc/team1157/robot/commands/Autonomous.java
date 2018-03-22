package org.usfirst.frc.team1157.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Full control of robot during autonomous
 */
public class Autonomous extends CommandGroup {

	public enum Position {
		LEFT, MIDDLE, RIGHT
	}

	Position start;
	Position switchNear;
	Position scale;
	Position switchFar;

	String gameData;

	int target = 0;

	public Autonomous(Position pos) {
		this.start = pos;
		SmartDashboard.putBoolean("autoDone", false);
	}
	
	public void setup() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.length() < 3) gameData = "ERROR";
		SmartDashboard.putString("gameData", gameData);
		// addSequential(new suckBox());
		if (gameData.charAt(0) == 'L')
			switchNear = Position.LEFT;
		else
			switchNear = Position.RIGHT;

		if (gameData.charAt(1) == 'L')
			scale = Position.LEFT;
		else
			scale = Position.RIGHT;

		if (gameData.charAt(2) == 'L')
			switchFar = Position.LEFT;
		else
			switchFar = Position.RIGHT;

		if (start == Position.MIDDLE)
			middle();
		else
			notMiddle();

	}

	private void notMiddle() {
		boolean variable;
		if (start == Position.LEFT)
			variable = false;
		else
			variable = true;

		if (switchNear == start) {

			addSequential(new AutoMove(164, false));
			addSequential(new AutoTurn(variable));
			addSequential(new AutoMove(10, false));
			addSequential(new AutoDropBoxL());
		} else if (scale == start) {
			addSequential(new AutoMove(312, false));
			addSequential(new AutoTurn(variable));
			addSequential(new AutoMove(10, true));
			addSequential(new AutoDropBoxH());
		} else {
			addSequential(new AutoMove(210, false));
			addSequential(new AutoTurn(variable));
			addSequential(new AutoMove(140, false));
		}
		SmartDashboard.putBoolean("autoDone", true);
	}

	private void middle() {
		// TODO fix with 90 angles
		boolean switchLeft;
		if (switchNear == Position.LEFT) {
			switchLeft = true;
			addSequential(new AutoMove(50, false));
			addSequential(new AutoTurn(switchLeft));
			addSequential(new AutoMove(55, false));
			addSequential(new AutoTurn(!switchLeft));
			addSequential(new AutoMove(50, false));
			addSequential(new AutoDropBoxL());
		} else {
			switchLeft = false;

			addSequential(new AutoMove(50, false));
			addSequential(new AutoTurn(switchLeft));
			addSequential(new AutoMove(45, false));
			addSequential(new AutoTurn(!switchLeft));
			addSequential(new AutoMove(50, false));
			addSequential(new AutoDropBoxL());
		}
		SmartDashboard.putBoolean("autoDone", true);
	}
	// addSequential(new Command2());

	// To run multiple commands at the same time,
	// use addParallel()
	// e.g. addParallel(new Command1());
	// addSequential(new Command2());
	// Command1 and Command2 will run in parallel.

	// A command group will require all of the subsystems that each member
	// would require.
	// e.g. if Command1 requires chassis, and Command2 requires arm,
	// a CommandGroup containing them would require both the chassis and the
	// arm.
}
