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
	public static DifferentialDrive chassis;
		
	private DriveTrain()
	{		
		backLeftMotor = new WPI_TalonSRX(DRIVETRAIN.BACK_LEFT_ID);
		frontRightMotor = new WPI_TalonSRX(DRIVETRAIN.FRONT_RIGHT_ID);
		
		backRightMotor = new WPI_VictorSPX(DRIVETRAIN.BACK_RIGHT_ID);
		frontLeftMotor = new WPI_VictorSPX(DRIVETRAIN.FRONT_LEFT_ID);
		
		//MotorControllerInitialize.configureMotorPIDTalon(backLeftMotor, 0, DRIVETRAIN.IS_DRIVETRAIN_TALON);
		//MotorControllerInitialize.configureMotorPIDTalon(frontRightMotor, 0, true);
		
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
	
	public double getEncoderSpeed()
	{
		return 0.0;
		//return frontRightTalon.getSelectedSensorPosition(0) * ( (motor == DRIVETRAIN.BACK_LEFT_MOTOR) ? 1 : -1);
	}

	public double returnVelocity()
	{
		return 0.0;
		//return frontRightTalon.getSelectedSensorVelocity(0);
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
		chassis.tankDrive(0.0,0.0);
	}
	
	public void TankDrive(double leftMotorPower, double rightMotorPower) 
	{
		chassis.tankDrive(leftMotorPower * -1, rightMotorPower * -1);
	}	
	public void CurvatureDrive(double motorPower, double turnSpeed)
	{
		chassis.curvatureDrive(motorPower, turnSpeed, false);
	}

	public void periodic()
	{
		SmartDashboard.putNumber("Front Right Displacement", (getEncoderSpeed()));
		SmartDashboard.putNumber("Back Left Displacement", (getEncoderSpeed()));
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}
}
