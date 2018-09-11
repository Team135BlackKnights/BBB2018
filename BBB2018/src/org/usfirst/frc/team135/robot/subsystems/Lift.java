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
import org.usfirst.frc.team135.robot.commands.teleop.RunLift;

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
		liftMotor = new TalonSRX(id);
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
}
