package org.usfirst.frc.team135.robot.commands.auto.groups;

import org.usfirst.frc.team135.robot.RobotMap;
import org.usfirst.frc.team135.robot.commands.auto.singles.DriveForward;
import org.usfirst.frc.team135.robot.commands.auto.singles.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MidToSwitch extends CommandGroup implements RobotMap
{
	System.out.println("MidToSwitch");
	
	public MidToSwitch(boolean isLeft)
	{
		System.out.println("MidToSwitch");
		if (isLeft)
		{
			addSequential(new Turn(90, 2));
		}
		else
		{
			addSequential(new Turn(45, 2));
		}
}
	}