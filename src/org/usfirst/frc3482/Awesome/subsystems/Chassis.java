package org.usfirst.frc3482.Awesome.subsystems;

import com.sun.squawk.util.Arrays;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.*;
import org.usfirst.frc3482.Awesome.RobotMap;
import org.usfirst.frc3482.Awesome.commands.*;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDOutput;

public class Chassis extends Subsystem {

	SpeedController driveFrontRight = RobotMap.chassisDriveFrontRight;
	SpeedController driveFrontLeft = RobotMap.chassisDriveFrontLeft;
	SpeedController driveBackRight = RobotMap.chassisDriveBackRight;
	SpeedController driveBackLeft = RobotMap.chassisDriveBackLeft;
	RobotDrive robotDrive = RobotMap.chassisRobotDrive;
	public DistancePIDSource distancePIDSource = new DistancePIDSource();
	public DistancePIDOutput distancePIDOutput = new DistancePIDOutput();

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public void initDefaultCommand() {
		setDefaultCommand(new Drive());

		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}
	//inverts the motors

	public void invertMotors() {
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
	}
	//drives the robot with a joystick - normal configuration

	public void driveWithJoystick(Joystick s) {

		double deadZone = .2;
		double xAxis = s.getAxis(Joystick.AxisType.kX);
		double yAxis = s.getAxis(Joystick.AxisType.kY);

		// X sensitivity set by slider, Y sensitivity set by knob
		double slider = SmartDashboard.getNumber("Slider 1");
		xAxis *= (slider / 100);
		double knob = s.getAxis(Joystick.AxisType.kZ);
		knob = 1 - (knob / 2);    // Format input from Z Axis
		yAxis *= knob;
		// If the X or Y axes are in the deadzone, flip them to zero.
		if (xAxis < deadZone && xAxis > -deadZone) {
			xAxis = 0;
		}
		if (yAxis < deadZone && yAxis > -deadZone) {
			yAxis = 0;
		}
		robotDrive.arcadeDrive(yAxis, xAxis);
	}
	//drives the robot with a joystick - xbox configuration

	public void driveWithXboxController(Joystick s) {
		double leftY = s.getRawAxis(2);
		double rightX = s.getRawAxis(4);
		//double sensitivity = SmartDashboard.getNumber("Slider 1");
		//sensitivity /= 100;
		double deadZone = 0.1;

		if (leftY < deadZone && leftY > -deadZone) {
			leftY = 0;
		}
		if (rightX < deadZone && rightX > -deadZone) {
			rightX = 0;
		}
		//leftY *= sensitivity;
		robotDrive.arcadeDrive(leftY, rightX);
	}
	//moves the robot to a location

	public void move(double moveValue, double rotateValue) {
		robotDrive.arcadeDrive(moveValue, rotateValue);
	}
	//stops the robot

	public void stop() {
		robotDrive.stopMotor();
	}
	//sets the safety

	public void setSafety(boolean n) {
		robotDrive.setSafetyEnabled(n);
	}

	public double getDistance() {
		// return distance in inches
		//System.out.println(RobotMap.ultrasonicSensor.getVoltage());
		double scalingFactor = 5.0 / 512;//volts/inch
		//System.out.println("Distance: " + RobotMap.ultrasonicSensor.getVoltage()/scalingFactor);
		double[] v = new double[100];
		for (int i = 0; i < 100; i++) {
			v[i] = RobotMap.ultrasonicSensor.getVoltage() / scalingFactor;
		}
		Arrays.sort(v);
		return v[49];
	}

	public class DistancePIDSource implements PIDSource {

		public double pidGet() {
			return getDistance();
		}
	}

	public class DistancePIDOutput implements PIDOutput {

		public void pidWrite(double output) {
			move(output, 0.0);
		}
	}
}
