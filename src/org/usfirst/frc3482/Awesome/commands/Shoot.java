package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3482.Awesome.Robot;

public class  Shoot extends Command {

    boolean isFinished = false;
    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        requires(Robot.catapult);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.catapult.unlock();
        Timer.delay(0.1);
        Robot.catapult.releaseOpen();
        Timer.delay(0.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        isFinished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.catapult.releaseClose();
        Robot.catapult.lock();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
