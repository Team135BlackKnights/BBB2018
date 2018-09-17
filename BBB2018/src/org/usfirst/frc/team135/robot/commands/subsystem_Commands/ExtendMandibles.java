
package org.usfirst.frc.team135.robot.commands.subsytem_Commands;

import org.usfirst.frc.team135.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class ExtendMandibles extends Command 
{
    public ExtendMandibles() 
    {
    	requires(Robot.intake);
    }

    protected void initialize() 
    {
    	Robot.intake.MoveMandibles(DoubleSolenoid.Value.kForward);
    }

    protected void execute() 
    {
    }
    
    protected boolean isFinished() 
    {
        return true;
    }

    protected void end() 
    {
    }
    
    protected void interrupted() {
    }
}