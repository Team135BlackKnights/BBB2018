package org.usfirst.frc.team135.robot.commands.tele;

import org.usfirst.frc.team135.robot.Robot;
import org.usfirst.frc.team135.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class RunArm extends Command
{
	public RunArm()
	{
		requires(Robot.arm);
		setTimeout(0.5f);
	}
	@Override
	protected void execute()
	{
		Robot.arm.RunArmMotors(.5 * Robot.oi.GetManipJoystickValues()[RobotMap.K_OI.GETY]);
		//Robot.arm.maintainPosition();
	}
	@Override
	protected boolean isFinished() 
	{
		return isTimedOut();
	}	
	@Override
	protected void end()
	{
		Robot.arm.RunArmMotors(0.0f);
	}
	@Override
	protected void interrupted()
	{
		end();
	}
}
