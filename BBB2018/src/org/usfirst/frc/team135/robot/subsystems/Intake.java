
package org.usfirst.frc.team135.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;


import org.usfirst.frc.team135.robot.RobotMap;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Preferences;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Intake extends Subsystem implements RobotMap {

	private static WPI_VictorSPX rightWheel, leftWheel;
	private static DoubleSolenoid claw; 
	private static DoubleSolenoid retraction;
	private static Compressor compressor;
	
	
	boolean rightWheelInverted = false;
	boolean leftWheelInverted = true;

	private static Intake instance;
	
	private static Intake GetInstance() 
	{
		if (instance == null)
		{
			instance = new Intake();
		}
		return instance;
	}
	private Intake()
	{
		
	}
	
	public void InitializeWheelMotors() 
	{
		leftWheel = new WPI_VictorSPX(INTAKE.LEFT_WHEEL_ID);
		rightWheel = new WPI_VictorSPX(INTAKE.RIGHT_WHEEL_ID);
		
		leftWheel.setInverted(leftWheelInverted);
		rightWheel.setInverted(rightWheelInverted);
		
	}
	public void InitializePneumatics()
	{
		int intake_open = PNEUMATICS.MANDIBLE_OPEN_CHANNEL;
		int intake_close = PNEUMATICS.MANDIBLE_CLOSE_CHANNEL;
		
		int retract_open = PNEUMATICS.RETRACT_IN_CHANNEL;
		int retract_close = PNEUMATICS.RETRACT_OUT_CHANNEL;
		
		claw = new DoubleSolenoid(intake_open, intake_close);
		retraction = new DoubleSolenoid(retract_open, retract_close);
		
		compressor = new Compressor(0);
		compressor.setClosedLoopControl(true);
		
		claw.set(DoubleSolenoid.Value.kOff);
		retraction.set(DoubleSolenoid.Value.kOff);
		
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
	public void FlyWheels(double power)
	{
		leftWheel.set(power);
		rightWheel.set(-power);
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
		// TODO Auto-generated method stub
		
	}
	
}
