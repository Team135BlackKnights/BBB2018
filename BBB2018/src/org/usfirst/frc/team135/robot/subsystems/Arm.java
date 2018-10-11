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
	
	public static WPI_TalonSRX 
	armMotor1; 
	public static WPI_VictorSPX 
	armMotor2, 
	armMotor3;
	
	
		
	private boolean isPositionInitialized = false;
	
	public boolean isMantaining;
	public double tripPoint = 0.0;

	private Arm()
	{
		armMotor1 = new WPI_TalonSRX(ARM.ARM_MOTOR_ID_1);
		armMotor2 = new WPI_VictorSRX(ARM.ARM_MOTOR_ID_2);
		armMotor3 = new WPI_VictorSRX(ARM.ARM_MOTOR_ID_3);
	
		
			armMotor1.setInverted(false);	
			armMotor1.setSensorPhase(true);
			armMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, ARM.TIMEOUT_MS);
			armMotor1.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 10, ARM.TIMEOUT_MS);
			armMotor1.setSelectedSensorPosition(0, 0, ARM.TIMEOUT_MS);
			armMotor1.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_100Ms, ARM.TIMEOUT_MS);
			armMotor1.configVelocityMeasurementWindow(64, ARM.TIMEOUT_MS);		
			armMotor1.configForwardSoftLimitThreshold(1450, ARM.TIMEOUT_MS);
			armMotor1.configReverseSoftLimitThreshold(0, ARM.TIMEOUT_MS);		
			armMotor1.configForwardSoftLimitEnable(true, ARM.TIMEOUT_MS);
			armMotor1.configReverseSoftLimitEnable(true, ARM.TIMEOUT_MS);		
			armMotor1.config_kP(0, ARM.kP, ARM.TIMEOUT_MS);
			armMotor1.config_kI(0, ARM.kI, ARM.TIMEOUT_MS);
			armMotor1.config_kD(0, ARM.kD, ARM.TIMEOUT_MS);
			armMotor1.config_kF(0, ARM.kF, ARM.TIMEOUT_MS);
		
		armMotor2.changeControlMode(CANTalon.ControlMode.Follower);
		armMotor2.set(armMotor1.getDeviceID());
		armMotor3.changeControlMode(CANTalon.ControlMode.Follower);
		armMotor3.set(armMotor1.getDeviceID());
		
		
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
		return (double)armMotor1.getSelectedSensorVelocity(0);
	}
	
	public double getEncoderPosition()
	{
		return (double)armMotor1.getSelectedSensorPosition(0);
	}
	
	public void RunArmMotors(double power) 
	{
		armMotor1.set(power);
	}
	
	
	public void initPosition()
	{
		if (!isPositionInitialized)
		{
			Timer timer = new Timer();
			timer.start();
			do
			{
					armMotor1.set(1.0f);
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
