package org.usfirst.frc.team135.robot.commands.auto.groups;

import org.usfirst.frc.team135.robot.RobotMap.AUTONOMOUS;
import org.usfirst.frc.team135.robot.commands.auto.singles.DriveForward;
import org.usfirst.frc.team135.robot.commands.auto.singles.DriveMandiblesWheelsAuto;
import org.usfirst.frc.team135.robot.commands.auto.singles.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SideToNearSwitch extends CommandGroup 
{
	public SideToNearSwitch(boolean isLeft)
	{
		addSequential(new DriveForward(AUTONOMOUS.FIELD.AUTO_LINE));
		addSequential(new Turn (-90));
		addSequential(new DriveForward(2));
		addSequential(new DriveMandiblesWheelsAuto(-1));
	}
}