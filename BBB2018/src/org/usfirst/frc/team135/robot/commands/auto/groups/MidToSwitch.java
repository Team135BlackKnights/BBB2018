package org.usfirst.frc.team135.robot.commands.auto.groups;

import org.usfirst.frc.team135.robot.RobotMap;
import org.usfirst.frc.team135.robot.RobotMap.AUTONOMOUS;
import org.usfirst.frc.team135.robot.commands.auto.singles.DriveForward;
import org.usfirst.frc.team135.robot.commands.auto.singles.DriveMandiblesWheelsAuto;
import org.usfirst.frc.team135.robot.commands.auto.singles.Turn;
import org.usfirst.frc.team135.robot.commands.tele.DriveMandibleWheels;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MidToSwitch extends CommandGroup
{	
	public MidToSwitch(boolean isLeft)
	{
		if (isLeft)
		{
			addSequential(new DriveForward(6));
			addSequential(new Turn(-30));
			addSequential(new DriveForward(5));
			addSequential(new Turn(75));
			addSequential(new DriveForward(5));
			addSequential(new DriveMandiblesWheelsAuto(1, 1));
		}
		else 
		{
			addSequential(new DriveForward(6));
			addSequential(new Turn(95));
			addSequential(new DriveForward(11));
			addSequential(new DriveMandiblesWheelsAuto(1, 1));
		}
	}
	}