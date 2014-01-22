// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3482.Awesome;
    
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import java.util.Vector;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Compressor wheelPickupCompressor;
    public static DoubleSolenoid wheelPickupDoubleSolenoid;
    public static RobotDrive chassisRobotDrive;
    public static Relay wheelPickupSpike;
    public static SpeedController wheelPickupWheels;
    public static SpeedController catapultPull;
    public static SpeedController chassisDriveFrontRight;
    public static SpeedController chassisDriveFrontLeft;
    public static SpeedController chassisDriveBackRight;
    public static SpeedController chassisDriveBackLeft;
    public static RobotDrive chassisRobotDrive41;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        wheelPickupCompressor = new Compressor(1, 1, 1, 1);
	
        
        wheelPickupDoubleSolenoid = new DoubleSolenoid(1, 1, 2);      
	
        
        wheelPickupSpike = new Relay(1, 5);
	LiveWindow.addActuator("Wheel Pickup", "Spike", wheelPickupSpike);
        
        wheelPickupWheels = new Talon(1, 6);
	LiveWindow.addActuator("Wheel Pickup", "Wheels", (Talon) wheelPickupWheels);
        
        catapultPull = new Talon(1, 5);
	LiveWindow.addActuator("Catapult", "Pull", (Talon) catapultPull);
        
        chassisDriveFrontRight = new Talon(1, 1);
	LiveWindow.addActuator("Chassis", "Drive Front Right", (Talon) chassisDriveFrontRight);
        
        chassisDriveFrontLeft = new Talon(1, 2);
	LiveWindow.addActuator("Chassis", "Drive Front Left", (Talon) chassisDriveFrontLeft);
        
        chassisDriveBackRight = new Talon(1, 3);
	LiveWindow.addActuator("Chassis", "Drive Back Right", (Talon) chassisDriveBackRight);
        
        chassisDriveBackLeft = new Talon(1, 4);
	LiveWindow.addActuator("Chassis", "Drive Back Left", (Talon) chassisDriveBackLeft);
        
        chassisRobotDrive41 = new RobotDrive(chassisDriveFrontLeft, chassisDriveBackLeft,
              chassisDriveFrontRight, chassisDriveBackRight);
	
        chassisRobotDrive41.setSafetyEnabled(true);
        chassisRobotDrive41.setExpiration(0.1);
        chassisRobotDrive41.setSensitivity(0.5);
        chassisRobotDrive41.setMaxOutput(1.0);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
