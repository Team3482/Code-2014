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

    
    public JoystickButton shootButton;
    public JoystickButton aimButton;
    public JoystickButton pullBackButton;
    public JoystickButton runWheelsButton;
    public JoystickButton passButton;
    public JoystickButton loadButton;
    public JoystickButton stopButton;
    public Joystick joystick;

    public OI() {

        joystick = new Joystick(1);
        
        stopButton = new JoystickButton(joystick, 8);
        stopButton.whenPressed(new Stop());
        loadButton = new JoystickButton(joystick, 2);
        loadButton.whenPressed(new Load());
        passButton = new JoystickButton(joystick, 1);
        passButton.whileHeld(new Pass());
        runWheelsButton = new JoystickButton(joystick, 5);
        runWheelsButton.whileHeld(new RunWheels());
        pullBackButton = new JoystickButton(joystick, 3);
        pullBackButton.whenPressed(new PullBack());
        aimButton = new JoystickButton(joystick, 6);
        aimButton.whileHeld(new Aim());
        shootButton = new JoystickButton(joystick, 4);
        shootButton.whenPressed(new Shoot());

	    
        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous", new Autonomous());

        SmartDashboard.putData("Drive", new Drive());

        SmartDashboard.putData("Pass", new Pass());

        SmartDashboard.putData("Run Wheels", new RunWheels());

        SmartDashboard.putData("Load", new Load());

        SmartDashboard.putData("Aim", new Aim());

        SmartDashboard.putData("Pull Back", new PullBack());

        SmartDashboard.putData("Shoot", new Shoot());
        
        SmartDashboard.putData("Move", new Move());
        
        SmartDashboard.putData("Stop", new Stop());

    }
    
    public Joystick getJoystick() {
        return joystick;
    }

}

