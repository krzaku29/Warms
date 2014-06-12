package pl.krzaku.proz.model;

import pl.krzaku.proz.view.Renderable;

/**
 * Bullet class. Every bullet added to game state must inherit from this class
 * @author Patryk Majkrzak
 * @version 1.3
 */
public abstract class Bullet implements Renderable
{
	///X position of the bullet
	protected double positionX;
	///Y position of the bullet
	protected double positionY;
	///Bullet's velocity along X axis
	protected double velocityX;
	///Bullet's velocity along Y axis
	protected double velocityY;
	///Bullet's damage
	protected double damage;
	///Bullet is active. Inactive bullets will be deleted
	protected boolean active;
	///Gravity force for non-laser bullets
	protected final double gravityAcceleration = 100;
	
	/**
	 * Method called for updating bullet's state
	 * @param deltaTime time elapsed from last update
	 */
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
	
	/**
	 * Returns true if bullet is active
	 * @return true if bullet is active
	 */
	public boolean isActive()
	{
		return active;
	}
	
	/**
	 * Returns bullet damage
	 * @return damage of bullet
	 */
	public double getDamage()
	{
		return damage;
	}
	
	/**
	 * Deactivates the bullet
	 */
	public void deactivate()
	{
		this.active = false;
	}
	
	@Override
	public boolean isFlipped()
	{
		return false; //bullet sprite is never flipped (it doesn't really matter as bullet sprite is symmetrical)
	}
}
