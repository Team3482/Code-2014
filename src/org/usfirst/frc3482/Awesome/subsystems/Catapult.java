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

    public void startPull(){
        pull.set(1.0);
    }
    public void stopPull(){
        pull.set(0.0);
    }
    public boolean isLimit() {
        return catapultLimit.get();
    }
    
    public void lock() {
        cylinderLock.set(DoubleSolenoid.Value.kForward);
    }
    public void unlock() {
        cylinderLock.set(DoubleSolenoid.Value.kReverse);
    }
    public void releaseOpen() {
        cylinderRelease.set(DoubleSolenoid.Value.kForward);
    }
    public void releaseClose() {
        cylinderRelease.set(DoubleSolenoid.Value.kReverse);
    }
}