package org.usfirst.frc.team135.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.CANifier.PWMChannel;
import com.ctre.phoenix.CANifierStatusFrame;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;

import com.ctre.phoenix.ILoopable;
import java.nio.ByteBuffer;
import edu.wpi.first.wpilibj.can.CANJNI;

import org.usfirst.frc.team135.robot.RobotMap;
/**
 *
 */
public class Canifier extends Subsystem implements RobotMap{

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	static public Canifier instance;
	static public CANifier canifier;
	

	CANifier.PWMChannel backLidar = CANifier.PWMChannel.PWMChannel0;
	CANifier.PWMChannel frontLidar = CANifier.PWMChannel.PWMChannel1;
	CANifier.PWMChannel leftLidar = CANifier.PWMChannel.PWMChannel2;
	CANifier.PWMChannel rightLidar = CANifier.PWMChannel.PWMChannel3;

    double[][] dutyCycleAndPeriods = new double[][] { new double[] { 0, 0 }, new double[] { 0, 0 }, new double[] { 0, 0 }, new double[] { 0, 0 } };

    
	public Canifier()
	{
		canifier = new CANifier(0);
	}
	public static Canifier getInstance()
	{
		if (instance == null)
		{
			instance = new Canifier();
		}
		return instance;
	}
	
	public double GetCanifierVoltage() 
	{
		double value = canifier.getBusVoltage();
		System.out.print("made it to voltage");
		System.out.print(value);
		return value;
	}
	
	public double getMeasuredPulseWidths(CANifier.PWMChannel pwmCh)
	{
        canifier.getPWMInput(CANifier.PWMChannel.PWMChannel0, dutyCycleAndPeriods[0]);  //reads from all the PWM Channels
        canifier.getPWMInput(CANifier.PWMChannel.PWMChannel1, dutyCycleAndPeriods[1]);
        canifier.getPWMInput(CANifier.PWMChannel.PWMChannel2, dutyCycleAndPeriods[2]);
        canifier.getPWMInput(CANifier.PWMChannel.PWMChannel3, dutyCycleAndPeriods[3]); 
        
      /*  ByteBuffer data = ByteBuffer.allocate(4);                              //sends CAN data
        data.asIntBuffer().put((int)(dutyCycleAndPeriods[3][0] *1000));
        byte[] newdata = new byte[4];
        data.get(newdata);
        CANJNI.FRCNetCommCANSessionMuxSendMessage(0x1E040000, newdata, 4); */
        
		return dutyCycleAndPeriods[pwmCh.value][0];   
	}
	
	public double getFrontLidarCM()
	{
		return getMeasuredPulseWidths(frontLidar)/10;
	}
	
	public double getBackLidarCM()
	{
		return getMeasuredPulseWidths(backLidar)/10;
	}
	
	public double getLeftLidarCM()
	{
		return getMeasuredPulseWidths(leftLidar)/10;
	}
	
	public double getRightLidarCM()
	{
		return getMeasuredPulseWidths(rightLidar)/10;
	}
	
	public double getFrontLidarInches()
	{
		return (getFrontLidarCM() / 2.54);
	}
	
	public double getBackLidarInches()
	{
		return (getBackLidarCM() / 2.54);
	}
	
	public double getLeftLidarInches()
	{
		return (getLeftLidarCM() / 2.54);
	}
	
	public double getRightLidarInches()
	{
		return (getRightLidarCM() / 2.54);
	}
	
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        
    }
    
    public void periodic()
    {		
    	System.out.println(("Lidar" + getBackLidarInches() + ", " + getFrontLidarInches() + ", " 
    								+ getLeftLidarInches() + ", " + getRightLidarInches()));
    }
}