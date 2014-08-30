/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3482.Awesome.Robot;
import edu.wpi.first.wpilibj.Timer;


/**
 *
 * @author Team3482
 */
public class ExtendedPassBack extends CommandGroup {
	
	public ExtendedPassBack() {
		requires(Robot.wheelPickup);
		requires(Robot.chassis);
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
		addParallel(new Drive());
	}
		
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//extends the pistons for the wheel pickup system and runs wheels to propel ball out
		Robot.wheelPickup.expelBackwards();
		Robot.wheelPickup.extendArmsFront();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		//retracts and sets the pistons off for the wheel pickup system
		Robot.wheelPickup.retractArmsFront();
		Robot.wheelPickup.stopBackWheels();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}

