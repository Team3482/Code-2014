/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc3482.Awesome.Robot;

/**
 *
 * @author Team3482
 */
public class AutoShoot extends CommandGroup {
	
	public AutoShoot() {
        // Add Commands here:
		// e.g. addSequential(new Command1());
		//      addSequential(new Command2());
		// these will run in order.

        // To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		//      addSequential(new Command2());
		// Command1 and Command2 will run in parallel.
        // A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
		requires(Robot.wheelPickup);
		requires(Robot.camera);
	}
	protected void initialize() {
	}
	protected void execute() {
		double spinTime = 3;
		if(Robot.camera.foundVertical() && Robot.camera.foundHorizontal()) {
			// if both targets are found, then pass
			Robot.wheelPickup.expelForwards();
			Timer.delay(spinTime);
			Robot.wheelPickup.stopWheels();
		} else if(Robot.camera.foundVertical() && !Robot.camera.foundHorizontal()) {
			// Wait for 5.5 seconds, then shoot
			Timer.delay(5.5 - Robot.camera.getElapsedTime());

			Robot.wheelPickup.expelForwards();
			Timer.delay(spinTime);
			Robot.wheelPickup.stopWheels();
		} else {
			// otherwise just forget it and pass anyway
			Robot.wheelPickup.expelForwards();
			Timer.delay(spinTime);
			Robot.wheelPickup.stopWheels();

			// and print an error message
			System.out.println("No targets detected, defaulted.");
		}
	}
	protected boolean isFinished() {
		return true;
	}
	protected void end() {
	}
	protected void interrupted() {
	}
}
