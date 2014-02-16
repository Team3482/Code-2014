package org.usfirst.frc3482.Awesome.subsystems;

import org.usfirst.frc3482.Awesome.RobotMap;
import org.usfirst.frc3482.Awesome.commands.*;
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.command.Subsystem;

public class WheelPickup extends Subsystem {

    Compressor compressor = RobotMap.wheelPickupCompressor;
    DoubleSolenoid cylinderArmFront = RobotMap.cylinderArmFront;
    DoubleSolenoid cylinderArmBack = RobotMap.cylinderArmBack;
    SpeedController frontWheels = RobotMap.wheelPickupFrontWheels;
    SpeedController backWheels = RobotMap.wheelPickupBackWheels;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        retractFrontArm();
        retractBackArm();
    }
    
    //stops running the front intake wheels
    public void stopFrontWheels() {
        frontWheels.set(0.0);
    }
    //starts running the front intake wheels - forward
    public void forwardFrontWheels() {
        frontWheels.set(1.0);
    }
    //starts running the front intake wheels - reverse
    public void reverseFrontWheels() {
        frontWheels.set(-1.0);
    }
    // stops running back intake wheels
    public void stopBackWheels() {
        backWheels.set(0.0);
    }
    //starts running the back intake wheels - forward
    public void forwardBackWheels() {
        backWheels.set(1.0);
    }
    //starts running the back intake wheels - reverse
    public void reverseBackWheels() {
        backWheels.set(-1.0);
    }
    
    //starts the compressor
    public void startCompressor() {
        compressor.start();
    }
    //stops the compressor
    public void stopCompressor() {
        compressor.stop();
    }
    //extends the wheel intake pistons
    public void extendFrontArm() {
        cylinderArmFront.set(DoubleSolenoid.Value.kForward);
    }
    //retracts the wheel intake pistons
    public void retractFrontArm() {
        cylinderArmFront.set(DoubleSolenoid.Value.kReverse);
    }
    
    //extends the wheel intake pistons
    public void extendBackArm() {
        cylinderArmBack.set(DoubleSolenoid.Value.kForward);
    }
    //retracts the wheel intake pistons
    public void retractBackArm() {
        cylinderArmBack.set(DoubleSolenoid.Value.kReverse);
    }
    //turns off the wheel intake pistons
    public void turnOffFrontArm() {
        cylinderArmFront.set(DoubleSolenoid.Value.kOff);
    }
    
    //turns off the wheel intake pistons
    public void turnOffBackArm() {
        cylinderArmBack.set(DoubleSolenoid.Value.kOff);
    }
    
}