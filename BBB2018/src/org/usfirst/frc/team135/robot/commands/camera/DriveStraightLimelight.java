package org.usfirst.frc.team135.robot.commands.camera;

import org.usfirst.frc.team135.robot.Robot;
import org.usfirst.frc.team135.robot.RobotMap;
import org.usfirst.frc.team135.robot.subsystems.Limelight;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightLimelight extends Command {

	//  Creates an Array to Store the Data from the Limelight
	private double[] limelightData = new double[RobotMap.LIMELIGHT.NUMBER_OF_LIMELIGHT_CHARACTERISTICS];
	
	private final double LIMELIGHT_DRIVE_STRAIGHT_P_VALUE = .045;
	private final double TARGET_AREA_THRESHOLD = 60;
	
    public DriveStraightLimelight()
    {
    	requires(Robot.drivetrain); 	
    }

    protected void initialize()
    {
    	//  Initializing Limelight
    	/*
    	Robot.limelight.SetCameraPipeline(RobotMap.LIMELIGHT.YELLOW_BLOCK_PIPELINE);
    	Robot.limelight.SetCameraMode(RobotMap.LIMELIGHT.VISION_PROCESSOR);
    	Robot.limelight.SetLEDMode(RobotMap.LIMELIGHT.LED_OFF);  //  Turns off LED to Track the Yellow Block
    	*/
    }

    protected void execute()
    {
    	//limelightData = Robot.limelight.GetLimelightData();
    	Robot.drivetrain.CurvatureDrive(1.0, limelightData[RobotMap.LIMELIGHT.HORIZONTAL_OFFSET] * LIMELIGHT_DRIVE_STRAIGHT_P_VALUE);
    }

    protected boolean isFinished()
    {
        return (limelightData[RobotMap.LIMELIGHT.TARGET_AREA] >= TARGET_AREA_THRESHOLD);
    }

    protected void end()
    {
    	System.out.println("Finished");
    	Robot.drivetrain.TankDrive(0.0, 0.0);
    }

    protected void interrupted()
    {
    	this.end();
    }
}