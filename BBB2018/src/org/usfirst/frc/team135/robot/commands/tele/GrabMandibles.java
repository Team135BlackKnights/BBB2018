package org.usfirst.frc.team135.robot.commands.tele;

import org.usfirst.frc.team135.robot.Robot;
import org.usfirst.frc.team135.robot.RobotMap.INTAKE;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class GrabMandibles extends Command
{
    public GrabMandibles() 
    {
    	requires(Robot.intake);
    	setTimeout(INTAKE.TIME_OUT_SECONDS);
    }
    protected void execute() 
    {
    	Robot.intake.ActivateClaw(DoubleSolenoid.Value.kForward);
    }
    protected boolean isFinished() 
    {
        return isTimedOut();
    }
    protected void interrupted() 
    {
    }
}