package org.usfirst.frc.team135.robot.commands;

import org.usfirst.frc.team135.robot.OI;
import org.usfirst.frc.team135.robot.Robot;
import org.usfirst.frc.team135.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveWithJoystick extends Command {

	private double leftJoystickValue;
	private double rightJoystickValue;
	
	public DriveWithJoystick()
	{
		requires(Robot.drivetrain);
	}
	
	protected void initialize()
	{}
	protected void excecute()
	{
		leftJoystickValue = Robot.oi.GetLeft()[0];
		rightJoystickValue = Robot.oi.GetRight()[1];
		
		Robot.drivetrain.TankDrive(leftJoystickValue, rightJoystickValue);
	}
	
	protected boolean isFinished()
	{
		return false;
	}
	
}
