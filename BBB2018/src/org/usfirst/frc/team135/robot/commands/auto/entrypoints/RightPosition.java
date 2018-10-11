package org.usfirst.frc.team135.robot.commands.auto.entrypoints;

import org.usfirst.frc.team135.robot.Robot;
import org.usfirst.frc.team135.robot.commands.auton.groups.MidToSwitch;
import org.usfirst.frc.team135.robot.commands.auton.groups.SideToAutoline;
import org.usfirst.frc.team135.robot.commands.auton.groups.SideToNearScale;
import org.usfirst.frc.team135.robot.commands.auton.groups.SideToNearSwitch;
import org.usfirst.frc.team135.robot.commands.auton.singles.InitializeAngle;
import org.usfirst.frc.team135.robot.commands.teleop.ResetNavX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RightPosition extends CommandGroup {

	private static final int
	CLOSE = 1,
	FAR = 0,
	INVALID = -1;
    public RightPosition() {
    	int switchPosition = getSwitchPosition(Robot.msg);
    	int scalePosition = getScalePosition(Robot.msg);
    	
    	addSequential(new ResetNavX());
    	addSequential(new InitializeAngle(180));
    	
    	if (switchPosition == INVALID || scalePosition == INVALID)
    	{
    		addSequential(new SideToAutoline(true));
    		System.out.println("Invalid message. Ran autoline. Terminating auto...");
    		return;
    	}
    	
    	
    	if (SmartDashboard.getBoolean("Try to go for Switch?", true) && !SmartDashboard.getBoolean("Try to go for Scale?", false))
    	{
    		
    		//Go for switch only
    		if (switchPosition == CLOSE)
    		{
    			addSequential(new SideToNearSwitch(true));
    		}
    		else
    		{
    			addSequential(new SideToAutoline(true));
    		}
    	}
    	else if (!SmartDashboard.getBoolean("Try to go for Switch?", true) && SmartDashboard.getBoolean("Try to go for Scale?", false))
    	{
    		//Go for scale only
    		if (scalePosition == CLOSE)
    		{
    			addSequential(new SideToNearScale(true));
    		}
    		else
    		{
    			addSequential(new SideToAutoline(true));
    		}
    		
    	}
    	else
    	{
    		//Either you want it all or are extremely indecive and didn't select anything. Either way here's a balanced auto
    		
    		if (switchPosition == CLOSE && scalePosition == CLOSE)
    		{
    			if (SmartDashboard.getBoolean("Prefer Switch or Scale?", true))
    			{
    				addSequential(new SideToNearSwitch(true));
    			}
    			else
    			{
    				addSequential(new SideToNearScale(true));
    			}
    		}
    		else if (switchPosition == FAR && scalePosition == FAR)
    		{
    			if (SmartDashboard.getBoolean("Prefer Switch or Scale?", true))
    			{
    				addSequential(new SideToAutoline(true));
    			}
    			else
    			{
    				addSequential(new SideToAutoline(true));
    			}
    		}
    		else if (switchPosition == CLOSE && scalePosition == FAR)
    		{
    			addSequential(new SideToNearSwitch(true));
    		}
    		else if (switchPosition == FAR && scalePosition == CLOSE)
    		{
    			addSequential(new SideToNearScale(true));
    		}
    		
    	}

    }
    
    private int getSwitchPosition(String msg)
    {
    	if (msg.toUpperCase().charAt(0) == 'R') //Switch is straight up from us
    	{
    		return CLOSE;
    	}
    	else if (msg.toUpperCase().charAt(0)  == 'L') //Switch is far away from us
    	{
    		return FAR;
    	}
    	else
    	{
    		System.out.println("Unable to get a valid game specific message. Only running autoline.");
    		return INVALID;
    	}
    }
    
    private int getScalePosition(String msg)
    {
    	if (msg.toUpperCase().charAt(1)  == 'R') //Switch is straight up from us
    	{
    		return CLOSE;
    	}
    	else if (msg.toUpperCase().charAt(1)  == 'L') //Switch is far away from us
    	{
    		return FAR;
    	}
    	else
    	{
    		System.out.println("Unable to get a valid game specific message. Only running autoline.");
    		return INVALID;
    	}
    }
}
