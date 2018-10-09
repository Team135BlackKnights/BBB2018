/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team135.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team135.robot.subsystems.*;

public class Robot extends TimedRobot {
	// subsystem variables
	public static OI oi;
	public static DriveTrain drivetrain;
	public static Intake intake;
	public static Arm arm;
	public static Navx navx;
	public static UltrasonicSensor ultrasonic;
	public static Limelight limelight; 
	public static PDP pdp;
	public Command _autonomousCommand;
	SendableChooser<String> _chooser = new SendableChooser<>();
	@Override
	public void robotInit() {
		oi = OI.getInstance();
		drivetrain = DriveTrain.getInstance();
		intake = Intake.getInstance();
		arm = Arm.getInstance();
		navx = Navx.getInstance();
		ultrasonic = UltrasonicSensor.getInstance();
		limelight = Limelight.getInstance();
		pdp = PDP.getInstance();
	}
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		if (_autonomousCommand != null) {
			_autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (_autonomousCommand != null) {
			_autonomousCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
	}
}
