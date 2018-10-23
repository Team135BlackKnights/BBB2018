package org.usfirst.frc.team135.robot.commands.tele;

import org.usfirst.frc.team135.robot.Robot;
import org.usfirst.frc.team135.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command {
	public DriveWithJoystick() 
	{
		requires(Robot.drivetrain);
		setTimeout(0.5f);
	}
	protected void execute() 
	{
		Robot.drivetrain.TankDrive(-Robot.oi.GetLeftJoystickValues()[RobotMap.K_OI.GETY], -Robot.oi.GetRightJoystickValues()[RobotMap.K_OI.GETY]);
	}

	@Override
	protected boolean isFinished() 
	{
		return isTimedOut();
	}
	protected void end() 
	{
		Robot.drivetrain.TankDrive(0, 0);
	}
	protected void interrupted() 
    {
    	this.end();
    }
}
