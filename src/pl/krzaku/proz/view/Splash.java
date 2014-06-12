package pl.krzaku.proz.view;

public class Splash implements Renderable
{
	private final int splashXPosition = 220;
	private final int splashYPosition = 172;
	private int splashType;
	
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
