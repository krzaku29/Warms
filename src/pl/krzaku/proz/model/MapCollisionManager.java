package pl.krzaku.proz.model;

public final class MapCollisionManager
{
	public static boolean checkCollision(GameMap map, MapCollidable object)
	{
		if(map.isTerrain(object.getMapCollisionXPoint(), object.getMapCollisionYPoint())) return true;
		return false;	
	}
}
