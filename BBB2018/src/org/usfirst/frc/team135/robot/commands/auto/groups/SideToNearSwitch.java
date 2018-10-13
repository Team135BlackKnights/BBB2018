package org.usfirst.frc.team135.robot.commands.auto.groups;

import org.usfirst.frc.team135.robot.RobotMap;
import org.usfirst.frc.team135.robot.commands.auto.singles.DriveForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SideToNearSwitch extends CommandGroup implements RobotMap
{
	public SideToNearSwitch(boolean isLeft)
	{
		addSequential(new DriveForward(AUTONOMOUS.FIELD.AUTO_LINE, isLeft, .1));
	}
}