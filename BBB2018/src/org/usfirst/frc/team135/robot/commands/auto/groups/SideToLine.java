package org.usfirst.frc.team135.robot.commands.auto.groups;

import org.usfirst.frc.team135.robot.RobotMap;
import org.usfirst.frc.team135.robot.RobotMap.AUTONOMOUS;
import org.usfirst.frc.team135.robot.commands.auto.singles.DriveForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SideToLine extends CommandGroup implements RobotMap
{
	public SideToLine(boolean isbackward)
	{
		addSequential(new DriveForward(AUTONOMOUS.FIELD.AUTO_LINE, isbackward, .2));
	}
}