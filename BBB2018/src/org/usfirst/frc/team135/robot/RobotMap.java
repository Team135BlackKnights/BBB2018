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
		BACK_LEFT_ID = 0, 
		BACK_RIGHT_ID = 1, 
		FRONT_LEFT_ID = 2, 
		FRONT_RIGHT_ID = 3;
		public static final int[]
		MOTOR_ID_ARRAY = {BACK_LEFT_ID, BACK_RIGHT_ID, FRONT_LEFT_ID, FRONT_RIGHT_ID};
		public static final int
		BACK_LEFT_MOTOR = 0, 
		BACK_RIGHT_MOTOR = 1, 
		FRONT_LEFT_MOTOR = 0, 
		FRONT_RIGHT_MOTOR = 1;
		public static final int
		NUMBER_OF_TALONS = 2;
		
		public static final int 
		NUMBER_OF_VICTORS = 2;
		
		public static final boolean
		IS_DRIVETRAIN_TALON = true;
		
		public interface PID
		{
			public static final int
			TIMEOUT_MS = 10;
			public static final int[]
			kP = {0, 0, 0, 0}, 
			kI = {0, 0, 0, 0}, 
			kD = {0, 0, 0, 0}, 
			kF = {0, 0, 0, 0};
			public static final double[]
			setPoints = {0, 0, 0, 0},
			orientationHelper = {0, 0, 0};
		}
		
		public interface ENCODERS
		{
			public static final int ENCODER_TICK_COUNT = 256;
			public static final int ENCODER_QUAD_COUNT = (ENCODER_TICK_COUNT * 4);
			
			public static final double 
			MAX_VELOCITY_TICKS_PER_100MS = 225,
			MAX_VELOCITY_TICKS = MAX_VELOCITY_TICKS_PER_100MS * 10, //Per second
			MAX_ACCELERATION_TICKS_PER_100MS = 1090,
			MAX_ACCELERATION_TICKS = MAX_ACCELERATION_TICKS_PER_100MS  * 10;
		
			public static final double
			WHEEL_DIAMETER = 4, //Inches
			TRACK_WIDTH = 22.626; //Inches
		
			public static final int 
			BACK_LEFT_ENCODER = BACK_LEFT_ID,
			BACK_RIGHT_ENCODER = BACK_RIGHT_ID;
		}
	}
	public interface INTAKE
	{
		public static final int
		LEFT_WHEEL_ID = 0,
		RIGHT_WHEEL_ID =1;
		public static final boolean 
		rightWheelInverted = false,
		leftWheelInverted = true;
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
		public static final int 
		TALON_ID = 4,
		ARM_VICTOR_ID_1 = 5,
		ARM_VICTOR_ID_2 = 6;
		public static final int 
		TALON = 0,
		ARM_VICTOR_1 = 1,
		ARM_VICTOR_2 = 2;
		
		public static final boolean
		IS_ARM_TALON = false;
		
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
	}
	
		public interface CONVERSIONS
		{
			public static final double
				INCHES2METERS = 0.0254, //meters/inch
				TICKS2INCHES = 0.0704, //inches/tick
				INCHES2TICKS = 1 / TICKS2INCHES, //ticks/inch
				TICKS2METERS = TICKS2INCHES * INCHES2METERS,		//rev/inches * inches/tick = REV/TICK
				TICKS2REVS = (1 / (4 * Math.PI)) * TICKS2INCHES,
				REVS2TICKS = 1 / TICKS2REVS,
				TICKS2RADIANS = TICKS2REVS * (2 * Math.PI), //Revs/tick * radians/rev = radians/tick
				RADIANS2TICKS = 1 / TICKS2RADIANS,
				TICKS2DEGREES = TICKS2REVS * 360, //Revs/tick * degrees/rev
				DEGREES2TICKS = 1 / TICKS2DEGREES;
		}
		public interface FIELD {
			public static final double // All measurements are in inches
				AUTO_LINE = 70, 
				WALL_SLANT_END = 10, 
				SIDE_SCALE_X = 37.5f, 
				SIDE_SCALE_Y = 326 - 48, 
				SIDE_SWITCH_X = 51f,
				SIDE_SWITCH_Y = 120, 
				MID_SWITCH_X = 105f, 
				MID_SWITCH_Y = 160,
				FAR_SCALE_X = 189f,
				FAR_SCALE_Y = 17.5,
				FAR_SWITCH_X = 160,
				
				FAR_SCALE_DISTANCE_FROM_WALL = 202, //222,
				FAR_SCALE_DISTANCE_TO_SCALE = 125; //200
		}
	
}
