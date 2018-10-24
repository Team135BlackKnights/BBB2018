package org.usfirst.frc.team135.robot.subsystems;
import org.usfirst.frc.team135.robot.RobotMap.LIMELIGHT;
import org.usfirst.frc.team135.robot.commands.camera.GetLimelightData;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Limelight extends Subsystem {
	//  Instance of the Subsystem that is used in Robot.java
	private static Limelight instance;
	
	//  Default Instance of NetworkTable that is created when the program is started
	NetworkTableInstance networkTableInstance = NetworkTableInstance.getDefault();
	
	//  Reading and Writing from the "limelight" Table in NetworkTables
	NetworkTable limelightTable = networkTableInstance.getTable("limelight");
	
	//  NetworkTableEntries for Reading Data from Limelight
	NetworkTableEntry validTargetEntry = limelightTable.getEntry("tv");
	NetworkTableEntry horizontalOffsetEntry = limelightTable.getEntry("tx");
	NetworkTableEntry verticalOffsetEntry = limelightTable.getEntry("ty");
	NetworkTableEntry targetAreaEntry = limelightTable.getEntry("ta");
	NetworkTableEntry targetSkewEntry = limelightTable.getEntry("tl");
	
	//  NetworkTableEntries for Writing Data to Limelight
	NetworkTableEntry ledModeEntry = limelightTable.getEntry("ledMode");
	NetworkTableEntry cameraModeEntry = limelightTable.getEntry("camMode");
	NetworkTableEntry limelightPipelineEntry = limelightTable.getEntry("pipeline");
		
	//  Stores the 5 Main Characteristics of the Target that the Limelight returns
	double[] limelightData = new double[LIMELIGHT.NUMBER_OF_LIMELIGHT_CHARACTERISTICS];
	
    //  Method used in Robot.java to Initialize the Subsystem to be used in the Commands
	public static Limelight getInstance()
    {
    	if (instance == null)
    	{
    		instance = new Limelight();
    	}
    	return instance;
    }
    
    //  Gets the Target Data and returns it in a Double Array
    public double[] GetLimelightData()
    {
    	limelightData[LIMELIGHT.VALID_TARGET] = validTargetEntry.getDouble(0.0);
    	limelightData[LIMELIGHT.HORIZONTAL_OFFSET] = horizontalOffsetEntry.getDouble(0.0);
    	limelightData[LIMELIGHT.VERTICAL_OFFSET] = verticalOffsetEntry.getDouble(0.0);
    	limelightData[LIMELIGHT.TARGET_AREA] = targetAreaEntry.getDouble(0.0);
    	limelightData[LIMELIGHT.TARGET_SKEW] = targetSkewEntry.getDouble(0.0);
    	
    	SmartDashboard.putNumber("Valid Target", limelightData[LIMELIGHT.VALID_TARGET]);
    	SmartDashboard.putNumber("Horizontal Offset", limelightData[LIMELIGHT.HORIZONTAL_OFFSET]);
    	SmartDashboard.putNumber("Vertical Offset", limelightData[LIMELIGHT.VERTICAL_OFFSET]);
    	SmartDashboard.putNumber("Target Area", limelightData[LIMELIGHT.TARGET_AREA]);
    	
    	return limelightData;
    }
    
    //  Returns True if a Valid Target exists with the currently set Vision Pipeline
    //  Returns False if there are no Valid Targets
    public boolean isTargetsExist()
    {
    	double numberOfValidTargets;
    	boolean targetsExist;
    	
    	numberOfValidTargets = validTargetEntry.getDouble(0.0);
    	
    	if (numberOfValidTargets > 0.0)
    	{
    		targetsExist = true;
    	}
    	else 
    	{
    		targetsExist = false;
    	}
    	
    	return targetsExist;
    }
    
    //  Sets the LED on the Limelight to be On, Off, or Blinking
    public void SetLEDMode(int onOrOff)
    {
    	ledModeEntry.setNumber(onOrOff);
    }
    
    //  Sets the Limelight to either be an Vision Processor or just a Driver Camera (No Vision Processing)
    public void SetCameraMode(int cameraMode)
    {
    	cameraModeEntry.setNumber(cameraMode);
    }
    
    //  Sets the Vision Pipeline to retrieve data from
    public void SetCameraPipeline(int pipeline)
    {
    	limelightPipelineEntry.setNumber(pipeline);
    }
    
    @Override
	public void initDefaultCommand() {
    	setDefaultCommand(new GetLimelightData());
    }
}

