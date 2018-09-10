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
		public static final int BACK_LEFT = 0, BACK_RIGHT = 1, FRONT_LEFT = 2, FRONT_RIGHT = 3;
	}
}
