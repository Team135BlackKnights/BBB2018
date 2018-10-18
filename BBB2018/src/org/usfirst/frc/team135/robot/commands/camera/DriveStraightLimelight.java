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
	
	private final double DRIVE_TRAIN_MOTOR_POWER = .35;
	private final double LIMELIGHT_DRIVE_STRAIGHT_P_VALUE = .045;
	private final double TARGET_AREA_THRESHOLD = 60;
	
    public DriveStraightLimelight()
    {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//***********requires(Robot.limelight);
    	requires(Robot.drivetrain);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
    	//  Initializing Drive Train
    	//Robot.drivetrain.InitializeCurvatureDrive();
    	
    	//  Initializing Limelight
    	/*
    	Robot.limelight.SetCameraPipeline(RobotMap.LIMELIGHT.YELLOW_BLOCK_PIPELINE);
    	Robot.limelight.SetCameraMode(RobotMap.LIMELIGHT.VISION_PROCESSOR);
    	Robot.limelight.SetLEDMode(RobotMap.LIMELIGHT.LED_OFF);  //  Turns off LED to Track the Yellow Block
    	*/
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
    	//limelightData = Robot.limelight.GetLimelightData();
    	//Robot.drivetrain.DriveStraightTowardsBlockWithVision(DRIVE_TRAIN_MOTOR_POWER, limelightData[RobotMap.LIMELIGHT.HORIZONTAL_OFFSET], LIMELIGHT_DRIVE_STRAIGHT_P_VALUE);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return (limelightData[RobotMap.LIMELIGHT.TARGET_AREA] >= TARGET_AREA_THRESHOLD);
    }

    // Called once after isFinished returns true
    protected void end()
    {
    	System.out.println("Finished");
    	Robot.drivetrain.TankDrive(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    	this.end();
    }
}