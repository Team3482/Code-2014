package org.usfirst.frc3482.Awesome.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3482.Awesome.Robot;
import java.lang.Thread;

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
		requires(Robot.camera);

		// Grab an image and process it
		try {
			Robot.camera.processImage();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Move up to the goal immediately, regardless of which targets were detected
        addSequential(new Move(0.8, 0.0, 3.0));
		addSequential(new Pass());
    }
}

