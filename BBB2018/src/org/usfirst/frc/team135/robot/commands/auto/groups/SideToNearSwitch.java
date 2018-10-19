package org.usfirst.frc.team135.robot.commands.auto.groups;

import org.usfirst.frc.team135.robot.RobotMap;
import org.usfirst.frc.team135.robot.RobotMap.AUTONOMOUS.FIELD;
import org.usfirst.frc.team135.robot.commands.auto.singles.DriveForward;
import org.usfirst.frc.team135.robot.commands.auto.singles.SetArmPosition;
import org.usfirst.frc.team135.robot.commands.auto.singles.Turn;
import org.usfirst.frc.team135.robot.commands.tele.ReleaseMandibles;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SideToNearSwitch extends CommandGroup implements RobotMap
{
	public SideToNearSwitch(boolean isLeft)
	{
		System.out.println("SideToNearSwitch");
		addParallel(new SetArmPosition(1.0));
		if (isLeft)
		{
			addSequential(new Turn(-90));
		}
		else
		{
			addSequential(new Turn(90));
		}
		addSequential(new DriveForward(FIELD.SCALE_X / 2 + FIELD.LEFT_NEAR_SWITCH_X));
		if (isLeft)
		{
			addSequential(new Turn(90));
		}
		else
		{
			addSequential(new Turn(-90));
		}
		addSequential(new SetArmPosition(0));
		addSequential(new ReleaseMandibles());
	}
}