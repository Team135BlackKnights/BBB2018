package org.usfirst.frc.team135.robot.commands.subsystem_Commands;

import org.usfirst.frc.team135.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class GrabMandibles extends Command
{
    public GrabMandibles() 
    {
    	requires(Robot.intake);
    }
    
    protected void initialize() 
    {
    	Robot.intake.ActivateClaw(DoubleSolenoid.Value.kForward);
    }
    protected void execute() 
    {
    }
    protected boolean isFinished() 
    {
        return false;
    }
    protected void end() 
    {
    }
    protected void interrupted() 
    {
    }
}