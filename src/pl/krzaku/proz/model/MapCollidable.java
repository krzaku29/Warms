package pl.krzaku.proz.model;

public interface MapCollidable
{
	public int getMapExplosionRadius();
	public int getMapCollisionXPoint();
	public int getMapCollisionYPoint();
	public void actOnMapCollision(double deltaTime);
}
