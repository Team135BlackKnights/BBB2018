
package org.usfirst.frc.team135.robot.subsystems;

import org.usfirst.frc.team135.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem implements RobotMap {

	private static Intake instance;

	private static WPI_VictorSPX rightWheel;
	private static WPI_TalonSRX leftWheel;
	private static DoubleSolenoid claw; 
	private static DoubleSolenoid retraction;
	private static Compressor compressor;

	public Intake()
	{
		leftWheel = new WPI_TalonSRX(INTAKE.LEFT_WHEEL_ID);
		rightWheel = new WPI_VictorSPX(INTAKE.RIGHT_WHEEL_ID);
		
		leftWheel.setInverted(RobotMap.INTAKE.leftWheelInverted);
		rightWheel.setInverted(RobotMap.INTAKE.rightWheelInverted);
		
		claw = new DoubleSolenoid(0, 1);
		retraction = new DoubleSolenoid(2, 3);
		
		compressor = new Compressor(PNEUMATICS.COMPRESSOR_ID);
		compressor.setClosedLoopControl(true);
		
		claw.set(DoubleSolenoid.Value.kOff);
		retraction.set(DoubleSolenoid.Value.kOff);
	}
	
	public static Intake getInstance() 
	{
		if (instance == null)
		{
			instance = new Intake();
		}
		return instance;
	}
	
	public void setCompressorOff()
	{
		compressor.setClosedLoopControl(false);
		compressor.stop();
	}
	
	public void setCompressorOn()
	{
		compressor.setClosedLoopControl(true);
	}
	
	public void ActivateClaw(DoubleSolenoid.Value value)
	{
		claw.set(value);
	}
	public void DriveWheels(double power)
	{
		leftWheel.set(power);
		rightWheel.set(.95*power);
	}
	public void MoveMandibles(DoubleSolenoid.Value value)
	{
		retraction.set(value);
	}
	public DoubleSolenoid.Value GetSolenoidPosition(DoubleSolenoid solenoid)
	{
		return solenoid.get();
	}
	@Override
	protected void initDefaultCommand()
	{
		
	}
	@Override
	public void periodic()
	{
		//System.out.println(GetSolenoidPosition(claw));
		//claw.set(DoubleSolenoid.Value.kReverse);
	}
	
}
