package org.usfirst.frc3482.Awesome.subsystems;

import org.usfirst.frc3482.Awesome.RobotMap;
import org.usfirst.frc3482.Awesome.commands.*;
import edu.wpi.first.wpilibj.camera.*;
import edu.wpi.first.wpilibj.image.*;
import edu.wpi.first.wpilibj.networktables.*;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Camera extends Subsystem {

    /*AxisCamera camera = AxisCamera.getInstance();
    ColorImage image;
    NetworkTable table = NetworkTable.getTable("imagetable");*/
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    /*public void setupCamera() {
        camera.writeMaxFPS(4);
    }
    public void sendImage() throws AxisCameraException, NIVisionException {
        if (camera.freshImage()) {
            image = camera.getImage();
            table.putValue("cameraimage", image);
        }
        // TODO: Filter image -> binary image to send to DS
    }*/
}

