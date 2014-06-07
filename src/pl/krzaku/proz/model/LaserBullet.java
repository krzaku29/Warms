package pl.krzaku.proz.model;

import pl.krzaku.proz.util.Rectangle;
import pl.krzaku.proz.view.Renderable;
import pl.krzaku.proz.view.SpriteID;

public class LaserBullet extends Bullet implements MapCollidable, MapBorderCollidable, ObjectCollidable, Renderable
{
	private final int bulletWidth = 3;
	private final int bulletHeight = 3;
	private final int explosionRadius = 5;
	
	public LaserBullet()
	{

	}
	
	public LaserBullet(double posX, double posY, double velX, double velY)
	{
		positionX = posX;
		positionY = posY;
		velocityX = velX;
		velocityY = velY;
		damage = 10;
		active = true;
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
		return explosionRadius;
	}

	@Override
	public void actOnObjectCollision(Object collidedObject, double deltaTime)
	{
		deactivate();		
	}

	@Override
	public void actOnMapBorderCollision(MapBorder border, double deltaTime)
	{
		positionX -= velocityX*deltaTime;
		positionY -= velocityY*deltaTime;
		if(border == MapBorder.UP || border == MapBorder.DOWN) velocityY = -velocityY;
		if(border == MapBorder.LEFT || border == MapBorder.RIGHT) velocityX = -velocityX;
	}

	@Override
	public void actOnMapCollision(double deltaTime)
	{
		deactivate();
	}

	@Override
	public SpriteID getSpriteID()
	{
		return SpriteID.BULLET_LASER;
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
