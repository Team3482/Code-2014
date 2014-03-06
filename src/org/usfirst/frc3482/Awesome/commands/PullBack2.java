/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3482.Awesome.Robot;

/**
 *
 * @author
 * Westmont
 * Robotics
 */
public class PullBack2 extends Command {

	public PullBack2() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.catapult);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.catapult.engageClutch();
		System.out.println("Clutch engaged");
		Robot.catapult.startPull();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.catapult.isLimit();
	}

	// Called once after isFinished returns true
	protected void end() {
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
