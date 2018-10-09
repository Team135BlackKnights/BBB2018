package org.usfirst.frc.team135.robot.commands.TeleCommands;

import org.usfirst.frc.team135.robot.Robot;
import org.usfirst.frc.team135.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command {

	private double leftJoystickValue, rightJoystickValue;

	public DriveWithJoystick() {
		requires(Robot.drivetrain);
	}

	protected void initialize()
    {
    	
    }
	protected void execute() {
		leftJoystickValue = Robot.oi.GetLeftJoystickValues()[RobotMap.K_OI.GETY];
		rightJoystickValue = Robot.oi.GetRightJoystickValues()[RobotMap.K_OI.GETY];

		Robot.drivetrain.TankDrive(leftJoystickValue, rightJoystickValue);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void end() {
	//	Robot.drivetrain.TankDrive(0, 0);
	}
	protected void interrupted() 
    {
    	//this.end();
    }
}
