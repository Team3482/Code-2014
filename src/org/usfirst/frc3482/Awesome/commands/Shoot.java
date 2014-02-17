package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3482.Awesome.Robot;

public class  Shoot extends CommandGroup {

    boolean isFinished = false;
    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        requires(Robot.catapult);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        /*if(Robot.catapult.isLimit()) {
            //unlocks the catapult via pistons
            Robot.catapult.disengageClutch();
            //allow time for piston to finish
            Timer.delay(0.1);
            //release the catapult via piston
            Robot.catapult.disengageRatchet();
        } else {
            //TODO: call pullback
        }*/
        Robot.catapult.disengageRatchet();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
        Timer.delay(0.5);
        Robot.catapult.engageClutch();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
