/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc3482.Awesome.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoShoot extends CommandGroup {

	public AutoShoot() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.camera);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		try {
			Robot.camera.initCamera();
			System.out.println("Initialized Camera");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		try {
			System.out.println("Processing image");
			Robot.camera.processImage();
			if (Robot.camera.foundHorizontal() && Robot.camera.foundVertical()) {
				addSequential(new PullBack());
			} else {
				Timer.delay(5);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
