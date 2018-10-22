package org.usfirst.frc.team135.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team135.robot.commands.auto.entrypoints.LeftPosition;
import org.usfirst.frc.team135.robot.commands.auto.entrypoints.MiddlePosition;
import org.usfirst.frc.team135.robot.commands.auto.entrypoints.RightPosition;
import org.usfirst.frc.team135.robot.commands.auto.groups.SideToLine;
import org.usfirst.frc.team135.robot.subsystems.*;

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
		int commandtotest = 0;
		//if (position.equals("LeftPosition"))
		//{
		switch (commandtotest)
		{
			case 0:
				System.out.println("Left Position");
				autonomousCommand = new LeftPosition();
				break;
			case 1:
				System.out.println("Middle Position");
				autonomousCommand = new MiddlePosition();
				break;
			case 2:
				System.out.println("Right Position");
				autonomousCommand = new RightPosition();
				break;
		}

		/*}
		else if (position.equals("MiddlePosition"))
		{
			System.out.println("Middle Position");
			autonomousCommand = new MiddlePosition();
		}
		else if (position.equals("RightPosition"))
		{
			System.out.println("Right Position");
			autonomousCommand = new RightPosition();
		}
		else
		{
			autonomousCommand = new SideToLine(false);
		}
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
		*/
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
