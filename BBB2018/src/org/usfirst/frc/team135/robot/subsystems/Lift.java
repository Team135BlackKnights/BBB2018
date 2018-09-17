package org.usfirst.frc.team135.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team135.robot.RobotMap;
import org.usfirst.frc.team135.robot.RobotMap.*;

public class Lift extends Subsytem implements RobotMap
{

	private static Lift instance;
	
	private double setpoint = 0.0;
	
	private TalonSRX liftMotor;
	
	private boolean isPositionInitialized = false;
	
	public boolean isMaintaining;
	
	public boolean isDrawingTooMuchCurrent = false;
	
	public double tripPoint = 0.0;
	
	public boolean isInverted = false;
	
	private Lift() 
	{
		liftMotor = new TalonSRX(LIFT.LIFT_MOTOR_ID);
		liftMotor.setInverted(isInverted);
		liftMotor.setSensorPhase(true);
		
		liftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		liftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 10,10 );
		liftMotor.setSelectedSensorPosition(0,0,10);
		liftMotor.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_100Ms, 10);
		liftMotor.configVelocityMeasurementWindow(64,10);
		liftMotor.configForwardSoftLimitThreshold(1450,10);
		liftMotor.configReverseSoftLimitThreshold(0,10);
		liftMotor.configForwardSoftLimitEnable(true, 10);
		liftMotor.configReverseSoftLimitEnable(true,10);
		
	}
	public static Lift getInstance()
	{
		if (instance == null)
		{
			instance = new Lift();
		}
		return instance;
	}
	public void set(double speed)
	{
		liftMotor.set(ControlMode.PercentOutput, speed);
	}
	public void initPosition()
	{
		if (!isPositionInitialized)
		{
			Timer timer = new Timer();
			timer.start();
			do
			{
				set(1.0);
			} 
			while (timer.get() < 5);
			timer.stop();
			timer.reset();
			isPositionInitialized = true; 
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
		return (double)liftMotor.getSelectedSensorVelocity(0);
	}
	
	public double getEncoderPosition()
	{
		return (double)liftMotor.getSelectedSensorPosition(0);
	}
	
	public void stopMotor()
	{
		set(0);
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
		
		setpoint = position;
	}
	
	public void mantainPosition()
	{
		liftMotor.set(ControlMode.Velocity, .75);
	}
	
	public void initDefaultCommand()
	{
		//setDefaultCommand(new RunLift());
	}
	public void periodic()
	{
		System.out.println(getEncoderPosition());
		SmartDashboard.putNumber("Lift Setpoint", setpoint);
		SmartDashboard.putNumber("Lift Velocity", getEncoderVelocity());
	}
	
}