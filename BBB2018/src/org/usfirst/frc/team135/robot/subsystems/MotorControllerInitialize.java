package org.usfirst.frc.team135.robot.subsystems;

import org.usfirst.frc.team135.robot.RobotMap.ARM;
import org.usfirst.frc.team135.robot.RobotMap.DRIVETRAIN;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class MotorControllerInitialize
{
	public static void configureMotorPIDTalon(WPI_TalonSRX talon, int arrayID, boolean isDrivetrain)
	{
		talon.setNeutralMode(NeutralMode.Brake);
		talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, DRIVETRAIN.PID.TIMEOUT_MS);
		talon.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 10, DRIVETRAIN.PID.TIMEOUT_MS);
		talon.setSelectedSensorPosition(0, 0, DRIVETRAIN.PID.TIMEOUT_MS);
		talon.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_100Ms, DRIVETRAIN.PID.TIMEOUT_MS);
		talon.configVelocityMeasurementWindow(64, DRIVETRAIN.PID.TIMEOUT_MS);
		talon.setSensorPhase(false);
		if (isDrivetrain)
		{
			talon.config_kP(0, DRIVETRAIN.PID.kP[arrayID], DRIVETRAIN.PID.TIMEOUT_MS); 
			talon.config_kI(0, DRIVETRAIN.PID.kI[arrayID], DRIVETRAIN.PID.TIMEOUT_MS);
			talon.config_kD(0, DRIVETRAIN.PID.kD[arrayID], DRIVETRAIN.PID.TIMEOUT_MS);
			talon.config_kF(0, DRIVETRAIN.PID.kF[arrayID], DRIVETRAIN.PID.TIMEOUT_MS);
		}
		else
		{
			talon.config_kP(0, ARM.kP, ARM.TIMEOUT_MS);
			talon.config_kI(0, ARM.kI, ARM.TIMEOUT_MS);
			talon.config_kD(0, ARM.kD, ARM.TIMEOUT_MS);
			talon.config_kF(0, ARM.kF, ARM.TIMEOUT_MS);
		}
	}
}
