package org.usfirst.frc.team135.robot.commands.auto.singles;

import org.usfirst.frc.team135.robot.Robot;
import org.usfirst.frc.team135.robot.RobotMap;
import org.usfirst.frc.team135.robot.subsystems.DriveTrain;
import org.usfirst.frc.team135.robot.utilities.PIDout;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turn extends InstantCommand implements RobotMap
{
	  PIDout buffer;
	  PIDController turnController;
	  double rotateToAngleRate;
	  double angletoturn;
	  
    public Turn(double angle) {
    	angletoturn = angle;
    	requires(Robot.drivetrain);
    }
    
    protected void initialize() {
    	double distancetotravel = Math.sqrt(2 * 21.63 * 21.63 * (1 - Math.cos(Math.toRadians(angletoturn))));
    	double distancetravelled = 0;
    	Timer timer = new Timer();
		timer.start();
		Robot.drivetrain.TankDrive(-1.0, 1.0);
		double time = timer.get();
		while (distancetravelled < distancetotravel / 12 && timer.get() - time > .2)
		{
			double currentvoltage = DriveTrain.frontRightMotor.getMotorOutputVoltage();
			double estimatedvelocity = (currentvoltage - 1.25) * 1.25;
			distancetravelled += estimatedvelocity * .2;
			double error = (distancetotravel - distancetravelled) / distancetotravel;
			Robot.drivetrain.TankDrive(-1.0 * error, 1.0 * error);
			System.out.println(currentvoltage);
			System.out.println(estimatedvelocity);
			System.out.println(distancetravelled);
			time = timer.get();
		}
    }
    
    protected void execute()
    {
    	
    	/*
    	turnController = new PIDController(AUTONOMOUS.kP, AUTONOMOUS.kI, AUTONOMOUS.kD, AUTONOMOUS.kF, Robot.navx.ahrs, buffer);
        turnController.setInputRange(-180.0f,  180.0f);
        turnController.setOutputRange(-1.0, 1.0);
        turnController.setAbsoluteTolerance(AUTONOMOUS.kToleranceDegrees);
        turnController.setContinuous(true);
    	turnController.enable();
    	SmartDashboard.putNumber("NavX: ", Robot.navx.ahrs.getAngle());
    	System.out.println(Robot.navx.ahrs.getAngle());
        double currentRotationRate = rotateToAngleRate;
    	Robot.drivetrain.TankDrive(1, 1, currentRotationRate);
    	*/
    }
    
    protected boolean isFinished()
    {
    	return false;
    }
    protected void interupted()
    {
    	end();
    }
    protected void end()
    {
    	Robot.drivetrain.stopMotors();
    }
}