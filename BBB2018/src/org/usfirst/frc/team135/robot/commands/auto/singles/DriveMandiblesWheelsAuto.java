package org.usfirst.frc.team135.robot.commands.auto.singles;

import org.usfirst.frc.team135.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class DriveMandiblesWheelsAuto extends InstantCommand {
public DriveMandiblesWheelsAuto(double timeout, double power) {
    	requires(Robot.drivetrain);
        requires(Robot.intake);
    	Timer timer = new Timer();
		timer.start();
                
        while (timer.get() < timeout)
        {
        	Robot.intake.DriveWheels(power);
        }
        Robot.intake.DriveWheels(0);
    }


	@Override
	public void end()
	{
		Robot.intake.DriveWheels(0);
	}
	@Override
	public void interrupted()
	{
		end();
	
	}
}
