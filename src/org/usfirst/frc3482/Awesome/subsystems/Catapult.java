package org.usfirst.frc3482.Awesome.subsystems;

import org.usfirst.frc3482.Awesome.RobotMap;
import org.usfirst.frc3482.Awesome.commands.*;
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Catapult extends Subsystem {    
    SpeedController pull = RobotMap.catapultPull;
    DigitalInput catapultLimit = RobotMap.catapultSensor;
    DoubleSolenoid cylinderClutch = RobotMap.cylinderCatapultClutch;
    DoubleSolenoid cylinderRatchet = RobotMap.cylinderCatapultRatchet;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
	engageClutch();
        disengageRatchet();
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    //starts pulling back the catapult with a motor
    public void startPull(){
        pull.set(1.0);
    }
    //stops pulling back the catapult with a motor
    public void stopPull(){
        pull.set(0.0);
    }
    //checks to see if the catapult has been pulled back enough
    public boolean isLimit() {
        return catapultLimit.get();
    }
    
    public void engageClutch() {
        cylinderClutch.set(DoubleSolenoid.Value.kForward);
    }
    public void disengageClutch() {
        cylinderClutch.set(DoubleSolenoid.Value.kReverse);
    }
    public void disengageRatchet() {
        cylinderRatchet.set(DoubleSolenoid.Value.kForward);
    }
    public void engageRatchet() {
        cylinderRatchet.set(DoubleSolenoid.Value.kReverse);
    }
}