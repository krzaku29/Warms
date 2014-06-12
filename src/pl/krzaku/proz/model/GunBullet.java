package pl.krzaku.proz.model;

import pl.krzaku.proz.util.Rectangle;
import pl.krzaku.proz.view.Renderable;
import pl.krzaku.proz.view.SpriteID;

/**
 * Class for bullet shot from gun tower
 * @author Patryk Majkrzak
 * @version 1.1
 */
public class GunBullet extends Bullet implements MapCollidable, ObjectCollidable, Renderable
{
	///Bullet sprite width
	private final int bulletWidth = 3;
	///Bullet sprite height
	private final int bulletHeight = 3;
	///Bullet explosion radius
	private final int explosionRadius = 0;

	/**
	 * Constructor. Sets basic parameters of bullet
	 * @param posX X position of bullet
	 * @param posY y position of bullet
	 * @param velX velocity of bullet along X axis
	 * @param velY velocity of bullet along Y axis
	 */
	public GunBullet(double posX, double posY, double velX, double velY)
	{
		positionX = posX;
		positionY = posY;
		velocityX = velX;
		velocityY = velY;
		damage = 5;
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
	public int getMapCollisionXPoint()
	{
		return (int)positionX+1;
	}

	@Override
	public int getMapCollisionYPoint()
	{
		return (int)positionY+1;
	}

	@Override
	public void actOnMapCollision(double deltaTime)
	{
		deactivate();		
	}

	@Override
	public SpriteID getSpriteID()
	{
		return SpriteID.BULLET_GUN;
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

	@Override
	public void actOnObjectCollision(Object collidedObject, double deltaTime)
	{
		deactivate();		
	}
	

}
