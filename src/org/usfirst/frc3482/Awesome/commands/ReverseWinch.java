/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc3482.Awesome.commands;

import org.usfirst.frc3482.Awesome.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author
 * Westmont
 * Robotics
 */
public class ReverseWinch extends Command {

	public ReverseWinch() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.catapult.disengageRatchet();
		Robot.catapult.reversePull();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.catapult.reversePull();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
