package pl.krzaku.proz.model;

import pl.krzaku.proz.util.Rectangle;

/**
 * Interface for objects that are able to collide with map border
 * @author Patryk Majkrzak
 * @version 1.0
 */
public interface MapBorderCollidable
{
	///Get rectangle for calculating collision with map border
	public Rectangle getCollisionRectangle();
	///Called when collision with map border occurs
	public void actOnMapBorderCollision(MapBorder border, double deltaTime);
}
