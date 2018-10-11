package org.usfirst.frc.team135.robot.commands.auto.singles;

import org.usfirst.frc.team135.robot.Robot;
import org.usfirst.frc.team135.robot.RobotMap;
import org.usfirst.frc.team135.robot.utilities.FunctionalDoubleManager;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class DriveForward extends InstantCommand implements RobotMap
{
	public static final int FORWARD = 1;
	public static final int BACKWARD = -1; 
	public static final double DRIVE_POWER = .7;
	
	private FunctionalDoubleManager _rangedSensor, _encoder;
	private Mode _driveMode;
	private double _targetDisplacement;
	
	private boolean _isFacingBackwards;

	private double _timeout;
	
	private enum Mode
	{
		RANGED_SENSOR,
		ENCODER,
		FUSED
	}
	
	public DriveForward(double targetDistance, FunctionalDoubleManager encoder, FunctionalDoubleManager sonarSensor, boolean isFacingBackwards, double timeout) 
	{
		super();
		requires(Robot.drivetrain);
		
		Robot.drivetrain.Reset
	}
	
}
