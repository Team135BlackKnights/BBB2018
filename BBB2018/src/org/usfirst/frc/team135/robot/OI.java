/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team135.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI implements RobotMap{
	private static OI instance;
	
	private Joystick[] _joysticks = new Joystick[3];
	
	private JoystickButton 
		MANDIBLES_OPEN, 
		MANDIBLES_CLOSE;
	
	public static OI getInstance() 
	{
		if (instance == null) {
			instance = new OI();
		}
		return instance;
	}
	
	private OI()
	{
		_joysticks[0] = new Joystick(0);
		_joysticks[1] = new Joystick(1);
		_joysticks[2] = new Joystick(2);
		
		MANDIBLES_OPEN = new JoystickButton(_joysticks[2], RobotMap.K_OI.MANIP_OPEN_ID);
		MANDIBLES_CLOSE = new JoystickButton(_joysticks[2], RobotMap.K_OI.MANIP_CLOSE_ID);
		
		//MANDIBLES_CLOSE.whenPressed(new GrabMandibles());
		//MANDIBLES_OPEN.whenPressed(new ReleaseMandibles());
	}
	private double deadband(double input)
	{
		if(Math.abs(input) < RobotMap.K_OI.DEADBAND)
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
		double[] getLeft = {deadband(_joysticks[0].getY()), deadband(_joysticks[0].getX())};
		return getLeft;
	}
	
	public double[] GetRightY()
	{
		double [] getRight = {deadband(-_joysticks[1].getY()), deadband(_joysticks[1].getX())};
		return getRight;
	}

}
