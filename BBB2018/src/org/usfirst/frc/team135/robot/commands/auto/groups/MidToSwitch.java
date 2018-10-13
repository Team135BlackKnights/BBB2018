package org.usfirst.frc.team135.robot.commands.auto.groups;

import org.usfirst.frc.team135.robot.RobotMap;
import org.usfirst.frc.team135.robot.commands.auto.singles.DriveForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MidToSwitch extends CommandGroup implements RobotMap
{
	public MidToSwitch(boolean isLeft)
	{
	if (isLeft)
	{
		addSequential(new DriveForward(AUTONOMOUS.FIELD.AUTO_LINE, isLeft, .2));
	}
	else
	{
		addSequential(new DriveForward(AUTONOMOUS.FIELD.AUTO_LINE, isLeft, .5));
	}
}
	}