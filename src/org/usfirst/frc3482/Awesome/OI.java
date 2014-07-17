package org.usfirst.frc3482.Awesome;

import org.usfirst.frc3482.Awesome.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

   /*----------- Xbox Controller ------------*/
    public Joystick       joystick;
    public Trigger        pullBackTrigger;
    public Trigger        shootTrigger;
    public JoystickButton winchReverseButton;
    public JoystickButton aimButton;
    public JoystickButton passFrontButton;
    public JoystickButton loadFrontButton;
    public JoystickButton stopButton;
	public JoystickButton wheelsFrontButton;
	public JoystickButton configureCamera;
	public JoystickButton extendedPassFrontButton;
	public JoystickButton extendedPassBackButton;
	public JoystickButton loadBackButton;

	double threshold = 0.8;
	public class leftTrigger extends Trigger {
		public boolean get() {
			double triggerAxis = joystick.getRawAxis(3);
			if(triggerAxis > threshold) {
				return true;
			} else {
				return false;
			}
		}
	}
	public class rightTrigger extends Trigger {
		public boolean get() {
			double triggerAxis = joystick.getRawAxis(3);
			if(triggerAxis < -threshold) {
				return true;
			} else {
				return false;
			}
		}
	}

   /*----------- Arcade Buttons -------------*/
    public Joystick       buttons;
    public JoystickButton easyWinch;
    public JoystickButton easyShoot;

    public OI() {
        joystick = new Joystick(1);
        buttons = new Joystick(2);

        // stop      - back button: stops robot when pressed
        // load      - right bumper: stays extended while held
        // pass      - a: passes ball forwards while held + arms up
		// camera    - x: configure the camera
        // pullBack  - left trigger
        // shoot     - right trigger
        // aim       - right stick press (this doesn't do anything currently)
        
		// Arcade buttons
		easyWinch = new JoystickButton(buttons, 1);
        easyWinch.whileHeld(new PullBack());
        easyShoot = new JoystickButton(buttons, 2);
        easyShoot.whenPressed(new Shoot());
		configureCamera = new JoystickButton(buttons, 3);
		configureCamera.whenPressed(new ConfigureCamera());
//rb front lb back
		// joystick buttons
        loadFrontButton = new JoystickButton(joystick, 6);
        loadFrontButton.whileHeld(new LoadFront());
		loadBackButton = new JoystickButton(joystick, 5);
        loadBackButton.whileHeld(new LoadBack());
		extendedPassFrontButton = new JoystickButton(joystick, 2);
		extendedPassFrontButton.whileHeld(new ExtendedPassBoth());
		wheelsFrontButton = new JoystickButton(joystick, 7);
        wheelsFrontButton.whileHeld(new RetrieveBallBoth());
        passFrontButton = new JoystickButton(joystick, 1);
        passFrontButton.whileHeld(new PassBoth());
        winchReverseButton = new JoystickButton(joystick, 8);
        winchReverseButton.whileHeld(new ReverseWinch());
        //aimButton = new JoystickButton(joystick, 10);
        //aimButton.whenPressed(new PositionRobot());
		configureCamera = new JoystickButton(joystick, 3);
		configureCamera.whenPressed(new ConfigureCamera());
		extendedPassBackButton = new JoystickButton(joystick, 4);
		extendedPassBackButton.whileHeld(new ExtendedPassBack());

		pullBackTrigger = new leftTrigger();
		pullBackTrigger.whileActive(new PullBack());
		shootTrigger = new rightTrigger();
		shootTrigger.whenActive(new Shoot());


        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous", new Autonomous());

        SmartDashboard.putData("Drive", new Drive());

        SmartDashboard.putData("Pass", new PassBoth());

        SmartDashboard.putData("Load", new LoadFront());

        SmartDashboard.putData("Aim", new PositionRobot());

        SmartDashboard.putData("Pull Back", new PullBack());

        SmartDashboard.putData("Shoot", new Shoot());

        SmartDashboard.putData("Move", new Move());

        SmartDashboard.putData("Stop", new Stop());

    }
}
