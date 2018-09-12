package org.usfirst.frc.team135.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;


import org.usfirst.frc.team135.robot.RobotMap;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Preferences;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Intake extends Subsystem implements RobotMap {

	private static WPI_VictorSPX rightWheel, leftWheel;
	private static DoubleSolenoid retraction;
	private static Compressor compressor;
	
	private boolean compressorState = true;
	
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
		//rightWheel = new WPI_VictorSPX();
	}
	@Override
	protected void initDefaultCommand()
	{
		// TODO Auto-generated method stub
		
	}
	
}