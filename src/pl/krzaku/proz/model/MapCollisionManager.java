package pl.krzaku.proz.model;

import pl.krzaku.proz.util.Rectangle;

public final class MapCollisionManager
{
	public static boolean checkCollision(GameMap map, MapCollidable object, double deltaTime)
	{
		if(map.isTerrain(object.getMapCollisionXPoint(), object.getMapCollisionYPoint())) 
		{
			object.actOnMapCollision(deltaTime);
			map.destroyTerrain(object.getMapCollisionXPoint(), object.getMapCollisionYPoint(), object.getMapExplosionRadius());
			return true;
		}
		return false;	
	}
	
	public static boolean checkBorderCollision(GameMap map, MapBorderCollidable object, double deltaTime)
	{
		Rectangle mapBorder = new Rectangle(0,0,GameMap.getMapWidth(),GameMap.getMapHeight());
		Rectangle objectCollisionRectangle = object.getCollisionRectangle();
		if(!mapBorder.contains(objectCollisionRectangle)) 
		{
			if(objectCollisionRectangle.getUpperBound() <= mapBorder.getUpperBound()) object.actOnMapBorderCollision(MapBorder.UP, deltaTime);
			if(objectCollisionRectangle.getLowerBound() >= mapBorder.getLowerBound()) object.actOnMapBorderCollision(MapBorder.DOWN, deltaTime);
			if(objectCollisionRectangle.getLeftBound() <= mapBorder.getLeftBound()) object.actOnMapBorderCollision(MapBorder.LEFT, deltaTime);
			if(objectCollisionRectangle.getRightBound() >= mapBorder.getRightBound()) object.actOnMapBorderCollision(MapBorder.RIGHT, deltaTime);

			return true;
		}
		return false;	
	}
}
