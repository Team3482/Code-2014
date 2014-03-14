package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3482.Awesome.Robot;

public class Pass extends CommandGroup {

	public Pass() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

		requires(Robot.wheelPickup);
		addParallel(new Drive());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//TODO: make needed checks (arms need to be up)
		Robot.wheelPickup.expelForwards();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		//stops the ball intake wheels from running
		Robot.wheelPickup.stopWheels();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
