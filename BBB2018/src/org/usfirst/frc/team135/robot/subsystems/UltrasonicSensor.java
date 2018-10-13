package org.usfirst.frc.team135.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team135.robot.RobotMap.SONARMAP;

import edu.wpi.first.wpilibj.*;

public class UltrasonicSensor extends Subsystem {
    private static UltrasonicSensor instance;

	public static Ultrasonic[] sonarArray;
    public static Ultrasonic 
    rightSonar,
    frontSonar,
    leftSonar,
    backSonar;
    
    public static UltrasonicSensor getInstance()
    {
    	if (instance == null)
    	{
    		instance = new UltrasonicSensor();
    	}
    	return instance;
    }
    
    public UltrasonicSensor()
    {
    	sonarArray = new Ultrasonic[SONARMAP.NUMBER_OF_SONARS];
    	for (int i = 0; i < SONARMAP.NUMBER_OF_SONARS; i++)
    	{
    		sonarArray[i] = new Ultrasonic(SONARMAP.TRIG_PORT_ARRAYS[i], SONARMAP.ECHO_PORT_ARRAYS[i]);
    		sonarArray[i].setAutomaticMode(true);
    	}
    }
    
    public boolean isCubeInMandibles()
    {
    	return (getSonarValues()[SONARMAP.FRONT_SONAR] < SONARMAP.CUBE_DISTANCE_FRONT_TO_FRONT_SONOR);
    }

    public double[] getSonarValues()
    {
    	double[] sonarDistances = {sonarArray[SONARMAP.FRONT_SONAR].getRangeInches(), 
    			sonarArray[SONARMAP.RIGHT_SONAR].getRangeInches(), 
    			sonarArray[SONARMAP.BACK_SONAR].getRangeInches(), 
    			sonarArray[SONARMAP.LEFT_SONAR].getRangeInches()};
		return sonarDistances;
    }
    	
    public void initDefaultCommand() {
        
    }
    
    public void periodic()
    {
    	SmartDashboard.putBoolean("Cube In Mandibles", isCubeInMandibles());
    	double[] sonar = getSonarValues();
    	System.out.println("Sonar: " + sonar);
    	for (int i = 0; i < SONARMAP.NUMBER_OF_SONARS; i++)
    	{
    		SmartDashboard.putNumber("Sonar Number " + i + " Distance: ", sonar[i]);
    	}
    }

}

