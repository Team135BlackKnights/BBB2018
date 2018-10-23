package org.usfirst.frc.team135.robot.commands.auto.singles;

import org.usfirst.frc.team135.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class DriveMandiblesWheelsAuto extends InstantCommand {

	private double _power;
	private double time;
		
    public DriveMandiblesWheelsAuto(double power) {
    	time = 0;
		Timer finaltimer = new Timer();
		finaltimer.start();
		Timer timer = new Timer();
		timer.start();
		time = timer.get();
		requires(Robot.drivetrain);
        requires(Robot.intake);
        
        this._power = power;
        
        while (timer.get() -time > 2)
        {
        	Robot.intake.DriveWheels(this._power);
        }
    }
}
