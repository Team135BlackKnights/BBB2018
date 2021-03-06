
package org.usfirst.frc.team135.robot;

import org.usfirst.frc.team135.robot.commands.tele.DriveMandibleWheels;
import org.usfirst.frc.team135.robot.commands.tele.GrabMandibles;
import org.usfirst.frc.team135.robot.commands.tele.PShiftS;
import org.usfirst.frc.team135.robot.commands.tele.PShiftT;
import org.usfirst.frc.team135.robot.commands.tele.ReleaseMandibles;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI implements RobotMap{
	private static OI instance;
	
	public Joystick[] _joysticks = new Joystick[RobotMap.K_OI.NUMBER_OF_JOYSTICKS];
	
	private JoystickButton 
	MANDIBLES_OPEN, 
	MANDIBLES_CLOSE,
	RUN_MANDIBLE_WHEELS_OUT,
	RUN_MANDIBLE_WHEELS_IN,
	PNEUMATICS_SHIFT_TORQUE,
	PNEUMATICS_SHIFT_SPEED,
	THROW_CUBE;

	public OI()
	{
		_joysticks[RobotMap.K_OI.LEFT_JOYSTICK_ID] = new Joystick(RobotMap.K_OI.LEFT_JOYSTICK_ID);
		_joysticks[RobotMap.K_OI.RIGHT_JOYSTICK_ID] = new Joystick(RobotMap.K_OI.RIGHT_JOYSTICK_ID);
		_joysticks[RobotMap.K_OI.MANIP_JOYSTICK_ID] = new Joystick(RobotMap.K_OI.MANIP_JOYSTICK_ID);
		
		MANDIBLES_OPEN = new JoystickButton(_joysticks[RobotMap.K_OI.MANIP_JOYSTICK_ID], 6);
		MANDIBLES_OPEN.whenPressed(new GrabMandibles());
		
		MANDIBLES_CLOSE = new JoystickButton(_joysticks[RobotMap.K_OI.MANIP_JOYSTICK_ID], 4);
		MANDIBLES_CLOSE.whenPressed(new ReleaseMandibles());
		
		RUN_MANDIBLE_WHEELS_OUT = new JoystickButton(_joysticks[RobotMap.K_OI.MANIP_JOYSTICK_ID], RobotMap.K_OI.RUN_MANIP_F_ID);
		RUN_MANDIBLE_WHEELS_OUT.whileHeld(new DriveMandibleWheels(RobotMap.K_OI.isInwardF));
		
		RUN_MANDIBLE_WHEELS_IN = new JoystickButton(_joysticks[RobotMap.K_OI.MANIP_JOYSTICK_ID], RobotMap.K_OI.RUN_MANIP_R_ID);
		RUN_MANDIBLE_WHEELS_IN.whileHeld(new DriveMandibleWheels(RobotMap.K_OI.isInwardT));
		
		PNEUMATICS_SHIFT_TORQUE = new JoystickButton(_joysticks[RobotMap.K_OI.RIGHT_JOYSTICK_ID], RobotMap.K_OI.P_SHIFT_T_ID);
		PNEUMATICS_SHIFT_TORQUE.whenPressed(new PShiftT());
		
		PNEUMATICS_SHIFT_SPEED = new JoystickButton(_joysticks[RobotMap.K_OI.RIGHT_JOYSTICK_ID], RobotMap.K_OI.P_SHIFT_S_ID);
		PNEUMATICS_SHIFT_SPEED.whenPressed(new PShiftS());
		
		
		
		//THROW_CUBE = new JoystickButton(_joysticks[RobotMap.K_OI.MANIP_JOYSTICK_ID], RobotMap.K_OI.THROW_CUBE_ID);
		//THROW_CUBE.whenPressed(new ThrowCubeForward());	
	}
	
	public static OI getInstance() 
	{
		if (instance == null) {
			instance = new OI();
		}
		return instance;
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
	// returns an array of joystick x and y values, array[0] = x values, array[1] = y values
	public double[] GetLeftJoystickValues()
	{
		double[] getLeft = {deadband(_joysticks[RobotMap.K_OI.LEFT_JOYSTICK_ID].getX()), deadband(_joysticks[RobotMap.K_OI.LEFT_JOYSTICK_ID].getY())};
		return getLeft;
	}
	
	public double[] GetRightJoystickValues()
	{
		double [] getRight = {deadband(-_joysticks[RobotMap.K_OI.RIGHT_JOYSTICK_ID].getX()), deadband(_joysticks[RobotMap.K_OI.RIGHT_JOYSTICK_ID].getY())};
		return getRight;
	}
	public double[] GetManipJoystickValues()
	
	{
		double [] getManip = {deadband(_joysticks[RobotMap.K_OI.MANIP_JOYSTICK_ID].getX()), deadband(_joysticks[RobotMap.K_OI.MANIP_JOYSTICK_ID].getY())};
		return getManip;
	}
}