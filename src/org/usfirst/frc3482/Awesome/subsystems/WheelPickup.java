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
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    //stops running the intake wheels
    public void stopWheels() {
        wheels.set(0.0);
    }
    //starts running the intake wheels - forward
    public void forwardWheels() {
        wheels.set(1.0);
    }
    //starts running the intake wheels - reverse
    public void reverseWheels() {
        wheels.set(-1.0);
    }
    
    //starts the compressor
    public void startCompressor() {
        if(!isCompressing) {
            compressor.start();
        }
    }
    //stops the compressor
    public void stopCompressor() {
        compressor.stop();
        isCompressing = false;
    }
    //extends the wheel intake pistons
    public void extend() {
        cylinderRight.set(DoubleSolenoid.Value.kForward);
        cylinderLeft.set(DoubleSolenoid.Value.kForward);
    }
    //retracts the wheel intake pistons
    public void retract() {
        cylinderRight.set(DoubleSolenoid.Value.kReverse);
        cylinderLeft.set(DoubleSolenoid.Value.kReverse);
    }
    //turns off the wheel intake pistons
    public void turnOff() {
        cylinderRight.set(DoubleSolenoid.Value.kOff);
        cylinderLeft.set(DoubleSolenoid.Value.kOff);
    }
    
}