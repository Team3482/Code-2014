package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3482.Awesome.Robot;

public class  RunFrontWheels extends Command {
    // Runs wheels on top of rolling intake to bring ball into robot
    // Opposite of Pass.java
    public RunFrontWheels() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        requires(Robot.wheelPickup);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //sets the ball intake wheels to forward (1)
        Robot.wheelPickup.forwardFrontWheels();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        //stops the ball intake wheels from running
        Robot.wheelPickup.stopFrontWheels();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
