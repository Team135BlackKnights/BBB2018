package org.usfirst.frc.team135.robot.commands.subsystem_Commands;

import org.usfirst.frc.team135.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RunArm extends Command
{
	
	public RunArm()
	{
		requires(Robot.arm);
	}
	protected void initialize()
	{
		Robot.arm.InitializeMotors();
	}
	protected void excecute()
	{
		double[] power = Robot.oi.GetManip();
		Robot.arm.RunArmMotors(power);
	}
	@Override
	protected boolean isFinished() {
		return false;
	}	
}
