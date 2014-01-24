package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3482.Awesome.Robot;

public class  Pass extends Command {

    public Pass() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        requires(Robot.wheelPickup);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.wheelPickup.reverseWheels();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {  
        Robot.wheelPickup.reverseWheels();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.wheelPickup.stopWheels();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
