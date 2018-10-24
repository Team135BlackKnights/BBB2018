package org.usfirst.frc.team135.robot.commands.tele;

import org.usfirst.frc.team135.robot.Robot;
import org.usfirst.frc.team135.robot.RobotMap.INTAKE;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class ThrowCubeForward extends InstantCommand {

    public ThrowCubeForward() {
        requires(Robot.intake);
        setTimeout(INTAKE.TIME_OUT_SECONDS);
    }
    @Override
	protected void execute() {
    	Robot.intake.MoveMandibles(DoubleSolenoid.Value.kReverse);
    	Timer.delay(.5);
    	Robot.intake.MoveMandibles(DoubleSolenoid.Value.kForward);
    	Timer.delay(.5 / 4);
    	Robot.intake.ActivateClaw(DoubleSolenoid.Value.kForward);
    }
    @Override
	protected boolean isFinished()
    {
    	return isTimedOut();
    }
    @Override
	protected void end()
    {
    	Robot.intake.MoveMandibles(DoubleSolenoid.Value.kOff);
    }
    @Override
	protected void interrupted()
    {
    	end();
    }
}
