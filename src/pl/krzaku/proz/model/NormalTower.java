package pl.krzaku.proz.model;

import pl.krzaku.proz.util.Rectangle;
import pl.krzaku.proz.view.Renderable;
import pl.krzaku.proz.view.SpriteID;

public class NormalTower extends Tower implements MapCollidable, ObjectCollidable, Renderable
{
	public NormalTower()
	{
		
	}
	
	public NormalTower(double positionX, double positionY, boolean flipped)
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
	}

	@Override
	public void rotate(float angle)
	{
		inclinationAngle += angle;
		if(inclinationAngle > maxAngle) inclinationAngle = maxAngle;
		if(inclinationAngle < minAngle) inclinationAngle = minAngle;
	}

	@Override
	public void changeShootPower(float amount)
	{
		shootPower += amount;
		if(shootPower > 1.0) shootPower = 1.0;
		if(shootPower < 0.0) shootPower = 0.0;		
	}

	@Override
	public SpriteID getSpriteID()
	{
		return SpriteID.TOWER_NORMAL;
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
	public void update(double deltaTime)
	{
		if(falling)
		{
			positionY += velocityY*deltaTime;
			velocityY += gravityAcceleration*deltaTime;
		}
		if(health < 0) deactivate();
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
