package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3482.Awesome.Robot;

public class  Autonomous extends CommandGroup {
    
    public Autonomous() {
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
        //requires(Robot.camera);
        requires(Robot.chassis);
        //addParallel(new PullBack());
        addParallel(new Aim());
        //System.out.println("Calling autoshoot");
        //addSequential(new AutoShoot());
        // pulls back catapult and checks if goal is hot
        // If it is hot, shoot. If not, wait 5 seconds and then shoot
        /*try {
            System.out.println("Distance: " + Robot.chassis.getDistance());
            System.out.println("Processing image");
            if (Robot.camera.processImage()) {
                addSequential(new Shoot());
            } else {
                Timer.delay(5);
                addSequential(new Shoot());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
        /*addParallel(new PullBack());
        addSequential(new Aim());
        addSequential(new Shoot());
        addSequential(new Move(2,0,5));*/
    }
}

