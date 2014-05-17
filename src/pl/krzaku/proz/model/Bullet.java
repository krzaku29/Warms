package pl.krzaku.proz.model;

public abstract class Bullet
{
	protected float positionX;
	protected float positionY;
	protected float velocityX;
	protected float velocityY;
	
	public abstract void update();
}
