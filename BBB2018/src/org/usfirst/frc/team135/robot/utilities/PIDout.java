package org.usfirst.frc.team135.robot.utilities;

import edu.wpi.first.wpilibj.PIDOutput;

public class PIDout implements PIDOutput
{

	public double output = 0.0;
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		this.output = output;
	}

}