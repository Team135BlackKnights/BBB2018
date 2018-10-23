package org.usfirst.frc.team135.robot.commands.tele;

import org.usfirst.frc.team135.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class PShiftT extends InstantCommand
{
    public PShiftT() 
    {
    	requires(Robot.intake);
    }
    protected void execute() 
    {
    	Robot.intake.MoveMandibles(DoubleSolenoid.Value.kForward);
    }
    protected boolean isFinished() 
    {
        return false;
    }
    protected void interrupted() 
    {
    }
}