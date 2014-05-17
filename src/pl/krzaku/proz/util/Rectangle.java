package pl.krzaku.proz.util;

public class Rectangle
{
	private float positionX;
	private float positionY;
	private float rectangleWidth;
	private float rectangleHeight;
	
	public Rectangle(float posX, float posY, float width, float height)
	{
		positionX = posX;
		positionY = posY;
		rectangleWidth = width;
		rectangleHeight = height;
	}
	
	public boolean intersects(Rectangle secondRectangle)
	{
		//FIXME
		return false;
	}
	
	public boolean contains(Rectangle secondRectangle)
	{
		//FIXME
		return false;
	}
	
	public boolean contains(float pointPositionX, float pointPositionY)
	{
		//FIXME
		return false;
	}
	
}
