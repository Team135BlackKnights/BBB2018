package org.usfirst.frc.team135.robot.utilities;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class PIDin implements PIDSource
{

	private FunctionalDoubleManager _source;
	private PIDSourceType _pidSourceType;
	public PIDin(FunctionalDoubleManager source, PIDSourceType pidSource)
	{
		this._source = source;
		this._pidSourceType = pidSource;
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSourceType) 
	{	
		this._pidSourceType = pidSourceType;
	}

	@Override
	public PIDSourceType getPIDSourceType() 
	{
		return this._pidSourceType;
	}

	@Override
	public double pidGet() 
	{
		return this._source.get();
	}

}
