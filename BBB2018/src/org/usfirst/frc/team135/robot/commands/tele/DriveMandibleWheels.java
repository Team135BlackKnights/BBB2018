package org.usfirst.frc.team135.robot.commands.tele;
import org.usfirst.frc.team135.robot.Robot;
import org.usfirst.frc.team135.robot.RobotMap.INTAKE;

import edu.wpi.first.wpilibj.command.Command;

public class DriveMandibleWheels extends Command {
	private int _power;
	
	public DriveMandibleWheels(boolean isInward)
	{
		this._power = (isInward) ? 1:-1;
		requires(Robot.intake);
		setTimeout(INTAKE.TIME_OUT_SECONDS);
	}
	protected void execute()
	{
		Robot.intake.DriveWheels(this._power);
	}
	protected boolean isFinished()
	{
		return false;
	}
	protected void end()
	{
		Robot.intake.DriveWheels(0);
	}
	protected void interupted()
	{
		end();
	}

}
