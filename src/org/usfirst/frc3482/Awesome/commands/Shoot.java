package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3482.Awesome.Robot;

public class Shoot extends CommandGroup {

    boolean isFinished = false;

    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.catapult);
		//if(Robot.oi.joystick.getRawButton(5)) {
		//if(Robot.chassis.isBackExtended(Robot.oi.joystick)) {
		//	addParallel(new LoadBack());
		//}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		//if(Robot.chassis.isBackExtended(Robot.oi.joystick)) {
		//	addParallel(new LoadBack());
		//}
		// extends arms to allow for catapult to shoot out
		
		// disengages clutch and reverse pulls to help the catapult release
		Robot.catapult.disengageClutch();
	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
		
		Robot.catapult.stopPull();
		//Robot.catapult.engageClutch();
		
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
