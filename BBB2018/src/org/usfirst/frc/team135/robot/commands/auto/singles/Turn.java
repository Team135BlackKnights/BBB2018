package org.usfirst.frc.team135.robot.commands.auto.singles;

import org.usfirst.frc.team135.robot.Robot;
import org.usfirst.frc.team135.robot.RobotMap;
import org.usfirst.frc.team135.robot.utilities.PIDout;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turn extends TimedCommand implements RobotMap
{
	  PIDout buffer;
	  PIDController turnController;
	  double rotateToAngleRate;
	  double angletoturn;
	  
    public Turn(double angle, double timeout) {
    	super(timeout);
    	angletoturn = angle;
    	requires(Robot.drivetrain);
    }
    
    protected void initialize() {
    	turnController = new PIDController(AUTONOMOUS.kP, AUTONOMOUS.kI, AUTONOMOUS.kD, AUTONOMOUS.kF, Robot.navx.ahrs, buffer);
        turnController.setInputRange(-180.0f,  180.0f);
        turnController.setOutputRange(-1.0, 1.0);
        turnController.setAbsoluteTolerance(AUTONOMOUS.kToleranceDegrees);
        turnController.setContinuous(true);
    	turnController.enable();
    	SmartDashboard.putNumber("NavX: ", Robot.navx.ahrs.getAngle());
    	System.out.println(Robot.navx.ahrs.getAngle());
    }
    
    protected void execute()
    {
        double currentRotationRate = rotateToAngleRate;
    	Robot.drivetrain.TankDrive(1, 1, currentRotationRate);
    }
    
    protected boolean isFinished()
    {
    	return Robot.navx.ahrs.getAngle() < angletoturn;
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