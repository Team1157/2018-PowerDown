package org.usfirst.frc.team1157.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTestGroup extends CommandGroup {

	public AutoTestGroup() {

		addSequential(new AutoMove(72, false));
		addSequential(new AutoTurn(false));
		addSequential(new AutoMove(72, false));
		addSequential(new AutoTurn(false));
		addSequential(new AutoMove(72, false));
		addSequential(new AutoTurn(false));
		addSequential(new AutoMove(72, false));
		addSequential(new AutoMove(72, true));
		addSequential(new AutoTurn(true));
		addSequential(new AutoMove(72, true));
		addSequential(new AutoTurn(true));
		addSequential(new AutoMove(72, true));
		addSequential(new AutoTurn(true));
		addSequential(new AutoMove(72, true));
		addSequential(new LiftGoto(LiftGoto.LiftDestination.MIDDLE));
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

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
}
