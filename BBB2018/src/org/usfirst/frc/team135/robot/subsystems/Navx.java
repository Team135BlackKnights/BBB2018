
package org.usfirst.frc.team135.robot.subsystems;

import org.usfirst.frc.team135.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Navx extends Subsystem
{
	private static Navx instance;
	public double initAngle;
	private AHRS ahrs;	
	
	private Navx()
	{
		ahrs = new AHRS(SerialPort.Port.kUSB1);
		ahrs.reset();
		initAngle = ahrs.getFusedHeading();
		//System.out.println(initAngle);
	}
	
	public static Navx getInstance()
	{
		if (instance == null)
		{
			instance = new Navx();
		}
		return instance;
	}
	
	public void reset()
	{
		ahrs.reset();
	}
	public double getFusedAngle()
	{
		return ((ahrs.getFusedHeading() + initAngle) % 360.0);
	}
	
	public void periodic()
	{
		
	}
	
	protected void initDefaultCommand() {

		
	}
}