package pl.krzaku.proz.model;

import pl.krzaku.proz.util.Rectangle;

public interface MapBorderCollidable
{
	public Rectangle getCollisionRectangle();
	public void actOnMapBorderCollision(MapBorder border, double deltaTime);
}
