package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3482.Awesome.Robot;
import edu.wpi.first.wpilibj.Timer;

public class PullBack extends Command {
	// uses speed controller to pull catapult back

	public PullBack() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

		requires(Robot.catapult);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//starts pulling the catapult back with a motor
		Robot.catapult.engageClutch();
		System.out.println("Clutch engaged");
		Robot.catapult.engageRatchet();
		System.out.println("Ratchet engaged");
		Robot.catapult.startPull();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		//checks to see if the catapult is pulled back enough
		return Robot.catapult.isLimit();
	}

	// Called once after isFinished returns true
	protected void end() {
		//stops pulling the catapult back with a motor
		Robot.catapult.stopPull();
		Robot.catapult.engageRatchet();
		System.out.println("Ratchet engaged");
		//closes the release and lock the catapult via pistons
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
