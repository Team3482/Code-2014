package org.usfirst.frc3482.Awesome.subsystems;

import org.usfirst.frc3482.Awesome.RobotMap;
import org.usfirst.frc3482.Awesome.commands.*;
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.command.Subsystem;

public class WheelPickup extends Subsystem {

	Compressor compressor = RobotMap.wheelPickupCompressor;
	DoubleSolenoid cylinderArms = RobotMap.cylinderArms;
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
	double wheelSpeed = 1.0;
	public void runWheelsInward() {
		frontWheels.set(wheelSpeed);
		backWheels.set(-wheelSpeed);
	}
	public void stopWheels() {
		frontWheels.set(0);
		backWheels.set(0);
	}
	public void expelForwards() {
		// TODO: run both wheels in the same direction to push the ball out
		frontWheels.set(-wheelSpeed);
		backWheels.set(-wheelSpeed);
	}

	// Arm functions
	public void extendArms() {
		cylinderArms.set(DoubleSolenoid.Value.kForward);
	}
	public void ventArms() {
		cylinderArms.set(DoubleSolenoid.Value.kOff);
	}
	public void retractArms() {
		cylinderArms.set(DoubleSolenoid.Value.kReverse);
		runWheelsInward();
		Timer.delay(0.2);
		stopWheels();
	}
}