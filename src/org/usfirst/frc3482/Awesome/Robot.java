package org.usfirst.frc3482.Awesome;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc3482.Awesome.commands.*;
import org.usfirst.frc3482.Awesome.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    Command teleopCommand;

    public static OI oi;
    public static WheelPickup wheelPickup;
    public static Catapult catapult;
    public static Chassis chassis;
    public static Camera camera;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		RobotMap.init();
        wheelPickup = new WheelPickup();
        catapult = new Catapult();
        chassis = new Chassis();
        camera = new Camera();
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
	
        // instantiate the command used for the autonomous period
		Robot.chassis.invertMotors();
        autonomousCommand = new Autonomous();
        teleopCommand = new Drive();
    }

	/**
	 * This begins the autonomous command, engages the winch clutch,
	 * and starts the compressor loop
	 */
    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
		        if (autonomousCommand != null) autonomousCommand.cancel();


    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
		DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser1, 1, "");
		DriverStationLCD.getInstance().updateLCD();
        wheelPickup.startCompressor();
				Robot.chassis.driveWithXboxController(Robot.oi.joystick);
		        Scheduler.getInstance().run();

    }

    /**
     * This function called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
