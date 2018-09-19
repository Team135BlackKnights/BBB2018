/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team135.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public interface RobotMap 
{
	public interface K_OI
	{
		public static final double DEADBAND = .1;
		public static final	int LEFT_JOYSTICK_ID = 0, RIGHT_JOYSTICK_ID = 1, MANIP_JOYSTICK_ID = 2;
		public static final	int GETX = 0, GETY = 1;
		public static final int MANIP_OPEN_ID = 6, MANIP_CLOSE_ID = 4, 
					RUN_MANIP_F_ID =1, RUN_MANIP_R_ID =2, THROW_CUBE_ID = 3;
		public static final int NUMBER_OF_JOYSTICKS = 3;
	}
	public interface DRIVETRAIN
	{
		public static final int 
		BACK_LEFT_ID = 0, 
		BACK_RIGHT_ID = 1, 
		FRONT_LEFT_ID = 2, 
		FRONT_RIGHT_ID = 3;
		public static final int ENCODER_TICK_COUNT = 256;
		public static final int ENCODER_QUAD_COUNT = (ENCODER_TICK_COUNT * 4);
				
		public static final int 
		BACK_LEFT_ENCODER = BACK_LEFT_ID,
		BACK_RIGHT_ENCODER = BACK_RIGHT_ID;
	}
	public interface INTAKE
	{
		public static final int
		LEFT_WHEEL_ID = 0,
		RIGHT_WHEEL_ID =1;
	}
	
	public interface PNEUMATICS 
	{
		public static final int 
		MANDIBLE_OPEN_CHANNEL =0,
		MANDIBLE_CLOSE_CHANNEL =1;

		public static final int
		RETRACT_IN_CHANNEL = 2,
		RETRACT_OUT_CHANNEL = 3;
	}
	public interface ARM 
	{
		public static final int ARM_MOTOR_ID_1 = 4;
		public static final int ARM_MOTOR_ID_2 = 5; 
	
	}
}
