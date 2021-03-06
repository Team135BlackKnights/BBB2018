package org.usfirst.frc.team135.robot.commands.auto.groups;

import org.usfirst.frc.team135.robot.RobotMap;
import org.usfirst.frc.team135.robot.RobotMap.AUTONOMOUS;
import org.usfirst.frc.team135.robot.commands.auto.singles.DriveForward;
import org.usfirst.frc.team135.robot.commands.tele.DriveMandibleWheels;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SideToLine extends CommandGroup
{
	public SideToLine(boolean isForward)
	{
		System.out.println("SideToLine");
		addSequential(new DriveForward(AUTONOMOUS.FIELD.AUTO_LINE));
		//addSequential(new DriveForward(AUTONOMOUS.FIELD.AUTO_LINE));
	}
}
