package org.usfirst.frc.team135.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team135.robot.Robot;
import org.usfirst.frc.team135.robot.RobotMap;
import org.usfirst.frc.team135.robot.RobotMap.DRIVETRAIN;
import org.usfirst.frc.team135.robot.RobotMap.K_OI;
import org.usfirst.frc.team135.robot.commands.TeleCommands.DriveWithJoystick;
import org.usfirst.frc.team135.robot.utilities.PIDout;
import org.usfirst.frc.team135.robot.utilities.PIDin;
import org.usfirst.frc.team135.robot.subsystems.*;

import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

@SuppressWarnings ("unused")
public class DriveTrain extends Subsystem {

	private static DriveTrain instance;
	
	public static WPI_TalonSRX frontLeftMotor, 
	backLeftMotor, 
	frontRightMotor, 
	backRightMotor;
	public static WPI_TalonSRX[] driveTrainMotors;
	public static DifferentialDrive chassis;
	
	private PIDin navx;
	
	private MotorSafetyHelper m_safetyHelper;

	private DriveTrain()
	{
		driveTrainMotors = new WPI_TalonSRX[DRIVETRAIN.NUMBER_OF_MOTORS];
		for (int i = 0; i < DRIVETRAIN.NUMBER_OF_MOTORS; i++)
		{
			driveTrainMotors[i] = new WPI_TalonSRX(DRIVETRAIN.MOTOR_ID_ARRAY[i]);
			
			driveTrainMotors[i].setNeutralMode(NeutralMode.Brake);
			driveTrainMotors[i].configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, DRIVETRAIN.PID.TIMEOUT_MS);
			driveTrainMotors[i].setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 10, DRIVETRAIN.PID.TIMEOUT_MS);
			driveTrainMotors[i].setSelectedSensorPosition(0, 0, DRIVETRAIN.PID.TIMEOUT_MS);
			
			driveTrainMotors[i].configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_100Ms, DRIVETRAIN.PID.TIMEOUT_MS);
			driveTrainMotors[i].configVelocityMeasurementWindow(64, DRIVETRAIN.PID.TIMEOUT_MS);
			
			driveTrainMotors[i].setSensorPhase(false);
			
			//configure talons with PID constants
			driveTrainMotors[i].config_kP(0, DRIVETRAIN.PID.kP[i], DRIVETRAIN.PID.TIMEOUT_MS); 
			driveTrainMotors[i].config_kI(0, DRIVETRAIN.PID.kI[i], DRIVETRAIN.PID.TIMEOUT_MS);
			driveTrainMotors[i].config_kD(0, DRIVETRAIN.PID.kD[i], DRIVETRAIN.PID.TIMEOUT_MS);
			driveTrainMotors[i].config_kF(0, DRIVETRAIN.PID.kF[i], DRIVETRAIN.PID.TIMEOUT_MS);
		}
		m_safetyHelper = new MotorSafetyHelper(chassis);
		
		frontLeftMotor = driveTrainMotors[DRIVETRAIN.FRONT_LEFT_MOTOR];
		backLeftMotor = driveTrainMotors[DRIVETRAIN.BACK_LEFT_MOTOR];
		frontRightMotor = driveTrainMotors[DRIVETRAIN.FRONT_RIGHT_MOTOR];
		backRightMotor = driveTrainMotors[DRIVETRAIN.BACK_RIGHT_MOTOR];
		
		SpeedControllerGroup left = new SpeedControllerGroup(frontLeftMotor, backLeftMotor);
		SpeedControllerGroup right = new SpeedControllerGroup(frontRightMotor, backRightMotor);
		
		chassis = new DifferentialDrive(left, right);
		
		chassis.setDeadband(K_OI.DEADBAND);
		chassis.setSafetyEnabled(false);
	}
	
	public static DriveTrain getInstance() 
	{
		if (instance == null)
		{
			instance = new DriveTrain();
		}
		return instance;
	}
	
	public double getEncoderCounts(int motorID)
	{
		return driveTrainMotors[motorID].getSelectedSensorPosition(0) * ( (motorID == 1 || motorID == 3) ? 1 : -1);
	}
	
	public double getEncoderSpeed(int motorID)
	{
		return driveTrainMotors[motorID].getSelectedSensorVelocity(0) * ( (motorID == 1 || motorID == 3) ? 1 : -1);
	}
	
	public double getEncoderSetpoint(int motorID)
	{
		return DRIVETRAIN.PID.setPoints[motorID];
	}
	
	public double returnVelocity()
	{
		return driveTrainMotors[DRIVETRAIN.FRONT_LEFT_MOTOR].getSelectedSensorVelocity(0);
	}
	
	public void stopMotors()
	{
		Timer timer = new Timer();
		timer.start();
		while((Math.abs(getEncoderSpeed(DRIVETRAIN.FRONT_LEFT_MOTOR)) > 0) && timer.get() < 1)
		{
			for (int i = 0; i < DRIVETRAIN.NUMBER_OF_MOTORS; i++)
			{
				driveTrainMotors[i].set(ControlMode.Velocity, 0);
			}
		}
	}
	
	public void initDefaultComand() {
		setDefaultCommand(new DriveWithJoystick());
	}
	
	public void TankDrive(double leftMotorPower, double rightMotorPower) 
	{
		chassis.tankDrive(leftMotorPower, rightMotorPower);
	}
	
	public void periodic()
	{
		SmartDashboard.putNumber("Front Left Displacement", (getEncoderCounts(DRIVETRAIN.FRONT_LEFT_MOTOR)));
		SmartDashboard.putNumber("Front Right Displacement", (getEncoderCounts(DRIVETRAIN.FRONT_RIGHT_MOTOR)));
		SmartDashboard.putNumber("Back Left Talon Displacement", (getEncoderCounts(DRIVETRAIN.BACK_LEFT_MOTOR)));
		SmartDashboard.putNumber("Back Right Displacement", (getEncoderCounts(DRIVETRAIN.BACK_RIGHT_MOTOR)));
	}

	@Override
	protected void initDefaultCommand() 
	{	
	}
}
