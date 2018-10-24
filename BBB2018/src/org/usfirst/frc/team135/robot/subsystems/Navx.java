
package org.usfirst.frc.team135.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Navx extends Subsystem
{
	private static Navx instance;
	public double initAngle;
	public AHRS ahrs;	
	
	private Navx()
	{
		ahrs = new AHRS(SerialPort.Port.kUSB1);
		ahrs.reset();
		initAngle = ahrs.getFusedHeading();
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
	
	@Override
	public void periodic()
	{
		
	}
	
	@Override
	protected void initDefaultCommand() {

		
	}
}