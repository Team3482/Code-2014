/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3482.Awesome.Robot;
/**
 *
 * @author
 * Westmont
 * Robotics
 */

public class LoadBack extends CommandGroup {
    
    public LoadBack() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.wheelPickup); 
        // allow driving to happen while load executes
        addParallel(new Drive());
        // run the wheels while loading
        addParallel(new RunBackWheels());
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.wheelPickup.extendFrontArm();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.wheelPickup.retractBackArm();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
