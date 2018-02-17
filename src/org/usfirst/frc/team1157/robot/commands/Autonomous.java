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
		boolean variable;
		if (start == Position.LEFT)
			variable = false;
		else
			variable = true;
		
		if (switchNear == start) {
			addSequential(new AutoMove(168, false));
			addSequential(new AutoTurn(variable));
			addSequential(new AutoMove(55.56, false));
			addSequential(new AutoDropBoxL());
		} else if (scale == start) {
			addSequential(new AutoMove(324, false));
			addSequential(new AutoTurn(variable));
			addSequential(new AutoMove(41.88, false));
			addSequential(new AutoDropBoxH());
		} else
			addSequential(new AutoMove(1, false));

	}

	private void middle() {
	    //TODO fix with 90 angles
		boolean switchLeft;
		if (switchNear == Position.LEFT) 
			switchLeft = true;
		else
			switchLeft = false;
		
		addSequential(new AutoMove(100, false));
		addSequential(new AutoTurn(switchLeft));
		addSequential(new AutoMove(68, false));
		addSequential(new AutoTurn(! switchLeft));
		addSequential(new AutoMove(40, false));
		addSequential(new AutoDropBoxL());
		
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
