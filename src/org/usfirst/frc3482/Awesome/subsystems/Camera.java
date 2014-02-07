package org.usfirst.frc3482.Awesome.subsystems;

import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc3482.Awesome.RobotMap;
import org.usfirst.frc3482.Awesome.commands.*;
import edu.wpi.first.wpilibj.camera.*;
import edu.wpi.first.wpilibj.image.*;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Camera extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    AxisCamera cam;
    ColorImage img;
    BinaryImage filtered;

    // vison processing constants
    final int HUE_LOW =115;
    final int HUE_HIGH = 140;
    final int SATURATION_LOW = 215;
    final int SATURATION_HIGH = 255;
    final int VALUE_LOW = 215;
    final int VALUE_HIGH = 255;
    final int EROSIONS = 3;
    // Camera constants
    final String IP_ADDRESS = "10.34.82.11";
    final int BRIGHTNESS = 40;
    final int COLOR_LEVEL = 70;
    final int COMPRESSION = 20;
    final AxisCamera.ExposureT EXPOSURE = AxisCamera.ExposureT.flickerfree60;
    final AxisCamera.ExposurePriorityT EXPOSURE_PRIORITY = AxisCamera.ExposurePriorityT.imageQuality;
    final int MAX_FPS = 20;
    final AxisCamera.ResolutionT RESOLUTION = AxisCamera.ResolutionT.k640x480;
    final AxisCamera.RotationT ROTATION = AxisCamera.RotationT.k0;
    final AxisCamera.WhiteBalanceT WHITE_BALANCE = AxisCamera.WhiteBalanceT.fixedIndoor;
    
    
    
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
    }*/
    public void initCamera () throws AxisCameraException {
        cam = AxisCamera.getInstance(IP_ADDRESS);
        while(!cam.freshImage()) {
            Timer.delay(0.5);
        }
        cam.writeBrightness(BRIGHTNESS);
        cam.writeColorLevel(COLOR_LEVEL);
        cam.writeCompression(COMPRESSION);
        cam.writeExposureControl(EXPOSURE);
        cam.writeExposurePriority(EXPOSURE_PRIORITY);
        cam.writeMaxFPS(MAX_FPS);
        cam.writeResolution(RESOLUTION);
        cam.writeRotation(ROTATION);
        cam.writeWhiteBalance(WHITE_BALANCE);
    }
    
    public void processImage() throws AxisCameraException, NIVisionException {
        // get image from camera and filter it according to size and color so it
        // shows retroflective tape only
        // then convert it to a binary image
        // determine whether image is hot or not; whether veritcal target is in
        // the area (determined by the pixel ratio from horizontal target that is always there)
        // return hot or not
        img = cam.getImage();
        img.write("/tmp/original.png");
        filtered = img.thresholdHSV(HUE_LOW, HUE_HIGH, SATURATION_LOW, SATURATION_HIGH, VALUE_LOW, VALUE_HIGH);
        filtered.write("/tmp/filtered.png");
        filtered.removeSmallObjects(true, EROSIONS);
        filtered.write("/tmp/size_filtered.png");
        filtered.convexHull(false);
        
    }
    
    //TODO:finish scoreImage()
    public void scoreImage(BinaryImage img) throws NIVisionException {
        int[] scoredBlobs;
        ParticleAnalysisReport[] blobs = img.getOrderedParticleAnalysisReports(3);
        for(int i = 0; i < blobs.length; i++) {
            int aspectRatio = blobs[i].boundingRectHeight / blobs[i].boundingRectWidth;
            
        // have not initialized or returned scoredBlobs yet
        }
    }
}

