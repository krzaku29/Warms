package pl.krzaku.proz.model;

public abstract class Tower
{
	protected double positionX;
	protected double positionY;
	protected double velocityY;
	protected double maxAngle;
	protected double minAngle;
	protected boolean falling;
	protected double shootPower;
	protected double inclinationAngle;
	protected boolean active;
	protected double health;
	protected double gravityAcceleration;
	protected final int towerWidth = 16;
	protected final int towerHeight = 24;

	public abstract void rotate(float angle);

	public abstract void changeShootPower(float amount);
	
	public abstract void update(double deltaTime);

	public boolean isActive()
	{
		return active;
	}

	public void startFalling()
	{
		falling = true;		
	}
	
	public void deactivate()
	{
		active = false;
	}
}
