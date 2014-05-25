package pl.krzaku.proz.model;

public class ObjectCollisionManager
{
	public static boolean checkObjectCollision(ObjectCollidable first, ObjectCollidable second)
	{
		if(first.getCollisionRectangle().intersects(second.getCollisionRectangle())) return true;
		return false;
	}
}
