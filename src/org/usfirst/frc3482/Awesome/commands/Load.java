package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3482.Awesome.Robot;

// Extends CommandGroup in order to be able to drive at the same time
public class Load extends CommandGroup {

	public Load() {
		requires(Robot.wheelPickup);
		// allow driving while load executes
		addParallel(new Drive());
		
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//extends the pistons for the wheel pickup system
		Robot.wheelPickup.runWheelsInward();
		Timer.delay(0.375);
		Robot.wheelPickup.extendArms();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		//retracts and sets the pistons off for the wheel pickup system
		Robot.wheelPickup.retractArms();
		Robot.wheelPickup.stopWheels();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
