package org.usfirst.frc.team135.robot.commands.camera;

import org.usfirst.frc.team135.robot.Robot;
import org.usfirst.frc.team135.robot.RobotMap;
import org.usfirst.frc.team135.robot.RobotMap.LIMELIGHT;
import org.usfirst.frc.team135.robot.subsystems.DriveTrain;
import org.usfirst.frc.team135.robot.subsystems.Limelight;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnTowardsObject extends Command {

	private double[] limelightData = new double[RobotMap.LIMELIGHT.NUMBER_OF_LIMELIGHT_CHARACTERISTICS];
	
	private boolean targetExists = false;
	private final double X_THRESHOLD_TO_STOP_TURNING = 20.0;
	
    public TurnTowardsObject()
    {
    	//requires(Robot.limelight);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	//  Initializing Limelight
    	/*
    	Robot.limelight.SetCameraPipeline(RobotMap.LIMELIGHT..YELLOW_BLOCK_PIPELINE);
    	Robot.limelight.SetCameraMode(RobotMap.LIMELIGHT..VISION_PROCESSOR);
    	Robot.limelight.SetLEDMode(RobotMap.LIMELIGHT..LED_OFF);  //  Turns off LED to Track the Yellow Block
    	*/
    	targetExists = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
    	//targetExists = Robot.limelight.isTargetsExist();
    	//limelightData = Robot.limelight.GetLimelightData();
    	
    	if (targetExists == false)  //  If no target is detected, Turn Left
    	{
    		Robot.drivetrain.TankDrive(-1.0, 1.0);
    	}
    	else if (targetExists && limelightData[LIMELIGHT.HORIZONTAL_OFFSET] > 0.0)  // If Target is to the Right, turn to the Right
    	{
    		Robot.drivetrain.TankDrive(1.0, -1.0);
    	}
    	else if (targetExists && limelightData[LIMELIGHT.HORIZONTAL_OFFSET] < 0.0)  //  If Target is to the Left, turn to the Left
    	{
    		Robot.drivetrain.TankDrive(-1.0, 1.0);
    	}
    	else  //  If the Horizontal Offset is equal to 0.0, Don't Turn
    	{
    		Robot.drivetrain.TankDrive(0.0, 0.0);
    	}
    }

    protected boolean isFinished()
    {
        return (limelightData[RobotMap.LIMELIGHT.HORIZONTAL_OFFSET] <= X_THRESHOLD_TO_STOP_TURNING 
        		&& limelightData[RobotMap.LIMELIGHT.HORIZONTAL_OFFSET] >= -X_THRESHOLD_TO_STOP_TURNING
        		&& limelightData[RobotMap.LIMELIGHT.HORIZONTAL_OFFSET] != 0);
    }

    protected void end()
    {
    	Robot.drivetrain.TankDrive(0.0, 0.0);
    	targetExists = false;
    }

    protected void interrupted()
    {
    	this.end();
    }
}