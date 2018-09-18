package org.usfirst.frc.team135.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.usfirst.frc.team135.robot.RobotMap;
import org.usfirst.frc.team135.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.PIDOutput;

public class DriveTrain extends Subsystem {

	private static DriveTrain instance;
	
	WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(RobotMap.DRIVETRAIN.FRONT_LEFT_ID);
	WPI_TalonSRX backLeftMotor = new WPI_TalonSRX(RobotMap.DRIVETRAIN.BACK_LEFT_ID);
	WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(RobotMap.DRIVETRAIN.FRONT_RIGHT_ID);
	WPI_TalonSRX backRightMotor = new WPI_TalonSRX(RobotMap.DRIVETRAIN.BACK_RIGHT_ID);

	SpeedControllerGroup left = new SpeedControllerGroup(frontLeftMotor, backLeftMotor);
	SpeedControllerGroup right = new SpeedControllerGroup(frontRightMotor, backRightMotor);
	
	DifferentialDrive chassis = new DifferentialDrive(left, right);
	
	public void initDefaultComand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	public static DriveTrain getInstance() 
	{
		if (instance == null)
		{
			instance = new DriveTrain();
		}
		return instance;
	}
	
	public void TankDrive(double leftMotorPower, double rightMotorPower) 
	{
		chassis.tankDrive(leftMotorPower, rightMotorPower);
		return;
	}

	@Override
	protected void initDefaultCommand() 
	{	
	}
}
