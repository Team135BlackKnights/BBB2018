package org.usfirst.frc.team135.robot;

import org.usfirst.frc.team135.robot.commands.auto.entrypoints.LeftPosition;
import org.usfirst.frc.team135.robot.subsystems.Arm;
import org.usfirst.frc.team135.robot.subsystems.DriveTrain;
import org.usfirst.frc.team135.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

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
		
		//if (position.equals("LeftPosition"))
		//{
			System.out.println("Left Position");
			autonomousCommand = new LeftPosition();
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
