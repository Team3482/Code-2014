package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3482.Awesome.Robot;

public class  Autonomous extends CommandGroup {
    private final boolean TRYING_TWO_BALL = true;
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

        requires(Robot.chassis);
        requires(Robot.camera);
        requires(Robot.wheelPickup);

        // Grab an image and process it
		if (!TRYING_TWO_BALL) {
			addSequential(new ProcessImage());
			System.out.println("Processing time: " + Robot.camera.getElapsedTime());
		}

        // Move up to the goal immediately, regardless of which targets were detected
        addSequential(new ExtendFront(), 1.0);
        addSequential(new Move(-0.8, 0.0, 3.0));
        addSequential(new AutoShoot());
        //Robot.wheelPickup.retractArms();
    }
}

