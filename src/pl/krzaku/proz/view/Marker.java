package pl.krzaku.proz.view;

/**
 * Class for object marker
 * @author Patryk Majkrzak
 * @version 1.0
 */
public class Marker implements Renderable
{
	///X position of marker
	private double positionX;
	///Y position of marker
	private double positionY;
	///Height of marker sprite
	private final int markerWidth = 12;
	///Width of marker sprite
	private final int markerHeight = 8;
	///Marker's sprite ID
	private final SpriteID spriteID = SpriteID.MARKER;

	/**
	 * Constructor.
	 * @param posX marker X position
	 * @param posY marker Y position
	 */
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
