package pl.krzaku.proz.model;

import pl.krzaku.proz.util.Rectangle;
import pl.krzaku.proz.view.Renderable;
import pl.krzaku.proz.view.SpriteID;

public class NormalBullet extends Bullet implements MapCollidable, ObjectCollidable, Renderable
{
	private final int bulletWidth = 3;
	private final int bulletHeight = 3;
	private final int explosionRadius = 15;
	public NormalBullet()
	{

	}
	
	public NormalBullet(double posX, double posY, double velX, double velY)
	{
		positionX = posX;
		positionY = posY;
		velocityX = velX;
		velocityY = velY;
		damage = 20;
		active = true;
	}
	
	@Override
	public Rectangle getCollisionRectangle()
	{
		return new Rectangle(positionX, positionY, bulletWidth, bulletHeight);
	}

	@Override
	public int getMapExplosionRadius()
	{
		return explosionRadius;
	}

	@Override
	public void update(double deltaTime)
	{
		positionX += velocityX * deltaTime;
		positionY += velocityY * deltaTime;
		velocityY += gravityAcceleration * deltaTime;		
	}

	@Override
	public void actOnObjectCollision(Object collidedObject, double deltaTime)
	{
		deactivate();
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
		deactivate();		
	}

	@Override
	public SpriteID getSpriteID()
	{
		return SpriteID.BULLET_NORMAL;
	}

	@Override
	public int getSpriteNumber()
	{
		return 0;
	}

	@Override
	public float getRenderPositionX()
	{
		return (float)positionX;
	}

	@Override
	public float getRenderPositionY()
	{
		return (float)positionY;
	}

}
