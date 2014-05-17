package pl.krzaku.proz.model;

public abstract class Tower
{
	protected float positionX;
	protected float positionY;
	protected float shootPower;
	protected float inclinationAngle;
	
	public abstract void shoot();
	public abstract void rotate(float angle);
	public abstract void changeShootPower(float amount);	
}
