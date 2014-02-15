package org.usfirst.frc3482.Awesome.subsystems;

import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc3482.Awesome.RobotMap;
import org.usfirst.frc3482.Awesome.commands.*;
import edu.wpi.first.wpilibj.camera.*;
import edu.wpi.first.wpilibj.image.*;
import java.lang.Math;
import org.usfirst.frc3482.Awesome.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Camera extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    AxisCamera cam;
    ColorImage img;
    BinaryImage filtered;
    BinaryImage filteredSmall;
    BinaryImage filteredHull;
    // vison processing constants
    final int HUE_LOW = 107;
    final int HUE_HIGH = 148;
    final int SATURATION_LOW = 226;
    final int SATURATION_HIGH = 255;
    final int VALUE_LOW = 188;
    final int VALUE_HIGH = 255;
    final int EROSIONS = 3;
    final double IDEAL_ASPECT_RATIO_VERTICAL = 4.0/32;
    final double IDEAL_ASPECT_RATIO_HORIZONTAL = 23.5/4;
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
    //Camera Viewing constants
    final double HORIZONTAL_THETA = 67/2;
    //Target type constants
    final int NEITHER = 0;
    final int VERTICAL = 1;
    final int HORIZONTAL = -1;
    
    int[] result;
    public boolean foundVertical = false;
    public boolean foundHorizontal = false;
    
    public void initDefaultCommand() {
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        // if you stare too long into the public void, the public void stares also into you. -PHILOSOPHER 
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
        // initializes camera with settings defined in constants  
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
        filteredSmall = filtered.removeSmallObjects(true, EROSIONS);
        filteredSmall.write("/tmp/size_filtered.png");
        filteredHull = filtered.convexHull(false);
        filteredHull.write("/tmp/convex_hull_filtered.png");
        result = getTargetTypes(filtered);
        freeMemory();
        /*for(int i=0;i<result.length;i++) {
            if (result[i] == VERTICAL) {
                foundVertical = true;
            }
            else if (result[i] == HORIZONTAL) {
                foundHorizontal = true;
            }
        }*/
    }
    
    private int[] getTargetTypes(BinaryImage img) throws NIVisionException {
       // returns array of target types in the image
       ParticleAnalysisReport[] blobs = img.getOrderedParticleAnalysisReports();
       int[] scoredBlobs = new int[blobs.length];
       for(int i = 0; i < blobs.length; i++) {
           int boundingBoxArea = blobs[i].boundingRectHeight * blobs[i].boundingRectWidth;
           double blobArea = blobs[i].particleArea;
           double rectangularity = (blobArea / boundingBoxArea) * 100;
           // score that describes the similarity in aspect ratio between the blob and the ideal target 
           double aspectRatio = ((double)blobs[i].boundingRectWidth / (double)blobs[i].boundingRectHeight);
           System.out.println("vertical perfect: " + IDEAL_ASPECT_RATIO_VERTICAL);
           System.out.println("aspect ratio: " + aspectRatio);
           double percentDifferenceVertical = (Math.abs(IDEAL_ASPECT_RATIO_VERTICAL-aspectRatio)/IDEAL_ASPECT_RATIO_VERTICAL)*100;
           System.out.println("vertical percent: " + percentDifferenceVertical);
           double percentDifferenceHorizontal = (Math.abs(IDEAL_ASPECT_RATIO_HORIZONTAL-aspectRatio)/IDEAL_ASPECT_RATIO_HORIZONTAL)*100;
           System.out.println("horizontal percent: " + percentDifferenceHorizontal);
           //check if it is a vertical target, horizontal target or neither
           /*double aspectRatio = 
           double percentDifferenceVertical = 
           double percentDifferenceHorizontal = */
           int targetType = 2;
           if(rectangularity > 75) {
               if(percentDifferenceVertical < 25) {
                   targetType = VERTICAL;
               }
               else if(percentDifferenceHorizontal < 25) {
                   targetType = HORIZONTAL;
               }
           } else {
                targetType = NEITHER;
           }
           scoredBlobs[i] = targetType;
        }
       return scoredBlobs;
    }
    
    public void freeMemory() throws NIVisionException {
        img.free();
        filtered.free();
        filteredSmall.free();
        filteredHull.free();
    }
    
    public double getDistance() {
        // TODO: Find Distance To Target
        // M1013 has horizontal viewing angle of 67 degrees (2 theta)
        // use FOV, theta, w, T to find d
        // Tft/Tpixel = FOVft/FOVpixxel and FOVft = 2w = 2dtan(theta)
        // d = Tft*FOVpixel/(2Tpixeltan(theta))
        //https://wpilib.screenstepslive.com/s/3120/m/8731/l/90361-identifying-and-processing-the-targets
        double d=0.0;
        return d;
        //TODO: calibrate camera & code
    }
}

