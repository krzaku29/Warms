package pl.krzaku.proz.model;

import pl.krzaku.proz.util.Rectangle;

public class ObjectCollisionManager
{
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
