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
	
	WPI_TalonSRX 
	armMotor1,
	armMotor2,
	armMotor3;
	
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
		armMotor1 = new WPI_TalonSRX(ARM.ARM_MOTOR_ID_1);
		armMotor2 = new WPI_TalonSRX(ARM.ARM_MOTOR_ID_2);
		armMotor3 = new WPI_TalonSRX(ARM.ARM_MOTOR_ID_3);
		
		double
		kP = 4,
		kI = 0,
		kD = 3 * Math.sqrt(kP),
		kF = 12;
		
		armMotor1.setInverted(false);
		armMotor2.setInverted(false);
		armMotor3.setInverted(false);
		
		armMotor1.setSensorPhase(true);
		armMotor2.setSensorPhase(true);
		armMotor3.setSensorPhase(true);
		
		armMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		armMotor2.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		armMotor3.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);

		armMotor1.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 10, 10);
		armMotor2.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 10, 10);
		armMotor3.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 10, 10);

		armMotor1.setSelectedSensorPosition(0, 0, 10);
		armMotor2.setSelectedSensorPosition(0, 0, 10);
		armMotor3.setSelectedSensorPosition(0, 0, 10);

		armMotor1.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_100Ms, 10);
		armMotor2.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_100Ms, 10);
		armMotor3.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_100Ms, 10);

		armMotor1.configVelocityMeasurementWindow(64, 10);		
		armMotor2.configVelocityMeasurementWindow(64, 10);
		armMotor3.configVelocityMeasurementWindow(64, 10); 

		armMotor1.configForwardSoftLimitThreshold(1450, 10);
		armMotor2.configForwardSoftLimitThreshold(1450, 10);
		armMotor3.configForwardSoftLimitThreshold(1450, 10);

		armMotor1.configReverseSoftLimitThreshold(0, 10);
		armMotor2.configReverseSoftLimitThreshold(0, 10);
		armMotor3.configReverseSoftLimitThreshold(0, 10);

		
		armMotor1.configForwardSoftLimitEnable(true, 10);
		armMotor2.configForwardSoftLimitEnable(true, 10);
		armMotor3.configForwardSoftLimitEnable(true, 10);

		armMotor1.configReverseSoftLimitEnable(true, 10);
		armMotor2.configReverseSoftLimitEnable(true, 10);
		armMotor3.configReverseSoftLimitEnable(true, 10);

		
		armMotor1.config_kP(0, kP, 10);
		armMotor1.config_kI(0, kI, 10);
		armMotor1.config_kD(0, kD, 10);
		armMotor1.config_kF(0, kF, 10);
		
		armMotor2.config_kP(0, kP, 10);
		armMotor2.config_kI(0, kI, 10);
		armMotor2.config_kD(0, kD, 10);
		armMotor2.config_kF(0, kF, 10);
		
		armMotor3.config_kP(0, kP, 10);
		armMotor3.config_kI(0, kI, 10);
		armMotor3.config_kD(0, kD, 10);
		armMotor3.config_kF(0, kF, 10);
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
		return (double)armMotor1.getSelectedSensorVelocity(0);
	}
	
	public double getEncoderPosition()
	{
		return (double)armMotor1.getSelectedSensorPosition(0);
	}
	
	public void RunArmMotors(double power) 
	{
		armMotor1.set(power);
		armMotor2.set(power);
		armMotor3.set(power);
	}
	
	
	public void initPosition()
	{
		if (!isPositionInitialized)
		{
			Timer timer = new Timer();
			timer.start();
			do
			{
				//System.out.println(getEncoderVelocity());
				armMotor1.set(1.0);
				armMotor2.set(1.0);
				armMotor3.set(1.0);
				
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
