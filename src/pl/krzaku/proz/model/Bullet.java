package pl.krzaku.proz.model;

import pl.krzaku.proz.view.Renderable;

public abstract class Bullet implements Renderable
{
	protected double positionX;
	protected double positionY;
	protected double velocityX;
	protected double velocityY;
	protected boolean active;
	
	public abstract void update(double deltaTime);
	
	public double getPositionX()
	{
		return positionX;
	}

	public void setPositionX(double positionX)
	{
		this.positionX = positionX;
	}

	public double getPositionY()
	{
		return positionY;
	}

	public void setPositionY(double positionY)
	{
		this.positionY = positionY;
	}

	public double getVelocityX()
	{
		return velocityX;
	}

	public void setVelocityX(double velocityX)
	{
		this.velocityX = velocityX;
	}

	public double getVelocityY()
	{
		return velocityY;
	}

	public void setVelocityY(double velocityY)
	{
		this.velocityY = velocityY;
	}
	
	public boolean isActive()
	{
		return active;
	}
	
	public void deactivate()
	{
		this.active = false;
	}
}
