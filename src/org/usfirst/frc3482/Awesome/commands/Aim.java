package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3482.Awesome.Robot;

//TODO: Aim and sensor for distance measurement
public class Aim extends Command {
	double kP = 0.0;
	double kI = 0.0;
	double kD = 0.0;

	// distance we want the robot from the wall in inches
	final double setDistance = 84.0;
	private PIDController distance = new PIDController(kP, kI, kD, Robot.chassis.distancePIDSource, Robot.chassis.distancePIDOutput);
	
    public Aim() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.chassis);
    }


    // Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("aim initialized");
		distance.setSetpoint(setDistance);
		distance.setOutputRange(-1.0, 1.0);
		distance.setAbsoluteTolerance(1.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		SmartDashboard.putNumber("kP", kP);
		SmartDashboard.putNumber("kI", kI);
		SmartDashboard.putNumber("kD", kD);
		distance.enable();
		System.out.println("Error: " + distance.getError() + ", Result: " + distance.get());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
		distance.disable();
		distance.free();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		end();
    }
}
