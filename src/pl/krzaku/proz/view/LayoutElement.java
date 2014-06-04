package pl.krzaku.proz.view;

public abstract class LayoutElement
{
	private SpriteID id;
	private float positionX;
	private float positionY;
	
	public SpriteID getId()
	{
		return id;
	}

	public float getPositionX()
	{
		return positionX;
	}

	public float getPositionY()
	{
		return positionY;
	}
	
}
