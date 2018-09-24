package org.usfirst.frc.team135.robot.subsystems;

import org.usfirst.frc.team135.robot.RobotMap.ARM;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {
	
	private static Arm instance; 
	
	WPI_TalonSRX[] armMotors = new WPI_TalonSRX[3];
	
	private double setpoint = 0.0;
	
	private boolean isPositionInitialized = false;
	
	public boolean isMantaining;
	public double tripPoint = 0.0;
	
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
		armMotors[ARM.ARM_MOTOR_1] = new WPI_TalonSRX(ARM.ARM_MOTOR_ID_1);
		armMotors[ARM.ARM_MOTOR_2] = new WPI_TalonSRX(ARM.ARM_MOTOR_ID_2);
		armMotors[ARM.ARM_MOTOR_3] = new WPI_TalonSRX(ARM.ARM_MOTOR_ID_3);
		
		
		for (int i = 0; i < ARM.NUMBER_OF_MOTORS; i++)
		{
			armMotors[i].setInverted(false);	
			armMotors[i].setSensorPhase(true);
			armMotors[i].configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
			armMotors[i].setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 10, 10);
			armMotors[i].setSelectedSensorPosition(0, 0, 10);
			armMotors[i].configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_100Ms, 10);
			armMotors[i].configVelocityMeasurementWindow(64, 10);		
			armMotors[i].configForwardSoftLimitThreshold(1450, 10);
			armMotors[i].configReverseSoftLimitThreshold(0, 10);		
			armMotors[i].configForwardSoftLimitEnable(true, 10);
			armMotors[i].configReverseSoftLimitEnable(true, 10);		
			armMotors[i].config_kP(0, ARM.kP, 10);
			armMotors[i].config_kI(0, ARM.kI, 10);
			armMotors[i].config_kD(0, ARM.kD, 10);
			armMotors[i].config_kF(0, ARM.kF, 10);
		}
	}
	
	public double getEncoderAcceleration()
	{
		double v1 = 0.0, v2 = 0.0;
		Timer timer = new Timer();
		v1 = getEncoderVelocity();
		timer.start();
		while (timer.get() < .2){}
		v2 = getEncoderVelocity();
		timer.stop();
		
		return ((v2 - v1) / timer.get());
	}
	
	public double getEncoderVelocity()
	{
		return (double)armMotors[ARM.ARM_MOTOR_1].getSelectedSensorVelocity(0);
	}
	
	public double getEncoderPosition()
	{
		return (double)armMotors[ARM.ARM_MOTOR_1].getSelectedSensorPosition(0);
	}
	
	public void RunArmMotors(double power) 
	{
		for (int i = 0; i < ARM.NUMBER_OF_MOTORS; i++)
		{
			armMotors[i].set(power);
		}
	}
	
	
	public void initPosition()
	{
		if (!isPositionInitialized)
		{
			Timer timer = new Timer();
			timer.start();
			do
			{
				for (int i = 0; i < ARM.NUMBER_OF_MOTORS; i++)
				{
					armMotors[i].set(1.0f);
				}
			}
			while (timer.get() < 5);
			timer.stop();
			timer.reset();
			isPositionInitialized = true;
		}
		
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
