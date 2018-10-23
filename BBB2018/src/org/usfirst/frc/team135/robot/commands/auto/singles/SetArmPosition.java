package org.usfirst.frc.team135.robot.commands.auto.singles;

import org.usfirst.frc.team135.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SetArmPosition extends InstantCommand {
	public SetArmPosition()
	{
		Robot.arm.autonArm();
	}


}
