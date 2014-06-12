package pl.krzaku.proz.model;

import pl.krzaku.proz.util.Rectangle;

/**
 * Class for calculating object collisions
 * @author Patryk Majkrzak
 * @version 1.1
 */
public class ObjectCollisionManager
{
	/**
	 * Checks for collision of two objects
	 * @param map game map
	 * @param first first object
	 * @param second second object
	 * @param deltaTime time elapsed from last frame
	 * @return true if collision happened
	 */
	public static boolean checkObjectCollision(GameMap map, ObjectCollidable first, ObjectCollidable second, double deltaTime)
	{
		Rectangle firstCollisionRectangle = first.getCollisionRectangle();
		Rectangle secondCollisionRectangle = second.getCollisionRectangle();
		
		if(firstCollisionRectangle.intersects(secondCollisionRectangle)) 
		{
			if(first instanceof Bullet && first instanceof MapCollidable) map.destroyTerrain(((MapCollidable)first).getMapCollisionXPoint(),((MapCollidable)first).getMapCollisionYPoint(), ((MapCollidable)first).getMapExplosionRadius());
			if(second instanceof Bullet && second instanceof MapCollidable) map.destroyTerrain(((MapCollidable)second).getMapCollisionXPoint(),((MapCollidable)second).getMapCollisionYPoint(), ((MapCollidable)second).getMapExplosionRadius());
			first.actOnObjectCollision(second, deltaTime);
			second.actOnObjectCollision(first, deltaTime);

			return true;
		}
		
		return false;
	}
}
