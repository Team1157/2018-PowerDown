package org.usfirst.frc.team1157.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

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
		gameData = DriverStation.getInstance().getGameSpecificMessage();
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
		int variable;
		if (start == Position.LEFT)
			variable = 1;
		else
			variable = -1;
		
		if (switchNear == start) {
			addSequential(new AutoMove(1, 1, 0));
			addSequential(new AutoTurn(variable * 90));
			addSequential(new AutoMove(1, 1, 0));
			addSequential(new AutoDropBox());
		} else if (scale == start) {
			addSequential(new AutoMove(10, 1, 0));
			addSequential(new AutoTurn(variable * 90));
			addSequential(new AutoMove(1, 1, 0));
			addSequential(new AutoDropBox());
		} else
			addSequential(new AutoMove(1, 1, 0));

	}

	private void middle() {
		double angle;
		if (switchNear == Position.LEFT) /* TODO: figure me out */
			angle = 60;
		else
			angle = -60;
		
		addSequential(new AutoMove(1, 1, 0));
		addSequential(new AutoTurn(angle));
		addSequential(new AutoMove(1, 1, 0));
		addSequential(new AutoTurn(-angle));
		addSequential(new AutoMove(1, 1, 0));
		addSequential(new AutoDropBox());
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
