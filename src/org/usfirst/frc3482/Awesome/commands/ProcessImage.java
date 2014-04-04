/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc3482.Awesome.commands;

import org.usfirst.frc3482.Awesome.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Grabs images from the camera and processes them, storing results
 * in local Camera subsystem variables.
 */
public class ProcessImage extends Command {
	public ProcessImage() {
		requires(Robot.camera);
	}
	public void initialize() {
	}
	public void execute() {
		try {
			Robot.camera.processImage();
		} catch (Exception e) {
			System.out.println("bad things is happenings");
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
			e.printStackTrace();
		}
	}
	public boolean isFinished() {
		return true;
	}
	public void end() {
	}
	public void interrupted() {
	}
}
