package org.usfirst.frc.team135.robot.commands.camera;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team135.robot.Robot;

/**
 *
 */
public class TrackWithVision extends CommandGroup {

    public TrackWithVision() {
    	requires(Robot.drivetrain);
    	//requires(Robot.limelight);
        addSequential(new TurnTowardsObject());
        addSequential(new DriveStraightLimelight());
    }
}
