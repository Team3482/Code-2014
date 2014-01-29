package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3482.Awesome.Robot;

public class  Drive extends Command {
    
    public Joystick xboxController = Robot.oi.joystick;
    public Drive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        // TODO: Check whether this is necessary
        Robot.chassis.invertMotors();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.chassis.driveWithXboxController(xboxController);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.chassis.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
