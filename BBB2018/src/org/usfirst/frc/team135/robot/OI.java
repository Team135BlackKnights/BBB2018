/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team135.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI implements RobotMap{
	private static OI instance;
	
	private Joystick LEFT, RIGHT, MANIP;
	
	public static OI getInstance() {
		if (instance == null) {
			instance = new OI();
		}
		return instance;
	}
	
	private OI()
	{
		LEFT = new Joystick(0);
		RIGHT = new Joystick(1);
		MANIP = new Joystick(2);
		
	}
	private double deadband(double input)
	{
		if(Math.abs(input) < .1)
		{
			return 0;
		}
		else
		{
			return input;
		}
	}
	
	public double[] GetLeft()
	{
		double[] getLeft = {deadband(LEFT.getY()), deadband(LEFT.getX())};
		return getLeft;
	}
	
	public double[] GetRightY()
	{
		double [] getRight = {deadband(-RIGHT.getY()), deadband(RIGHT.getX())};
		return getRight;
	}
	
	public double GetRightX()
	{
		return deadband(RIGHT.getX());
	}

}
