package org.usfirst.frc.team135.robot.commands.auto.singles;

import org.usfirst.frc.team135.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SetArmPosition extends InstantCommand {

	private double _position;
    public SetArmPosition(double position) {
        super();
        requires(Robot.arm);
        
        this._position = position;
    }

    // Called once when the command executes
    protected void initialize() 
    {
    	Robot.arm.setToPosition(this._position);
    	Robot.arm.mantainPosition();
    }

}
