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
		requires(Robot.wheelPickup);
		addParallel(new Drive());
		//if(Robot.oi.loadBackButton.get()) {
		//	addParallel(new LoadBack());
		//}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		// extends arms to allow for catapult to shoot out
		Robot.wheelPickup.extendArmsFront();
		Robot.wheelPickup.extendArmsBack();
		Timer.delay(1);
		// disengages clutch and reverse pulls to help the catapult release
		Robot.catapult.disengageClutch();
		Robot.catapult.reversePull();
		Timer.delay(0.25);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.wheelPickup.retractArmsFront();
		Robot.wheelPickup.retractArmsBack();
		Robot.catapult.stopPull();
		//Robot.catapult.engageClutch();
		
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
