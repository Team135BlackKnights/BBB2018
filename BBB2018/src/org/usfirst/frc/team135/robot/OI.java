
package org.usfirst.frc.team135.robot;

import org.usfirst.frc.team135.robot.commands.subsystem_Commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI implements RobotMap{
	private static OI instance;
	
	public Joystick[] _joysticks = new Joystick[RobotMap.K_OI.NUMBER_OF_JOYSTICKS];
	
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
	
	public OI()
	{
		_joysticks[RobotMap.K_OI.LEFT_JOYSTICK_ID] = new Joystick(RobotMap.K_OI.LEFT_JOYSTICK_ID);
		_joysticks[RobotMap.K_OI.RIGHT_JOYSTICK_ID] = new Joystick(RobotMap.K_OI.RIGHT_JOYSTICK_ID);
		_joysticks[RobotMap.K_OI.MANIP_JOYSTICK_ID] = new Joystick(RobotMap.K_OI.MANIP_JOYSTICK_ID);
		
		MANDIBLES_OPEN = new JoystickButton(_joysticks[RobotMap.K_OI.MANIP_JOYSTICK_ID], RobotMap.K_OI.MANIP_OPEN_ID);
		MANDIBLES_CLOSE = new JoystickButton(_joysticks[RobotMap.K_OI.MANIP_JOYSTICK_ID], RobotMap.K_OI.MANIP_CLOSE_ID);
		
		MANDIBLES_CLOSE.whenPressed(new GrabMandibles());
		MANDIBLES_OPEN.whenPressed(new ReleaseMandibles());
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
		double[] getLeft = {deadband(_joysticks[RobotMap.K_OI.LEFT_JOYSTICK_ID].getX()), deadband(_joysticks[RobotMap.K_OI.LEFT_JOYSTICK_ID].getY())};
		return getLeft;
	}
	
	public double[] GetRight()
	{
		double [] getRight = {deadband(-_joysticks[RobotMap.K_OI.RIGHT_JOYSTICK_ID].getX()), deadband(_joysticks[RobotMap.K_OI.RIGHT_JOYSTICK_ID].getY())};
		return getRight;
	}
	public double[] GetManip()
	{
		double [] getManip = {deadband(_joysticks[RobotMap.K_OI.MANIP_JOYSTICK_ID].getX()), deadband(_joysticks[RobotMap.K_OI.MANIP_JOYSTICK_ID].getY())};
		return getManip;
	}
}