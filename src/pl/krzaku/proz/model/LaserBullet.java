package pl.krzaku.proz.model;

import pl.krzaku.proz.util.Rectangle;

public class LaserBullet extends Bullet implements MapCollidable, MapBorderCollidable, ObjectCollidable
{
	private final int bulletWidth = 3;
	private final int bulletHeight = 3;
	
	public LaserBullet()
	{
		
	}
	
	public LaserBullet(double posX, double posY, double velX, double velY)
	{
		positionX = posX;
		positionY = posY;
		velocityX = velX;
		velocityY = velY;
	}
	
	@Override
	public void update(double deltaTime)
	{
		positionX += velocityX * deltaTime;
		positionY += velocityY * deltaTime;
	}

	@Override
	public Rectangle getCollisionRectangle()
	{
		return new Rectangle(positionX, positionY, bulletWidth, bulletHeight);
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
	public int getMapExplosionRadius()
	{
		return 3;
	}

}
