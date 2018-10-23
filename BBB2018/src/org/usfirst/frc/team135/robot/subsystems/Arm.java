package org.usfirst.frc.team135.robot.subsystems;

import org.usfirst.frc.team135.robot.RobotMap.ARM;
import org.usfirst.frc.team135.robot.commands.tele.RunArm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {
	
	private static Arm instance; 
		
	public static WPI_TalonSRX 
	armTalon; 
	public static WPI_VictorSPX 
	armVictor1, 
	armVictor2;

	private boolean isPositionInitialized = false;
	
	public boolean isMantaining;
	
	private Arm()
	{
		armTalon = new WPI_TalonSRX(ARM.TALON_ID);
		armVictor1 = new WPI_VictorSPX(ARM.ARM_VICTOR_ID_1);
		armVictor2 = new WPI_VictorSPX(ARM.ARM_VICTOR_ID_2);
		//MotorControllerInitialize.configureMotorPIDTalon(armTalon, 0, false);
		armTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
		armVictor1.setNeutralMode(NeutralMode.Brake);
		armVictor2.setNeutralMode(NeutralMode.Brake);
		armVictor1.follow(armTalon);
		armVictor2.follow(armTalon);
	}
	
	public static Arm getInstance()
	{
		if (instance == null)
		{
			instance = new Arm();
		}
		return instance; 
	}
	
	public double getEncoderAcceleration()
	{
		double intial_velocity = 0.0, final_velocity = 0.0;
		Timer timer = new Timer();
		intial_velocity = getEncoderVelocity();
		timer.start();
		while (timer.get() < .2){}
		final_velocity = getEncoderVelocity();
		timer.stop();
		
		return ((intial_velocity - final_velocity) / timer.get());
	}
	
	public double getEncoderVelocity()
	{
		return (double)armTalon.getSelectedSensorVelocity(0);
	}
	
	public double getEncoderPosition()
	{
		return (double)armTalon.getSelectedSensorPosition(0);
	}
	
	public void RunArmMotors(double power) 
	{
		armTalon.set(power);
	}
	
	public void initPositon()
	{
		if (!isPositionInitialized)
		{
			Timer timer = new Timer();
			timer.start();
			do 
			{
				set(1.0);
			}
			while (timer.get() < 0);
			timer.stop();
			timer.reset();
			isPositionInitialized = true; 
		}
	}
	
	public void set(double speed)
	{
		armTalon.set(ControlMode.PercentOutput, speed);
	}
	
	public void autonArm()
	{
		while (getEncoderPosition() < 4000)
		{
			armTalon.set(ControlMode.Velocity, -.3);
		}
		while (getEncoderPosition() > 3000)
		{
			armTalon.set(ControlMode.Velocity, .3);
		}
	}
	
	public void setToPosition(double position)
	{
		Timer timer = new Timer();
		timer.start();
		if (position == getEncoderPosition())
		{
			return;
		}
		double direction = (position < getEncoderPosition()) ? -1 : 1;
		
		if (direction == 1)
		{
			while(getEncoderPosition() < position && timer.get() < 3)
			{
				set(1 * direction);
			}
		}
		else
		{
			while(getEncoderPosition() > position && timer.get() < 3)
			{
				set(1 * direction);
			}
		}
		timer.stop();
		timer.reset();
		}
	
	public void maintainPosition()
	{
		double position = getEncoderPosition();
		Timer timer = new Timer();
		timer.start();
		while (timer.get() < 2)
		{
			armTalon.set(ControlMode.Velocity, getEncoderPosition() < position ? .3 : -.1);
		}
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RunArm());
	}
	public void periodic()
	{
		//System.out.println("Encoder Velocty : " + getEncoderVelocity());
		//System.out.println("Encoder Position : " + getEncoderPosition());
	}
}
