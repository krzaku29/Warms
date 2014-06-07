package pl.krzaku.proz.model;

import pl.krzaku.proz.util.Rectangle;

public interface ObjectCollidable
{
	public Rectangle getCollisionRectangle();
	public void actOnObjectCollision(double deltaTime);
}
