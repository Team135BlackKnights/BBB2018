package org.usfirst.frc.team135.robot.commands.camera;

import org.usfirst.frc.team135.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

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
