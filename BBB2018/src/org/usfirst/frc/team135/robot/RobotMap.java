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
		public static final double 
		DEADBAND = .1;
		public static final	int 
		LEFT_JOYSTICK_ID = 0, 
		RIGHT_JOYSTICK_ID = 1, 
		MANIP_JOYSTICK_ID = 2;
		public static final	int 
		GETX = 0, 
		GETY = 1;
		public static final int 
		MANIP_OPEN_ID = 6, 
		MANIP_CLOSE_ID = 4, 
		RUN_MANIP_F_ID = 1, 
		RUN_MANIP_R_ID =2, 
		THROW_CUBE_ID = 3;
		public static final int 
		NUMBER_OF_JOYSTICKS = 3;
		public static boolean 
		isInwardF = false,
		isInwardT = true;
	}
	
	public interface DRIVETRAIN
	{
		public static final int 
		BACK_LEFT_ID = 1, 
		BACK_RIGHT_ID = 11, 
		FRONT_LEFT_ID = 12, 
		FRONT_RIGHT_ID = 2;
		
		public static final boolean
		IS_DRIVETRAIN_TALON = true;
		
		public interface PID
		{
			public static final int
			TIMEOUT_MS = 10;
			public static final int[]
			kP = {0, 0}, 
			kI = {0, 0}, 
			kD = {0, 0}, 
			kF = {0, 0};
		}
	}
	
	public interface INTAKE
	{
		public static final int
		LEFT_WHEEL_ID = 4,
		RIGHT_WHEEL_ID = 15;
		public static final boolean 
		rightWheelInverted = false,
		leftWheelInverted = true;
		public static final double
		TIME_OUT_SECONDS = 0.5f;
	}
	
	public interface PNEUMATICS 
	{
		public static final int
		COMPRESSOR_ID = 0;
		public static final int 
		MANDIBLE_OPEN_CHANNEL = 0,
		MANDIBLE_CLOSE_CHANNEL = 1;
		public static final int
		RETRACT_IN_CHANNEL = 2,
		RETRACT_OUT_CHANNEL = 3;
	}
	public interface ARM 
	{
		public static final int 
		TALON_ID = 3,
		ARM_VICTOR_ID_1 = 13,
		ARM_VICTOR_ID_2 = 14;
		
		public static final boolean
		IS_DRIVETRAIN_TALON = false;
		
		public static final double
		kP = 4,
		kI = 0,
		kD = 3 * Math.sqrt(kP),
		kF = 12;
		public static final int
		TIMEOUT_MS = 10;
	}
	public interface SONARMAP {
			public static final int 
			RIGHT_SONAR_TRIG_PORT = 3,
		    RIGHT_SONAR_ECHO_PORT = 2,
		    LEFT_SONAR_TRIG_PORT = 1,
		    LEFT_SONAR_ECHO_PORT = 0,
		    FRONT_SONAR_TRIG_PORT = 4,
		    FRONT_SONAR_ECHO_PORT = 5,		    
		    BACK_SONAR_TRIG_PORT = 7,
		    BACK_SONAR_ECHO_PORT = 8;
			public static final int 
			FRONT_SONAR = 0,
			RIGHT_SONAR = 1,
			BACK_SONAR = 2,
			LEFT_SONAR = 3;
			public static final int[]
			TRIG_PORT_ARRAYS = {FRONT_SONAR_TRIG_PORT, RIGHT_SONAR_TRIG_PORT, BACK_SONAR_TRIG_PORT, LEFT_SONAR_TRIG_PORT},
			ECHO_PORT_ARRAYS = {FRONT_SONAR_ECHO_PORT, RIGHT_SONAR_ECHO_PORT, BACK_SONAR_ECHO_PORT, LEFT_SONAR_ECHO_PORT};
			public static final int 
			NUMBER_OF_SONARS = 4;
			public static final int
			CUBE_DISTANCE_FRONT_TO_FRONT_SONOR = 18;
	}
	public interface LIMELIGHT
	{
		public static final int NUMBER_OF_LIMELIGHT_CHARACTERISTICS = 5;

		//  Elements for limelightData Array
		public static final int VALID_TARGET = 0;
		public static final int HORIZONTAL_OFFSET = 1;
		public static final int VERTICAL_OFFSET = 2;
		public static final int TARGET_AREA = 3;
		public static final int TARGET_SKEW = 4;
		//  LED Modes
		public static int LED_ON = 0;
		public static int LED_OFF = 1;
		public static int LED_BLINKING = 2;
		
		//  Camera Modes
		public static int VISION_PROCESSOR = 0;
		public static int DRIVER_CAMERA = 1; 
		
		//  Pipeline Options
		public static int YELLOW_BLOCK_PIPELINE = 0;
	}
	public interface AUTONOMOUS
	{
		public static final int
		CLOSE = 1,
		FAR = 0,
		INVALID = -1;
		public static final boolean
		IS_LEFT = true,
		IS_RIGHT = false;

		public static final double
		TIME_PERIOD = .05;
		
		static final double 
		kP = 0.03,
		kI = 0.00,
		kD = 0.00,
		kF = 0.00;
		static final double kToleranceDegrees = 2.0f;
		
		public interface FIELD {
			public static final double // All measurements are in feet
				
				LEFT_NEAR_SWITCH_X = 196 / 12,
				LEFT_NEAR_SWITCH_Y = 9,
				FIELD_LENGTH = 30,
				RIGHT_NEAR_SWITCH_X = 196 / 12,
				RIGHT_NEAR_SWITCH_Y = 21,
				SCALE_X = 4,
				SCALE_Y = 3,
				SCALE_BOX_Y = 3,
				LEFT_POSITION_YDISTANCE = 4,
				MIDDLE_POSITION_YDISTANCE = 15,
				RIGHT_POSITION_YDISTANCE = 26,
				AUTO_LINE = 10;
				

		}
	}
}
