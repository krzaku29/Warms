package pl.krzaku.proz.model;

import org.newdawn.slick.util.FastTrig;

import pl.krzaku.proz.util.Rectangle;
import pl.krzaku.proz.view.Renderable;
import pl.krzaku.proz.view.SoundID;
import pl.krzaku.proz.view.SpriteID;

/**
 * Class for repulsor tower
 * @author Patryk Majkrzak
 * @version 1.1
 */
public class RepulsorTower extends Tower implements Renderable, MapCollidable, ObjectCollidable
{	
	/**
	 * Constructor. Sets basic parameters
	 * @param positionX X position of tower
	 * @param positionY Y position of tower
	 * @param flipped true if tower should start being turned to the right
	 */
	public RepulsorTower(double positionX, double positionY, boolean flipped)
	{
		this.positionX = positionX;
		this.positionY = positionY;
		this.minAngle = 0;
		this.maxAngle = 180;
		this.gravityAcceleration = 40;
		this.shootPower = 0.5;
		this.health = 100;
		if(flipped)	this.inclinationAngle = maxAngle;
		else this.inclinationAngle = minAngle;
		this.active = true;
		this.falling = false;
		
		minBulletSpeed = 20;
		maxBulletSpeed = 70;
		currentCooldown = 0;
		shootCooldown = 1;
	}
	
	@Override
	public void shoot(GameState gameState)
	{
		if(currentCooldown == 0)
		{
			double bulletSpeedX = -(minBulletSpeed + shootPower *(maxBulletSpeed - minBulletSpeed)) * FastTrig.cos(Math.toRadians(inclinationAngle));
			double bulletSpeedY = -(minBulletSpeed + shootPower *(maxBulletSpeed - minBulletSpeed)) * FastTrig.sin(Math.toRadians(inclinationAngle));
			
			gameState.repulse(bulletSpeedX, bulletSpeedY);
			gameState.soundPlayed(SoundID.REPULSE);
			
			currentCooldown = shootCooldown;
		}
	}

	@Override
	public SpriteID getSpriteID()
	{
		return SpriteID.TOWER_REPULSOR;
	}

	@Override
	public int getSpriteNumber()
	{
		int angle = (int)this.inclinationAngle-90;
		angle = Math.abs(angle);
		if(angle < 30) return 2;
		if(angle < 60) return 1;
		return 0;
	}

	@Override
	public float getRenderPositionX()
	{
		return (float)positionX - towerWidth/2;
	}

	@Override
	public float getRenderPositionY()
	{
		return (float)positionY - towerHeight;
	}

	@Override
	public int getMapExplosionRadius()
	{
		return 0;
	}

	@Override
	public int getMapCollisionXPoint()
	{
		return (int)positionX;
	}

	@Override
	public int getMapCollisionYPoint()
	{
		return (int)positionY;
	}

	@Override
	public void actOnMapCollision(double deltaTime)
	{
		falling = false;
		if(velocityY > 50) health -= velocityY/5;
		velocityY=0;
	}
	
	@Override
	public boolean isFlipped()
	{
		if(inclinationAngle > 90) return true;
		return false;
	}

	@Override
	public Rectangle getCollisionRectangle()
	{
		return new Rectangle(positionX-towerWidth/2, positionY-towerHeight, towerWidth, towerHeight);
	}

	@Override
	public void actOnObjectCollision(Object collidedObject, double deltaTime)
	{
		if(collidedObject instanceof Bullet) health -= ((Bullet)collidedObject).getDamage();
	}

}
