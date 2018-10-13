package org.usfirst.frc.team135.robot.commands.tele;

import org.usfirst.frc.team135.robot.Robot;
import org.usfirst.frc.team135.robot.RobotMap.INTAKE;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class ReleaseMandibles extends Command {

    public ReleaseMandibles() {
      
    	requires(Robot.intake);
    	setTimeout(INTAKE.TIME_OUT_SECONDS);
    }
    protected void execute() 
    {
    	Robot.intake.ActivateClaw(DoubleSolenoid.Value.kReverse);
    }
    protected boolean isFinished() 
    {
        return isTimedOut();
    }
    protected void interrupted() 
    {
    	end();
    }
}