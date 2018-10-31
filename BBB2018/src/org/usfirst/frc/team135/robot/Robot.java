package org.usfirst.frc.team135.robot;

import org.usfirst.frc.team135.robot.commands.auto.entrypoints.LeftPosition;
import org.usfirst.frc.team135.robot.commands.auto.entrypoints.MiddlePosition;
import org.usfirst.frc.team135.robot.commands.auto.entrypoints.RightPosition;
import org.usfirst.frc.team135.robot.commands.auto.groups.SideToLine;
import org.usfirst.frc.team135.robot.commands.auto.groups.SideToNearSwitch;
import org.usfirst.frc.team135.robot.subsystems.Arm;
import org.usfirst.frc.team135.robot.subsystems.DriveTrain;
import org.usfirst.frc.team135.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	// subsystem variables
	
	public static DriveTrain drivetrain;
	public static Intake intake;
	public static Arm arm;
	//public static Navx navx;
	//public static UltrasonicSensor ultrasonic;
	//public static Limelight limelight;
	public static OI oi;
	public Command autonomousCommand;
	public static String gameMessage;
	SendableChooser<String> chooser = new SendableChooser<>();
	@Override
	public void robotInit() {
		
		drivetrain = DriveTrain.getInstance();
		intake = Intake.getInstance();
		arm = Arm.getInstance();
		//navx = Navx.getInstance();
		//ultrasonic = UltrasonicSensor.getInstance();
		//limelight = Limelight.getInstance();
		oi = OI.getInstance();
		
		chooser.addDefault("Autoline", "Autoline");
		chooser.addObject("Left Position", "LeftPosition");
		chooser.addObject("Middle Position", "MiddlePosition");
		chooser.addObject("Right Position", "RightPosition");
		SmartDashboard.putData("Auto mode", chooser);
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
		gameMessage = DriverStation.getInstance().getGameSpecificMessage();
				
		String position = chooser.getSelected();
		String positionreal = Preferences.getInstance().getString("RobotPosition", "l");
		System.out.println(positionreal);
		
		if (position.equals("LeftPosition"))
		{
			autonomousCommand = new LeftPosition();
			System.out.println("LeftPosition");
		}
		else if (position.equals("MiddlePosition"))
		{
			autonomousCommand = new MiddlePosition();
			System.out.println("MiddlePosition");
		}
		else if (position.equals("RightPosition"))
		{
			autonomousCommand = new RightPosition();
			System.out.println("RightPosition");
		}
		else
		{
			autonomousCommand = new SideToLine(false);
		}

		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
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
