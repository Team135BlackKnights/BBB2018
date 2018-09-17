package org.usfirst.frc.team135.robot.commands.subsystem_Commands;

import org.usfirst.frc.team135.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class RetractMandibles extends Command{

	public RetractMandibles()
	{
		requires(Robot.intake);
	}
	protected void initialize() {
		Robot.intake.MoveMandibles(DoubleSolenoid.Value.kReverse);
	}
	
	protected void exectute()
	{	
	}
	public boolean isFinished()
	{
		return true; 
	}
	protected void end()
	{
	}
	
	protected void interrupted() {
	}
}

