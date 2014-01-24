package org.usfirst.frc3482.Awesome.subsystems;

import org.usfirst.frc3482.Awesome.RobotMap;
import org.usfirst.frc3482.Awesome.commands.*;
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Catapult extends Subsystem {
    SpeedController pull = RobotMap.catapultPull;

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new Shoot());
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void startPull(){
        pull.set(1.0);
    }
    public void stopPull(){
        pull.set(0.0);
    }
    public void release() {
        //TODO:Release catapult
    }
}


