package org.usfirst.frc3482.Awesome;

import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class RobotMap {

	public static DigitalInput catapultSensor;
	public static AnalogChannel ultrasonicSensor;
	public static Compressor wheelPickupCompressor;
	public static DoubleSolenoid cylinderArms;
	public static DoubleSolenoid cylinderArmBack;
	public static Relay wheelPickupSpike;
	public static SpeedController wheelPickupFrontWheels;
	public static SpeedController wheelPickupBackWheels;
	public static SpeedController catapultPull;
	public static DoubleSolenoid cylinderCatapultClutch;
	public static DoubleSolenoid cylinderCatapultRatchet;
	public static SpeedController chassisDriveFrontRight;
	public static SpeedController chassisDriveFrontLeft;
	public static SpeedController chassisDriveBackRight;
	public static SpeedController chassisDriveBackLeft;
	public static RobotDrive chassisRobotDrive;

	public static void init() {
		catapultSensor = new DigitalInput(2);
		ultrasonicSensor = new AnalogChannel(3);

		wheelPickupCompressor = new Compressor(1, 1);

		// Both pneumatic arms are connected to this solenoid:
		cylinderArms = new DoubleSolenoid(1, 2);
		cylinderArmBack = new DoubleSolenoid(3, 4);
		cylinderCatapultClutch = new DoubleSolenoid(8, 6);
		//cylinderCatapultRatchet = new DoubleSolenoid(5, 8);

		wheelPickupFrontWheels = new Talon(1, 6);
		LiveWindow.addActuator("Wheel Pickup", "Wheels", (Talon) wheelPickupFrontWheels);

		wheelPickupBackWheels = new Talon(1, 7);
		LiveWindow.addActuator("Wheel Pickup", "Wheels", (Talon) wheelPickupBackWheels);

		catapultPull = new Talon(1, 5);
		LiveWindow.addActuator("Catapult", "Pull", (Talon) catapultPull);

		chassisDriveFrontRight = new Talon(1, 3);
		LiveWindow.addActuator("Chassis", "Drive Front Right", (Talon) chassisDriveFrontRight);

		chassisDriveFrontLeft = new Talon(1, 1);
		LiveWindow.addActuator("Chassis", "Drive Front Left", (Talon) chassisDriveFrontLeft);

		chassisDriveBackRight = new Talon(1, 4);
		LiveWindow.addActuator("Chassis", "Drive Back Right", (Talon) chassisDriveBackRight);

		chassisDriveBackLeft = new Talon(1, 2);
		LiveWindow.addActuator("Chassis", "Drive Back Left", (Talon) chassisDriveBackLeft);

		chassisRobotDrive = new RobotDrive(chassisDriveFrontLeft, chassisDriveBackLeft,
			chassisDriveFrontRight, chassisDriveBackRight);

		chassisRobotDrive.setSafetyEnabled(false);
		chassisRobotDrive.setExpiration(0.1);
		chassisRobotDrive.setSensitivity(0.5);
		chassisRobotDrive.setMaxOutput(1.0);

	}
}
