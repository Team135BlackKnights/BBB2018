package org.usfirst.frc.team135.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PDP extends Subsystem {

	private static PDP instance;
	
	private PowerDistributionPanel pdp;
	
	private PDP()
	{
		pdp = new PowerDistributionPanel(0);
	}
	public static PDP getInstance()
	{
		if (instance == null)
		{
			instance = new PDP();
		}
		return instance;
	}
	public double getChannelCurrent(int channel)
	{
		return pdp.getCurrent(channel);
	}
	@Override
	public void periodic()
	{
		//System.out.println(getChannelCurrent(4));
	}
	@Override
	protected void initDefaultCommand()
	{
	}
}

