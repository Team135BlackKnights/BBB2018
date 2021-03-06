package org.usfirst.frc.team135.robot.commands.camera;

import org.usfirst.frc.team135.robot.RobotMap.LIMELIGHT;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GetLimelightData extends Command {
	
	//  Creates an Array to Store the Data from the Limelight
	private double[] limelightData = new double[LIMELIGHT.NUMBER_OF_LIMELIGHT_CHARACTERISTICS];
	/*
    public GetLimelightData()
    {
    	requires(Robot.limelight);
    }

    protected void initialize() // Called just before this Command runs the first time
    {
    	Robot.limelight.SetCameraPipeline(LIMELIGHT.YELLOW_BLOCK_PIPELINE);
    	Robot.limelight.SetCameraMode(LIMELIGHT.VISION_PROCESSOR);
    	Robot.limelight.SetLEDMode(LIMELIGHT.LED_OFF);  //  Turns off LED to Track the Yellow Block
    }

    protected void execute() // Called repeatedly when this Command is scheduled to run
    {
    	limelightData = Robot.limelight.GetLimelightData();
    }
	 */
    @Override
	protected boolean isFinished() // Make this return true when this Command no longer needs to run execute()
    {
        return false;
    }

    @Override
	protected void end() // Called once after isFinished returns true
    {
    	
    }

    @Override
	protected void interrupted() // Called when another command which requires one or more of the same subsystems is scheduled to run
    {
    	
    }
    
}
