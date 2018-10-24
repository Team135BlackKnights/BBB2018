package org.usfirst.frc.team135.robot.commands.camera;

import org.usfirst.frc.team135.robot.Robot;
import org.usfirst.frc.team135.robot.RobotMap;

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

    @Override
	protected void initialize()
    {
    	//  Initializing Limelight
    	/*
    	Robot.limelight.SetCameraPipeline(RobotMap.LIMELIGHT.YELLOW_BLOCK_PIPELINE);
    	Robot.limelight.SetCameraMode(RobotMap.LIMELIGHT.VISION_PROCESSOR);
    	Robot.limelight.SetLEDMode(RobotMap.LIMELIGHT.LED_OFF);  //  Turns off LED to Track the Yellow Block
    	*/
    }

    @Override
	protected void execute()
    {
    	//limelightData = Robot.limelight.GetLimelightData();
    	Robot.drivetrain.CurvatureDrive(1.0, limelightData[RobotMap.LIMELIGHT.HORIZONTAL_OFFSET] * LIMELIGHT_DRIVE_STRAIGHT_P_VALUE);
    }

    @Override
	protected boolean isFinished()
    {
        return (limelightData[RobotMap.LIMELIGHT.TARGET_AREA] >= TARGET_AREA_THRESHOLD);
    }

    @Override
	protected void end()
    {
    	System.out.println("Finished");
    	Robot.drivetrain.TankDrive(0.0, 0.0);
    }

    @Override
	protected void interrupted()
    {
    	this.end();
    }
}