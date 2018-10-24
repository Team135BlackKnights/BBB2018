package org.usfirst.frc.team135.robot.commands.tele;

import org.usfirst.frc.team135.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class GrabMandibles extends InstantCommand
{
    public GrabMandibles() 
    {
    	requires(Robot.intake);
    }
    @Override
	protected void execute() 
    {
    	Robot.intake.ActivateClaw(DoubleSolenoid.Value.kForward);
    }
    @Override
	protected boolean isFinished() 
    {
        return false;
    }
    @Override
	protected void interrupted() 
    {
    }
}