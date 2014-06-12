package pl.krzaku.proz.model;

/**
 * Interface for objects that are able to collide with terrain
 * @author Patryk Majkrzak
 * @version 1.0
 */
public interface MapCollidable
{
	///Map explosion radius, if object destroys part of the map 
	public int getMapExplosionRadius();
	///Get X point to be checked for collision with game map
	public int getMapCollisionXPoint();
	///Get X point to be checked for collision with game map
	public int getMapCollisionYPoint();
	///Called when collision with terrain occurs
	public void actOnMapCollision(double deltaTime);
}
