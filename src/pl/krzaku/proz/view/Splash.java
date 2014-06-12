package pl.krzaku.proz.view;

/**
 * Class for splash screens displayed on game window
 * @author Patryk Majkrzak
 * @version 1.0
 */
public class Splash implements Renderable
{
	///Splash window X position
	private final int splashXPosition = 220;
	///Splash window Y position
	private final int splashYPosition = 172;
	///Type of splash window
	private int splashType;
	
	/** 
	 * Constructor.
	 * @param type splash window type
	 */
	public Splash(int type)
	{
		splashType = type;
	}

	@Override
	public SpriteID getSpriteID()
	{
		if(splashType == 1) return SpriteID.PLAYER_1_LOST;
		else return SpriteID.PLAYER_2_LOST;
	}

	@Override
	public int getSpriteNumber()
	{
		return 0;
	}

	@Override
	public float getRenderPositionX()
	{
		return splashXPosition;
	}

	@Override
	public float getRenderPositionY()
	{
		return splashYPosition;
	}

	@Override
	public boolean isFlipped()
	{
		return false;
	}
	

}
