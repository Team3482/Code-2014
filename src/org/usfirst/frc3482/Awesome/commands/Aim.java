package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.PIDController;
import org.usfirst.frc3482.Awesome.Robot;

//TODO: Aim and sensor for distance measurement
public class  Aim extends Command /*extends PIDCommand*/ {
	final double kP = 0.0;
	final double kI = 0.0;
	final double kD = 0.0;

	// distance we want the robot from the wall in inches
	final double setDistance = 84.0;
	//PIDController distance = new PIDController(kP, kI, kD,);
	
    public Aim() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        //requires(Robot.chassis);
    }


    // Called just before this Command runs the first time
	protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
