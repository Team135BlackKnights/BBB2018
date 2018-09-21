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
	
	DifferentialDrive chassis;
	
	private PIDin navx;
	
	private static final int angleSetPoint = 0; //Was thinking about using this but didn't.
	
	//Leftover stuff that we don't use.
	private static final int ENCODER_TICK_COUNT = 256;
	private static final int ENCODER_QUAD_COUNT = (ENCODER_TICK_COUNT * 4);
	
	private static final double MOTOR_SETPOINT_PER_100MS = DRIVETRAIN.MAX_VELOCITY_TICKS_PER_100MS; //NU/100 ms MAX SPEED for slowest motor
	
	private MotorSafetyHelper m_safetyHelper = new MotorSafetyHelper(chassis); //watchdog
	
	private PIDController orientationHelper; //Orientation helper SHOULD helper you go straight
											//But it doesn't work right now
	
	private PIDout buffer; //Stores the orientation helper's motor bias
	
	private double BackRightkP;  //PID constants for each of the drive talons
	private double BackRightkI;
	private double BackRightkD;
	private double BackRightkF;

	private double BackLeftkP; 
	private double BackLeftkI;
	private double BackLeftkD;
	private double BackLeftkF;
	
	private double FrontRightkP; 
	private double FrontRightkI;
	private double FrontRightkD;
	private double FrontRightkF;
	
	private double FrontLeftkP; 
	private double FrontLeftkI;
	private double FrontLeftkD;
	private double FrontLeftkF;
	
	private double
	OrientationHelper_kP,
	OrientationHelper_kI,
	OrientationHelper_kD;

	private double
	FrontLeftSetpoint = 0.0,
	BackLeftSetpoint = 0.0,
	FrontRightSetpoint = 0.0,
	BackRightSetpoint = 0.0;

	public int 
	FL_ID = RobotMap.DRIVETRAIN.FRONT_LEFT_ID,
	BL_ID =	RobotMap.DRIVETRAIN.BACK_LEFT_ID,
	FR_ID = RobotMap.DRIVETRAIN.FRONT_RIGHT_ID,
	BR_ID = RobotMap.DRIVETRAIN.BACK_RIGHT_ID;

	private DriveTrain()
	{
		frontLeftMotor = new WPI_TalonSRX(FL_ID);
		backLeftMotor = new WPI_TalonSRX(BL_ID);
		frontRightMotor = new WPI_TalonSRX(FR_ID);
		backRightMotor = new WPI_TalonSRX(BR_ID);
		
		ConfigureTalons(frontRightMotor, FR_ID);
		ConfigureTalons(frontLeftMotor, FL_ID);
		ConfigureTalons(backRightMotor, BR_ID);
		ConfigureTalons(backLeftMotor, BL_ID);
		
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
	
	public void ConfigureTalons(WPI_TalonSRX talon, int talon_id)
	{
		ConfigureEncoderDirection();
		
		talon.setNeutralMode(NeutralMode.Brake);
		talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		talon.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 10, 10);
		talon.setSelectedSensorPosition(0, 0, 10);
		
		talon.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_100Ms, 10);
		talon.configVelocityMeasurementWindow(64, 10);
	}
	
	public void InitializeDrivetrain()
	{
		BackRightkP = 0;
		BackRightkI = 0;
		BackRightkD = 0;
		BackRightkF = 0;
		
		FrontRightkP = 0;
		FrontRightkI = 0;
		FrontRightkD = 0;
		FrontRightkF = 0;
		
		FrontLeftkP = 0;
		FrontLeftkI = 0;
		FrontLeftkD = 0;
		FrontLeftkF = 0;
		
		BackLeftkP = 0;
		BackLeftkI = 0;
		BackLeftkD = 0;
		BackLeftkF = 0;
	
		OrientationHelper_kP = 0;
		OrientationHelper_kI = 0;
		OrientationHelper_kD = 0;
		
		backRightMotor.config_kP(0, BackRightkP, 10); //configure talons with PID constants
		backRightMotor.config_kI(0, BackRightkI, 10);
		backRightMotor.config_kD(0, BackRightkD, 10);
		backRightMotor.config_kF(0, BackRightkF, 10);
		
		frontRightMotor.config_kP(0, FrontRightkP, 10);
		frontRightMotor.config_kI(0, FrontRightkI, 10);
		frontRightMotor.config_kD(0, FrontRightkD, 10);
		frontRightMotor.config_kF(0, FrontRightkF, 10);
		
		backLeftMotor.config_kP(0, BackLeftkP, 10);
		backLeftMotor.config_kI(0, BackLeftkI, 10);
		backLeftMotor.config_kD(0, BackLeftkD, 10);
		backLeftMotor.config_kF(0, BackLeftkF, 10);
		
		frontLeftMotor.config_kP(0, FrontLeftkP, 10);
		frontLeftMotor.config_kI(0, FrontLeftkI, 10);
		frontLeftMotor.config_kD(0, FrontLeftkD, 10);
		frontLeftMotor.config_kF(0, FrontLeftkF, 10);
		
		SpeedControllerGroup left = new SpeedControllerGroup(frontLeftMotor, backLeftMotor);
		SpeedControllerGroup right = new SpeedControllerGroup(frontRightMotor, backRightMotor);
		
		chassis = new DifferentialDrive(left, right);
		
		chassis.setDeadband(.1);
		chassis.setSafetyEnabled(false);
	}
	
	public void ConfigureEncoderDirection()
	{
		frontLeftMotor.setSensorPhase(false);
		backLeftMotor.setSensorPhase(false);
		frontRightMotor.setSensorPhase(false);
		backRightMotor.setSensorPhase(false);
	}
	
	public double getEncoderCounts(WPI_TalonSRX talon)
	{
		double position = talon.getSelectedSensorPosition(0);
		if (talon.getDeviceID() == FR_ID || talon.getDeviceID() == BR_ID)
		{
			position *= -1;
		}
		return (position);
	}
	
	public double getEncoderSpeed(WPI_TalonSRX talon)
	{
		double velocity = talon.getSelectedSensorVelocity(0);
		if (talon.getDeviceID() == FR_ID || talon.getDeviceID() == BR_ID)
		{
			velocity *= -1;
		}
		return (velocity);
	}
	
	public double getEncoderSetpoint(WPI_TalonSRX talon)
	{
		if (talon.getDeviceID() == FL_ID)
		{
			return FrontLeftSetpoint;
		}
		else if (talon.getDeviceID() == BL_ID)
		{
			return BackLeftSetpoint;
		}
		else if (talon.getDeviceID() == FR_ID)
		{
			return FrontRightSetpoint;
		}
		else if (talon.getDeviceID() == BR_ID)
		{
			return BackRightSetpoint;
		}
		else
		{
			return 0;
		}
		
	}
	
	public double returnVelocity()
	{
		double value = frontLeftMotor.getSelectedSensorVelocity(0);
		return value;
	}
	
	public void stopMotors()
	{
		orientationHelper.setSetpoint(Robot.navx.getFusedAngle());
		orientationHelper.enable();
		Timer timer = new Timer();
		timer.start();
		while((Math.abs(getEncoderSpeed(frontLeftMotor)) > 0 || orientationHelper.getError() > .2) && timer.get() < 1)
		{
			backLeftMotor.set(ControlMode.Velocity, 0 + buffer.output);
			backRightMotor.set(ControlMode.Velocity, 0 + buffer.output);
			frontLeftMotor.set(ControlMode.Velocity, 0 + buffer.output);
			frontRightMotor.set(ControlMode.Velocity, 0 + buffer.output);
		}
	
		orientationHelper.disable();
	}
	
	public void initDefaultComand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	public static DriveTrain InitializeSubsystem() 
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
		return;
	}
	
	public void periodic()
	{
		SmartDashboard.putNumber("Front Left Displacement", (getEncoderCounts(frontLeftMotor)));
		SmartDashboard.putNumber("Front Right Displacement", (getEncoderCounts(frontRightMotor)));
		SmartDashboard.putNumber("Back Left Talon Displacement", (getEncoderCounts(backLeftMotor)));
		SmartDashboard.putNumber("Back Right Displacement", (getEncoderCounts(backRightMotor)));
	}

	@Override
	protected void initDefaultCommand() 
	{	
	}
}
