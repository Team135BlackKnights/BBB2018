package org.usfirst.frc.team135.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team135.robot.RobotMap.SONARMAP;

import edu.wpi.first.wpilibj.*;

/**
 *
 */
public class UltrasonicSensor extends Subsystem {

    
    public Ultrasonic rightSonar = new Ultrasonic(SONARMAP.RIGHT_SONAR_TRIG_PORT, SONARMAP.RIGHT_SONAR_ECHO_PORT);
    public Ultrasonic frontSonar = new Ultrasonic(SONARMAP.FRONT_SONAR_TRIG_PORT, SONARMAP.FRONT_SONAR_ECHO_PORT);
    public Ultrasonic leftSonar = new Ultrasonic(SONARMAP.LEFT_SONAR_TRIG_PORT, SONARMAP.LEFT_SONAR_ECHO_PORT);
    public Ultrasonic backSonar = new Ultrasonic(SONARMAP.BACK_SONAR_TRIG_PORT, SONARMAP.BACK_SONAR_ECHO_PORT);
    
    private static UltrasonicSensor instance;
    
    
    public static UltrasonicSensor InitializeSubsystem()
    {
    	if (instance == null)
    	{
    		instance = new UltrasonicSensor();
    	}
    	return instance;
    }
    
    public UltrasonicSensor()
    {
    	rightSonar.setAutomaticMode(true);
    	leftSonar.setAutomaticMode(true);
    	backSonar.setAutomaticMode(true);
    	frontSonar.setAutomaticMode(true);
    	
    }
    
    public boolean isCubeInMandibles()
    {
    	return (getFrontSonarValue() < 7);
    }

	public double getRightSonarValue() {
		double RightSonarDistance = rightSonar.getRangeInches();
		SmartDashboard.putNumber("Right Sonar Distance: ", RightSonarDistance);
		return RightSonarDistance;
	}

	public double getLeftSonarValue() {
		double LeftSonarDistance = leftSonar.getRangeInches();
		SmartDashboard.putNumber("Left Sonar Distance: ", LeftSonarDistance);
		return LeftSonarDistance;
	}

	public double getBackSonarValue() {
		double BackSonarDistance = backSonar.getRangeInches();
		SmartDashboard.putNumber("Back Sonar Distance: ", BackSonarDistance);
		return BackSonarDistance;
	}

	public double getFrontSonarValue() {
		double FrontSonarDistance = frontSonar.getRangeInches();
		SmartDashboard.putNumber("Front Sonar Distance: ", FrontSonarDistance);
		return FrontSonarDistance;
	}
    	
    public void initDefaultCommand() {
        
    }
    
    public void periodic()
    {
    	SmartDashboard.putBoolean("Cube In Mandibles", isCubeInMandibles());
    	//System.out.println("Sonar: " + getLeftSonarValue() + ", " + getRightSonarValue());
    	getLeftSonarValue();
    	getBackSonarValue();
    	getFrontSonarValue();
    }

}

