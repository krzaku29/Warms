package pl.krzaku.proz.model;

import org.newdawn.slick.util.FastTrig;

import pl.krzaku.proz.view.Markable;

/**
 * Tower class. Every tower added to game state must inherit from this class
 * @author Patryk Majkrzak
 * @version 1.3
 */
public abstract class Tower implements Markable
{
	///X position of tower
	protected double positionX;
	///Y position of tower
	protected double positionY;
	///Velocity along Y axis (when falling)
	protected double velocityY;
	///Minimum angle that tower is able to rotate to
	protected double maxAngle;
	///Maximum angle that tower is able to rotate to
	protected double minAngle;
	///True if tower is currently falling
	protected boolean falling;
	///Shoot power (from 0.0 to 1.0)
	protected double shootPower;
	///Current inclination angle of the tower
	protected double inclinationAngle;
	///Determines if tower is active (inactive towers are deleted)
	protected boolean active;
	///Tower's health
	protected double health;
	///Gravity force for falling towers
	protected double gravityAcceleration;
	///Minimal bullet speed that tower is able to shoot
	protected double minBulletSpeed;
	///Maximal bullet speed that tower is able to shoot
	protected double maxBulletSpeed;
	///Current shooting cooldown
	protected double currentCooldown;
	///Shooting cooldown that is set after a shot
	protected double shootCooldown;
	///Width of a tower sprite
	protected final int towerWidth = 16;
	///Height of a current sprite
	protected final int towerHeight = 24;
	///How many pixels above the tower should the marker be
	protected final int markerYOffset = 4;
	///Speed of angle change (in degrees per second)
	protected final double angleChangeSpeed = 80;
	///Speed of power change speed (in units per second)
	protected final double powerChangeSpeed = 1;
	
	/**
	 * Rotates the tower counter-clockwise with tower specific rotate speed 
	 * @param deltaTime time elapsed from the last frame
	 */
	public void rotateLeft(double deltaTime)
	{
		inclinationAngle -= angleChangeSpeed*deltaTime;
		if(inclinationAngle > maxAngle) inclinationAngle = maxAngle;
		if(inclinationAngle < minAngle) inclinationAngle = minAngle;
	}
	
	/**
	 * Rotates the tower clockwise with tower specific rotate speed 
	 * @param deltaTime time elapsed from the last frame
	 */
	public void rotateRight(double deltaTime)
	{
		inclinationAngle += angleChangeSpeed*deltaTime;
		if(inclinationAngle > maxAngle) inclinationAngle = maxAngle;
		if(inclinationAngle < minAngle) inclinationAngle = minAngle;
	}
	
	/**
	 * Raises the tower's shoot power with tower specific speed
	 * @param deltaTime time elapsed from the last frame
	 */
	public void morePower(double deltaTime)
	{
		shootPower += powerChangeSpeed*deltaTime;
		if(shootPower > 1.0) shootPower = 1.0;
		if(shootPower < 0.0) shootPower = 0.0;
	}
	
	/**
	 * Lowers the tower's shoot power with tower specific speed
	 * @param deltaTime time elapsed from the last frame
	 */
	public void lessPower(double deltaTime)
	{
		shootPower -= powerChangeSpeed*deltaTime;
		if(shootPower > 1.0) shootPower = 1.0;
		if(shootPower < 0.0) shootPower = 0.0;
	}
	
	/**
	 * Called when tower shoots
	 * @param gameState game state to spawn bullet on
	 */
	public abstract void shoot(GameState gameState);
	
	/**
	 * Updates tower state
	 * @param deltaTime time elapsed from the last frame
	 */
	public void update(double deltaTime)
	{
		if(falling)
		{
			positionY += velocityY*deltaTime;
			velocityY += gravityAcceleration*deltaTime;
		}
		
		if(health < 0) deactivate();
		
		if(currentCooldown != 0) 
		{
			currentCooldown -= deltaTime;
			if(currentCooldown < 0) currentCooldown = 0;
		}
	}
	
	@Override
	public double getCenterXPosition()
	{
		return this.positionX;
	}
	
	@Override
	public double getCenterYPosition()
	{
		return this.positionY-towerHeight/2;
	}
	
	@Override
	public double getMarkerYOffset()
	{
		return (this.towerHeight/2 + markerYOffset);
	}
	
	@Override
	public double getCrosshairXOffset()
	{
		return -((shootPower*40) + 10) * FastTrig.cos(Math.toRadians(inclinationAngle));
	}
	
	@Override
	public double getCrosshairYOffset()
	{
		return -((shootPower*40) + 10) * FastTrig.sin(Math.toRadians(inclinationAngle));
	}
	
	/**
	 * True if tower is active
	 * @return true if tower is active
	 */
	public boolean isActive()
	{
		return active;
	}

	/**
	 * Method invoked to start tower's fall
	 */
	public void startFalling()
	{
		falling = true;		
	}
	
	/**
	 * Method for deactivating a tower	
	 */
	public void deactivate()
	{
		active = false;
	}
	
	/**
	 * Returns tower's health
	 */
	public int getHealth()
	{
		return (int)health;
	}
}
