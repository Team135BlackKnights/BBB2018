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
	
	public WPI_TalonSRX frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor;
	WPI_TalonSRX[] driveTrainMotors;
	DifferentialDrive chassis;
	
	private PIDin navx;
	
	private static final int angleSetPoint = 0; //Was thinking about using this but didn't.
	
	private static final double MOTOR_SETPOINT_PER_100MS = DRIVETRAIN.ENCODERS.MAX_VELOCITY_TICKS_PER_100MS; //NU/100 ms MAX SPEED for slowest motor
	
	private MotorSafetyHelper m_safetyHelper = new MotorSafetyHelper(chassis); //watchdog
	
	private PIDController orientationHelper; //Orientation helper SHOULD helper you go straight
											//But it doesn't work right now
	private PIDout buffer; //Stores the orientation helper's motor bias

	public int 
	FL_ID = RobotMap.DRIVETRAIN.FRONT_LEFT_ID,
	BL_ID =	RobotMap.DRIVETRAIN.BACK_LEFT_ID,
	FR_ID = RobotMap.DRIVETRAIN.FRONT_RIGHT_ID,
	BR_ID = RobotMap.DRIVETRAIN.BACK_RIGHT_ID;

	private DriveTrain()
	{
		driveTrainMotors = new WPI_TalonSRX[4];
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
		}
		
		//Configure the orientation helper and it's output storage helper.
		buffer = new PIDout();
		navx = new PIDin(() -> Robot.navx.getFusedAngle(), PIDSourceType.kDisplacement);
		
		orientationHelper = new PIDController(.01, 0, .1, navx, buffer);
	
		orientationHelper.setInputRange(0, 360);
		orientationHelper.setOutputRange(-.15, .15);
		orientationHelper.setAbsoluteTolerance(1);
		orientationHelper.setContinuous();
		
		//Get PIDF constants.
		InitializeDrivetrain();
	}
	
	public void InitializeDrivetrain()
	{
		for (int i = 0; i < DRIVETRAIN.NUMBER_OF_MOTORS; i++)
		{
			driveTrainMotors[i].config_kP(0, DRIVETRAIN.PID.kP[i], DRIVETRAIN.PID.TIMEOUT_MS); //configure talons with PID constants
			driveTrainMotors[i].config_kI(0, DRIVETRAIN.PID.kI[i], DRIVETRAIN.PID.TIMEOUT_MS);
			driveTrainMotors[i].config_kD(0, DRIVETRAIN.PID.kD[i], DRIVETRAIN.PID.TIMEOUT_MS);
			driveTrainMotors[i].config_kF(0, DRIVETRAIN.PID.kF[i], DRIVETRAIN.PID.TIMEOUT_MS);
		}
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
		orientationHelper.setSetpoint(Robot.navx.getFusedAngle());
		orientationHelper.enable();
		Timer timer = new Timer();
		timer.start();
		while((Math.abs(getEncoderSpeed(DRIVETRAIN.FRONT_LEFT_MOTOR)) > 0 || orientationHelper.getError() > .2) && timer.get() < 1)
		{
			for (int i = 0; i < DRIVETRAIN.NUMBER_OF_MOTORS; i++)
			{
				driveTrainMotors[i].set(ControlMode.Velocity, 0 + buffer.output);
			}
		}
	
		orientationHelper.disable();
	}
	
	public void initDefaultComand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	public static DriveTrain getInstance() 
	{
		if (instance == null)
		{
			instance = new DriveTrain();
		}
		return instance;
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
