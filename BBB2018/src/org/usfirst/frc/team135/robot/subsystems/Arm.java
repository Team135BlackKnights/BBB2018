package org.usfirst.frc.team135.robot.subsystems;

import org.usfirst.frc.team135.robot.RobotMap.ARM;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {
	
	private static Arm instance; 
	
	WPI_TalonSRX armMotor1;
	WPI_TalonSRX armMotor2;
	
	public static Arm InitializeSubsystem()
	{
		if (instance == null)
		{
			instance = new Arm();
		}
		return instance; 
	}

	private Arm()
	{
		InitializeMotors();
	}
	
	public void InitializeMotors()
	{
		armMotor1 = new WPI_TalonSRX(ARM.ARM_MOTOR_ID_1);
		armMotor2 = new WPI_TalonSRX(ARM.ARM_MOTOR_ID_2);
	}
	
	public void RunArmMotors(double power) 
	{
		armMotor1.set(power);
		armMotor2.set(power);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
