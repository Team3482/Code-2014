package org.usfirst.frc3482.Awesome.subsystems;

import org.usfirst.frc3482.Awesome.RobotMap;
import org.usfirst.frc3482.Awesome.commands.*;
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Catapult extends Subsystem {    
    SpeedController pull = RobotMap.catapultPull;
    DigitalInput catapultLimit = RobotMap.catapultSensor;
    DoubleSolenoid cylinderLock = RobotMap.cylinderCatapultLock;
    DoubleSolenoid cylinderRelease = RobotMap.cylinderCatapultRelease;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
	
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
    
    //TODO:edit function names
    
    //locks the catapult via pistons (clutch)
    public void lock() {
        cylinderLock.set(DoubleSolenoid.Value.kForward);
    }
    //unlocks the catapult via pistons (clutch)
    public void unlock() {
        cylinderLock.set(DoubleSolenoid.Value.kReverse);
    }
    //releases the catapult via piston (ratchet)
    public void releaseOpen() {
        cylinderRelease.set(DoubleSolenoid.Value.kForward);
    }
    //closes the catapult release via piston (ratchet)
    public void releaseClose() {
        cylinderRelease.set(DoubleSolenoid.Value.kReverse);
    }
}