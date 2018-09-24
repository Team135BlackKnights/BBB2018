package org.usfirst.frc.team135.robot.commands.TeleCommands;

import org.usfirst.frc.team135.robot.OI;
import org.usfirst.frc.team135.robot.Robot;
import org.usfirst.frc.team135.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

@SuppressWarnings("unused")
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
		leftJoystickValue = Robot.oi.GetLeft()[RobotMap.K_OI.GETX];
		rightJoystickValue = Robot.oi.GetRight()[RobotMap.K_OI.GETX];
		
		Robot.drivetrain.TankDrive(leftJoystickValue, rightJoystickValue);
	}
	
	@Override
	protected boolean isFinished()
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
