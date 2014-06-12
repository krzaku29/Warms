package pl.krzaku.proz.view;

public class Crosshair implements Renderable
{
	private double positionX;
	private double positionY;
	private final SpriteID spriteID = SpriteID.CROSSHAIR;
	
	public Crosshair(double posX, double posY)
	{
		positionX = posX;
		positionY = posY;
	}
	
	@Override
	public SpriteID getSpriteID()
	{
		return this.spriteID;
	}
	@Override
	public int getSpriteNumber()
	{
		return 0;
	}
	@Override
	public float getRenderPositionX()
	{
		return (float)positionX-1;
	}
	@Override
	public float getRenderPositionY()
	{
		return (float)positionY-1;
	}
	@Override
	public boolean isFlipped()
	{
		return false;
	}
	
	
}
