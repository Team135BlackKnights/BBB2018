package org.usfirst.frc.team135.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import org.usfirst.frc.team135.robot.RobotMap.DRIVETRAIN;
import org.usfirst.frc.team135.robot.RobotMap.K_OI;
import org.usfirst.frc.team135.robot.commands.tele.DriveWithJoystick;
import org.usfirst.frc.team135.robot.utilities.PIDin;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {

	private static DriveTrain instance;

	public static WPI_TalonSRX
	backLeftMotor, 
	frontRightMotor;

	public static WPI_VictorSPX
	frontLeftMotor,
	backRightMotor;

	public static WPI_TalonSRX[] talons;
	public static WPI_VictorSPX[] victors; 
	public static DifferentialDrive chassis;
		
	private DriveTrain()
	{
		talons = new WPI_TalonSRX[DRIVETRAIN.NUMBER_OF_TALONS];
		victors = new WPI_VictorSPX[DRIVETRAIN.NUMBER_OF_VICTORS];
		for (int i = 0; i < DRIVETRAIN.NUMBER_OF_TALONS; i++)
		{
			talons[i] = new WPI_TalonSRX(DRIVETRAIN.MOTOR_ID_ARRAY[i]);
			//MotorControllerInitialize.configureMotorPIDTalon(talons[i], i, DRIVETRAIN.IS_DRIVETRAIN_TALON);
			victors[i] = new WPI_VictorSPX(DRIVETRAIN.MOTOR_ID_ARRAY[i] + DRIVETRAIN.NUMBER_OF_TALONS);
			//victors[i].setNeutralMode(NeutralMode.Brake);
		}
		
		frontLeftMotor = victors[DRIVETRAIN.FRONT_LEFT_MOTOR];
		backLeftMotor = talons[DRIVETRAIN.BACK_LEFT_MOTOR];
		frontRightMotor = talons[DRIVETRAIN.FRONT_RIGHT_MOTOR];
		backRightMotor = victors[DRIVETRAIN.BACK_RIGHT_MOTOR];
		
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
	
	public double getEncoderSpeed(int motor)
	{
		return talons[motor].getSelectedSensorPosition(0) * ( (motor == DRIVETRAIN.BACK_LEFT_MOTOR) ? 1 : -1);
	}

	public double returnVelocity()
	{
		return talons[DRIVETRAIN.FRONT_LEFT_MOTOR].getSelectedSensorVelocity(0);
	}
	
	public void ResetEncoders()
	{
		/*
		for (int i = 0; i < DRIVETRAIN.NUMBER_OF_TALONS; i++)
		{
			talons[i].setSelectedSensorPosition(0, 0, 10);
			victors[i].setSelectedSensorPosition(0, 0, 10);
		}
		*/
	}
	
	public void stopMotors()
	{
		Timer timer = new Timer();
		timer.start();
		while((Math.abs(getEncoderSpeed(DRIVETRAIN.FRONT_LEFT_MOTOR)) > 0) && timer.get() < 1)
		{
			for (int i = 0; i < DRIVETRAIN.NUMBER_OF_TALONS; i++)
			{
				talons[i].set(ControlMode.Velocity, 0);
			}
		}
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}
	
	public void TankDrive(double leftMotorPower, double rightMotorPower) 
	{
		chassis.tankDrive(leftMotorPower * -1, rightMotorPower * -1);
	}	
	public void TankDrive(double leftMotorPower, double rightMotorPower, double rotationRate) 
	{
		chassis.tankDrive(-leftMotorPower * rotationRate * .1, -rightMotorPower * rotationRate * .1);
	}
	public void periodic()
	{
		SmartDashboard.putNumber("Front Left Displacement", (getEncoderSpeed(DRIVETRAIN.FRONT_LEFT_MOTOR)));
		SmartDashboard.putNumber("Front Right Displacement", (getEncoderSpeed(DRIVETRAIN.FRONT_RIGHT_MOTOR)));
		SmartDashboard.putNumber("Back Left Talon Displacement", (getEncoderSpeed(DRIVETRAIN.BACK_LEFT_MOTOR)));
		SmartDashboard.putNumber("Back Right Displacement", (getEncoderSpeed(DRIVETRAIN.BACK_RIGHT_MOTOR)));
	}
}
