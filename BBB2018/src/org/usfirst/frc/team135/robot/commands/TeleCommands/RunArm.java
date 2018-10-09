package org.usfirst.frc.team135.robot.commands.TeleCommands;

import org.usfirst.frc.team135.robot.Robot;
import org.usfirst.frc.team135.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class RunArm extends Command
{
	
	public RunArm()
	{
		requires(Robot.arm);
	}
	protected void initialize()
	{
	}
	protected void excecute()
	{
		double power = Robot.oi.GetManipJoystickValues()[RobotMap.K_OI.GETY];
		Robot.arm.RunArmMotors(power);
	}
	@Override
	protected boolean isFinished() {
		return false;
	}	
}
