package pl.krzaku.proz.model;

import pl.krzaku.proz.util.Rectangle;

/**
 * Interface for objects that are able to collide with each other
 * @author Patryk Majkrzak
 * @version 1.0
 */
public interface ObjectCollidable
{
	///Get rectangle for calculating collision with map border
	public Rectangle getCollisionRectangle();
	///Called when collision with other object happened
	public void actOnObjectCollision(Object collidedObject, double deltaTime);
}
