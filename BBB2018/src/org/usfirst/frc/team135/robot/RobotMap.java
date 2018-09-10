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
		public static final double DEADBAND = .05;
		public static final	int LEFT = 0, RIGHT = 1, MANIP = 2;
	}
	public interface DRIVETRAIN
	{
		public static final int 
		BACK_LEFT_ID = 0, 
		BACK_RIGHT_ID = 1, 
		FRONT_LEFT_ID = 2, 
		FRONT_RIGHT_ID = 3;
		public static final double 
		MAX_VELOCITY_TICKS_PER_100MS = 225,
		MAX_VELOCITY_TICKS = MAX_VELOCITY_TICKS_PER_100MS * 10, //Per second
		MAX_ACCELERATION_TICKS_PER_100MS = 1090,
		MAX_ACCELERATION_TICKS = MAX_ACCELERATION_TICKS_PER_100MS  * 10,
		MAX_JERK_TICKS_PER_100MS = 40000,
		MAX_JERK_TICKS = MAX_JERK_TICKS_PER_100MS * 10;
	}
}
