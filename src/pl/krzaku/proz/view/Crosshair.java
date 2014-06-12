package pl.krzaku.proz.view;

/**
 * Towers crosshair class
 * @author Patryk Majkrzak
 * @version 1.0
 */
public class Crosshair implements Renderable
{
	///X position of crosshair
	private double positionX;
	///Y position of crosshair
	private double positionY;
	///Crosshair sprite
	private final SpriteID spriteID = SpriteID.CROSSHAIR;
	
	/**
	 * Constructor.
	 * @param posX X position of crosshair
	 * @param posY Y position of crosshair
	 */
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
