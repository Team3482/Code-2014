package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3482.Awesome.Robot;

public class  Autonomous extends CommandGroup {
    
    private boolean isFinished = false;
    public Autonomous() {
        // Use requires() here to declare subsystem dependencies
	requires(Robot.camera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        addParallel(new PullBack());
        addSequential(new Aim());
        addSequential(new Shoot());
        addSequential(new Move(2,0,5));
        isFinished = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
