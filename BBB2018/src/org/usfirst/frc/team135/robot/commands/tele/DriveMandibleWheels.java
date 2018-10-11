package org.usfirst.frc.team135.robot.commands.tele;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team135.robot.Robot;

public class DriveMandibleWheels extends Command {
	private int _power;
	
	public DriveMandibleWheels(boolean isInward)
	{
		this._power = (isInward) ? 1:-1;
		requires(Robot.intake);
	}
	
	protected void initialize()
	{
		Robot.intake.InitializeWheelMotors();
	}
	protected void exectute()
	{
		Robot.intake.DriveWheels(this._power);
	}
	
	protected boolean isFinished()
	{
		return false; 
	}
	
	protected void interupted()
	{
		end();
	}

}
