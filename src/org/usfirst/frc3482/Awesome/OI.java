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
	public class XboxController {
		
	}
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

    
    //public Trigger shootTrigger;
    //public Trigger pullBackTrigger
    public JoystickButton shootButton;
    public JoystickButton pullBackButton;
	public JoystickButton winchReverseButton;
    public JoystickButton aimButton;
    public JoystickButton frontPassButton;
    public JoystickButton backPassButton;
    public JoystickButton loadFrontButton;
    public JoystickButton stopButton;
    public Joystick joystick;
	

    public OI() {
        // xbox controller
        joystick = new Joystick(1);
        
        // axis 9 is left trigger
        // axis 10 is right trigger
        class XboxTrigger extends Trigger {
            Joystick controller;
            double threshold;
            int axis;
            public XboxTrigger(Joystick c, boolean rightTrigger, double triggerThreshold) {
                controller = c;
                threshold = triggerThreshold;
                if(rightTrigger) {
                    axis = 10;
                } else {
                    axis = 9;
                }
            }
            public boolean get() {
                System.out.println(controller.getRawAxis(axis));
                if(controller.getRawAxis(axis) >= threshold) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        // stop      - back button
        // loadFront - right bumper: this extends the front and keeps it there
        // loadBack  - left bumper: this retracts the front
        // frontPass - a
        // backPass  - b
        // pullBack  - left trigger TODO | actually x right now
        // aim       - right stick press
        // shoot     - right trigger TODO | actually y right now
        stopButton = new JoystickButton(joystick, 7);
        stopButton.whenPressed(new Stop());
        loadFrontButton = new JoystickButton(joystick, 6);
        loadFrontButton.whileHeld(new Load());
        frontPassButton = new JoystickButton(joystick, 1);
        frontPassButton.whileHeld(new FrontPass());
        backPassButton = new JoystickButton(joystick, 2);
        backPassButton.whileHeld(new BackPass());
        pullBackButton = new JoystickButton(joystick, 3);
        pullBackButton.whileHeld(new PullBack());
		winchReverseButton = new JoystickButton(joystick, 8);
		winchReverseButton.whileHeld(new ReverseWinch());
	    shootButton = new JoystickButton(joystick, 4);
        shootButton.whileHeld(new Shoot());
        aimButton = new JoystickButton(joystick, 10);
        aimButton.whenPressed(new Aim());
        
	    
        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous", new Autonomous());

        SmartDashboard.putData("Drive", new Drive());

        SmartDashboard.putData("Pass", new FrontPass());

        SmartDashboard.putData("Run Wheels", new RunFrontWheels());

        SmartDashboard.putData("Load", new Load());

        SmartDashboard.putData("Aim", new Aim());

        SmartDashboard.putData("Pull Back", new PullBack());

        SmartDashboard.putData("Shoot", new Shoot());
        
        SmartDashboard.putData("Move", new Move());
        
        SmartDashboard.putData("Stop", new Stop());

    }
}