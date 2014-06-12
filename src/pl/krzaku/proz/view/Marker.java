package pl.krzaku.proz.view;

public class Marker implements Renderable
{
	private double positionX;
	private double positionY;
	private final int markerWidth = 12;
	private final int markerHeight = 8;
	private final SpriteID spriteID = SpriteID.MARKER;

	public Marker(double posX, double posY)
	{
		positionX = posX;
		positionY = posY;
	}
	
	@Override
	public SpriteID getSpriteID()
	{
		return spriteID;
	}

	@Override
	public int getSpriteNumber()
	{
		return 0;
	}

	@Override
	public float getRenderPositionX()
	{
		return (float)this.positionX - markerWidth/2;
	}

	@Override
	public float getRenderPositionY()
	{
		return (float)this.positionY - markerHeight;
	}

	@Override
	public boolean isFlipped()
	{
		return false;
	}
	
}
