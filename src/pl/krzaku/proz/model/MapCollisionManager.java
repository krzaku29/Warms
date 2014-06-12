package pl.krzaku.proz.model;

import pl.krzaku.proz.util.Rectangle;
import pl.krzaku.proz.view.SoundID;

/**
 * Class for calculating object collisions with a map
 * @author Patryk Majkrzak
 * @version 1.2
 */
public final class MapCollisionManager
{
	/**
	 * Checks collision with terrain
	 * @param gameState game state
	 * @param map game map
	 * @param object object to check for collision
	 * @param deltaTime time elapsed from the last frame
	 * @return true if collision happened
	 */
	public static boolean checkCollision(GameState gameState, GameMap map, MapCollidable object, double deltaTime)
	{
		if(object instanceof Tower)
		{
			if(map.isTerrain(object.getMapCollisionXPoint(), object.getMapCollisionYPoint())) 
			{
				object.actOnMapCollision(deltaTime);
				return true;
			}
			((Tower)object).startFalling();
			return false;
		}
		else if(map.isTerrain(object.getMapCollisionXPoint(), object.getMapCollisionYPoint())) 
		{
			object.actOnMapCollision(deltaTime);
			if(!(object instanceof GunBullet) && !(object instanceof LaserBullet)) gameState.soundPlayed(SoundID.EXPLOSION);
			map.destroyTerrain(object.getMapCollisionXPoint(), object.getMapCollisionYPoint(), object.getMapExplosionRadius());
			return true;
		}
		return false;	
	}
	
	/**
	 * Checks for collision wit map border
	 * @param map game map
	 * @param object object to check for collision
	 * @param deltaTime time elapsed from the last frame
	 * @return true if map border collision happened
	 */
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
