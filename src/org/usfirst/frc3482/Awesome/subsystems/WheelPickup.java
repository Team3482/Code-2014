package org.usfirst.frc3482.Awesome.subsystems;

import org.usfirst.frc3482.Awesome.RobotMap;
import org.usfirst.frc3482.Awesome.commands.*;
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.command.Subsystem;

public class WheelPickup extends Subsystem {

	Compressor compressor = RobotMap.wheelPickupCompressor;
	DoubleSolenoid cylinderArmsFront = RobotMap.cylinderArms;
	DoubleSolenoid cylinderArmsBack = RobotMap.cylinderArmBack;
	SpeedController frontWheels = RobotMap.wheelPickupFrontWheels;
	SpeedController backWheels = RobotMap.wheelPickupBackWheels;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public void initDefaultCommand() {
		
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		retractArms();
	}

	// Compressor Functions
	public void startCompressor() {
		compressor.start();
	}
	public void stopCompressor() {
		compressor.stop();
	}

	// Wheel functions
	double wheelSpeed = -1.0;
	public void runWheelsInward() {
		frontWheels.set(wheelSpeed);
	}
	public void stopFrontWheels() {
		frontWheels.set(0);
	}
	public void expelForwards() {
		frontWheels.set(-wheelSpeed);
	}
	
	public void runBackWheelsInward() {
		backWheels.set(-wheelSpeed);
	}
	public void stopBackWheels() {
		backWheels.set(0);
	}
	public void expelBackwards() {
		// TODO: run both wheels in the same direction to push the ball out
		backWheels.set(wheelSpeed);
	}

	// Arm functions
	public void extendArms() {
		cylinderArmsFront.set(DoubleSolenoid.Value.kForward);
	}
	public void ventArms() {
		cylinderArmsFront.set(DoubleSolenoid.Value.kOff);
	}
	public void retractArms() {
		cylinderArmsFront.set(DoubleSolenoid.Value.kReverse);
		runWheelsInward();
		Timer.delay(0.2);
		stopFrontWheels();
	}
	
	public void extendArmsBack() {
		cylinderArmsBack.set(DoubleSolenoid.Value.kForward);
	}
	public void ventArmsBack() {
		cylinderArmsBack.set(DoubleSolenoid.Value.kOff);
	}
	public void retractArmsBack() {
		cylinderArmsBack.set(DoubleSolenoid.Value.kReverse);
		runBackWheelsInward();
		Timer.delay(0.2);
		stopBackWheels();
	}
}