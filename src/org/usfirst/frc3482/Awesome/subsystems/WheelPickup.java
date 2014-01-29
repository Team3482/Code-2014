package org.usfirst.frc3482.Awesome.subsystems;

import org.usfirst.frc3482.Awesome.RobotMap;
import org.usfirst.frc3482.Awesome.commands.*;
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.command.Subsystem;

public class WheelPickup extends Subsystem {

    Compressor compressor = RobotMap.wheelPickupCompressor;
    DoubleSolenoid cylinderRight = RobotMap.cylinderRight;
    DoubleSolenoid cylinderLeft = RobotMap.cylinderLeft;
    Relay spike = RobotMap.wheelPickupSpike;
    SpeedController wheels = RobotMap.wheelPickupWheels;
    
    boolean isCompressing = false;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new RunWheels());
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void stopWheels() {
        wheels.set(0.0);
    }
    public void forwardWheels() {
        wheels.set(1.0);
    }
    public void reverseWheels() {
        wheels.set(-1.0);
    }
    
    public void startCompressor() {
        if(!isCompressing) {
            compressor.start();
        }
    }
    public void stopCompressor() {
        compressor.stop();
        isCompressing = false;
    }
    public void extend() {
        cylinderRight.set(DoubleSolenoid.Value.kForward);
        cylinderLeft.set(DoubleSolenoid.Value.kForward);
    }
    public void retract() {
        cylinderRight.set(DoubleSolenoid.Value.kReverse);
        cylinderLeft.set(DoubleSolenoid.Value.kReverse);
    }
    public void turnOff() {
        cylinderRight.set(DoubleSolenoid.Value.kOff);
        cylinderLeft.set(DoubleSolenoid.Value.kOff);
    }
    
}