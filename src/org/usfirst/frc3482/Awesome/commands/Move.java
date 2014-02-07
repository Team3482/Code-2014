package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3482.Awesome.Robot;

public class  Move extends Command {
    
    private boolean isFinished = false;
    private double forward, turn, time;
    public Move() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        requires(Robot.chassis);
        forward = 0.0;
        turn    = 0.0;
        time    = 0.0;
    }
    public Move(double forward, double turn, double time) {
        requires(Robot.chassis);
        this.forward = forward;
        this.turn = turn;
        this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //disabled saftey and moves to a location
        Robot.chassis.setSafety(false);
        Robot.chassis.move(forward, turn);
        //waits for a given time
        Timer.delay(time);
        //enables safety and stops
        Robot.chassis.move(0.0, 0.0);
        Robot.chassis.setSafety(true);
        isFinished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
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
