package org.usfirst.frc.team135.robot.commands.auto.groups;

import org.usfirst.frc.team135.robot.RobotMap;
import org.usfirst.frc.team135.robot.commands.auto.singles.DriveForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SideToLIne extends CommandGroup implements RobotMap
{
	public void SideToLine(boolean isbackward)
	{
		addSequential(new DriveForward(FIELD.AUTO_LINE, isbackward, 2));
	}
}
