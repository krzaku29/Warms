package pl.krzaku.proz.model;

import org.newdawn.slick.util.FastTrig;

import pl.krzaku.proz.view.Markable;

public abstract class Tower implements Markable
{
	protected double positionX;
	protected double positionY;
	protected double velocityY;
	protected double maxAngle;
	protected double minAngle;
	protected boolean falling;
	protected double shootPower;
	protected double inclinationAngle;
	protected boolean active;
	protected double health;
	protected double gravityAcceleration;
	protected double minBulletSpeed;
	protected double maxBulletSpeed;
	protected double currentCooldown;
	protected double shootCooldown;
	protected final int towerWidth = 16;
	protected final int towerHeight = 24;
	protected final int markerYOffset = 4;
	protected final double angleChangeSpeed = 80;
	protected final double powerChangeSpeed = 1;
	

	public void rotateLeft(double deltaTime)
	{
		inclinationAngle -= angleChangeSpeed*deltaTime;
		if(inclinationAngle > maxAngle) inclinationAngle = maxAngle;
		if(inclinationAngle < minAngle) inclinationAngle = minAngle;
	}
	public void rotateRight(double deltaTime)
	{
		inclinationAngle += angleChangeSpeed*deltaTime;
		if(inclinationAngle > maxAngle) inclinationAngle = maxAngle;
		if(inclinationAngle < minAngle) inclinationAngle = minAngle;
	}
	
	public void morePower(double deltaTime)
	{
		shootPower += powerChangeSpeed*deltaTime;
		if(shootPower > 1.0) shootPower = 1.0;
		if(shootPower < 0.0) shootPower = 0.0;
	}
	
	public void lessPower(double deltaTime)
	{
		shootPower -= powerChangeSpeed*deltaTime;
		if(shootPower > 1.0) shootPower = 1.0;
		if(shootPower < 0.0) shootPower = 0.0;
	}
	
	public abstract void shoot(GameState gameState);
	
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
	
	public boolean isActive()
	{
		return active;
	}

	public void startFalling()
	{
		falling = true;		
	}
	
	public void deactivate()
	{
		active = false;
	}
}
