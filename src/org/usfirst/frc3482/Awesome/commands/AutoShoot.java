/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc3482.Awesome.Robot;

/**
 *
 * @author Team3482
 */
public class AutoShoot extends CommandGroup {
	private final boolean TRYING_HIGH_GOAL = false;
        private final boolean TRYING_TWO_BALL = true;
	public AutoShoot() {
        // Add Commands here:
		// e.g. addSequential(new Command1());
		//      addSequential(new Command2());
		// these will run in order.

        // To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		//      addSequential(new Command2());
		// Command1 and Command2 will run in parallel.
        // A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
		requires(Robot.wheelPickup);
		requires(Robot.camera);
                requires(Robot.catapult);
               	}
	protected void initialize() {
	}
	protected void execute() {
		double spinTime = 3;
                if (!TRYING_TWO_BALL) {
                    if(Robot.camera.foundVertical() && Robot.camera.foundHorizontal()) {
                    // if both targets are found, then pass
                        if (!TRYING_HIGH_GOAL) {
                            Robot.wheelPickup.expelForwards();
                            Timer.delay(spinTime);
                            Robot.wheelPickup.stopFrontWheels();
                        } else {
                            addSequential(new PullBack());
                            addSequential(new Shoot());
                        }

                    } else if(Robot.camera.foundVertical() && !Robot.camera.foundHorizontal()) {
                        // Wait for 5.5 seconds, then shoot
                        Timer.delay(5.5 - Robot.camera.getElapsedTime());
                        if (!TRYING_HIGH_GOAL) {
                            Robot.wheelPickup.expelForwards();
                            Timer.delay(spinTime);
                            Robot.wheelPickup.stopFrontWheels();
                        } else {
                            addSequential(new PullBack());
                            addSequential(new Shoot());
                        }
                    } else {
                            // otherwise just forget it and pass anyway
                        if (!TRYING_HIGH_GOAL) {
                            Robot.wheelPickup.expelForwards();
                            Timer.delay(spinTime);
                            Robot.wheelPickup.stopFrontWheels();
                        } else {
                            addSequential(new PullBack());
                            addSequential(new Shoot());
                        }

                            // and print an error message
                        System.out.println("No targets detected, defaulted.");
                    }
                } else {
                    // TRYING TWO BALL AUTO
                    double pullTime = 0.6;
					System.out.println("TWO BALL AUTO RUNNING");
                    //pulls back for pullTime seconds
                    //addSequential(new PullBack(), pullTime);
					Robot.catapult.engageClutch();
					System.out.println("Clutch engaged");
					Robot.catapult.startPull();
					Timer.delay(pullTime);
					Robot.catapult.stopPull();
                    //Extends arms to shoot
                    Robot.wheelPickup.extendArmsBack();
                    Robot.catapult.disengageClutch();
                    Robot.catapult.reversePull();
                    Timer.delay(0.25);
                    Robot.catapult.stopPull();
                    //Delays and brings next ball in from front intake and 
                    //then retracts arms
                    Timer.delay(0.5);
                    Robot.wheelPickup.runWheelsInward();
                    Robot.wheelPickup.retractArms();
                    Timer.delay(1.0);
                    Robot.wheelPickup.stopFrontWheels();
                    Robot.wheelPickup.retractArmsBack();
                    // pulls back and shoots 
                    //addSequential(new PullBack(), pullTime);
					Robot.catapult.engageClutch();
					System.out.println("Clutch engaged");
					Robot.catapult.startPull();
					Timer.delay(pullTime);
					Robot.catapult.stopPull();
                    //addSequential(new Shoot());
					Robot.wheelPickup.extendArms();
					Robot.wheelPickup.extendArmsBack();
					Timer.delay(1);
					// disengages clutch and reverse pulls to help the catapult release
					Robot.catapult.disengageClutch();
					Robot.catapult.reversePull();
					Timer.delay(0.25);
					Robot.wheelPickup.retractArms();
					Robot.catapult.stopPull();
					Robot.wheelPickup.retractArmsBack();
                }
	}
	protected boolean isFinished() {
		return true;
	}
	protected void end() {
	}
	protected void interrupted() {
	}
}
