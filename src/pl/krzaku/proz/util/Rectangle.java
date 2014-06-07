package pl.krzaku.proz.util;

/**
 * Rectangle class
 * 
 * @author Patryk Majkrzak
 *
 */
public class Rectangle
{
	///Position of left upper corner of rectangle along X axis
	private double positionX;
	///Position of left upper corner of rectangle along Y axis
	private double positionY;
	///Width of rectangle
	private double rectangleWidth;
	///Height of rectangle
	private double rectangleHeight;
	
	/**
	 * Basic rectangle constructor. Makes a rectangle with 0 width and height at point (0,0)
	 */
	public Rectangle()
	{
		positionX = 0;
		positionY = 0;
		rectangleHeight = 0;
		rectangleWidth = 0;
	}
	
	/**
	 * Rectangle constructor
	 * @param posX position of left upper corner along X axis
	 * @param posY position of left upper corner along Y axis
	 * @param width width of rectangle
	 * @param height height of rectangle
	 */
	public Rectangle(double posX, double posY, double width, double height)
	{
		positionX = posX;
		positionY = posY;
		rectangleWidth = width;
		rectangleHeight = height;
	}
	
	/**
	 * Returns true if there is an intersection between two given rectangles
	 * @param otherRectangle other rectangle to be checked for intersection 
	 * @return true if rectangles intersect each other
	 */
	public boolean intersects(Rectangle otherRectangle)
	{
		double smallerX, smallerY, biggerX, biggerY;
		
		if(this.positionX + this.rectangleWidth < otherRectangle.positionX + otherRectangle.rectangleWidth)
		{
			biggerX = this.positionX + this.rectangleWidth;
		}
		else
		{
			biggerX = otherRectangle.positionX + otherRectangle.rectangleWidth;
		}
		
		if(this.positionY + this.rectangleHeight < otherRectangle.positionY + otherRectangle.rectangleHeight)
		{
			biggerY = this.positionY + this.rectangleHeight;
		}
		else
		{
			biggerY = otherRectangle.positionY + otherRectangle.rectangleHeight;
		}
		
		if(this.positionX > otherRectangle.positionX)
		{
			smallerX = this.positionX;
		}
		else
		{
			smallerX = otherRectangle.positionX;
		}
		
		if(this.positionY > otherRectangle.positionY)
		{
			smallerY = this.positionY;
		}
		else
		{
			smallerY = otherRectangle.positionY;
		}
		
		
		if(smallerX <= biggerX && smallerY <= biggerY) return true;		
		
		return false;
	}
	
	/**
	 * Returns true if checked rectangle is entirely inside the rectangle this method is called from 
	 * @param otherRectangle other rectangle to be checked if it's inside
	 * @return true if other rectangle is inside this rectangle
	 */
	public boolean contains(Rectangle otherRectangle)
	{
		if(otherRectangle.positionX > this.positionX && otherRectangle.positionY > this.positionY)
		{
			if(otherRectangle.positionX + otherRectangle.rectangleWidth < this.positionX + this.rectangleWidth &&
					otherRectangle.positionY + otherRectangle.rectangleHeight < this.positionY + this.rectangleHeight) return true;
		}
		
		return false;
	}
	
	/**
	 * Returns true if given point is inside this rectangle
	 * @param pointPositionX position of point along X axis
	 * @param pointPositionY position of point along Y axis
	 * @return true if point is inside this rectangle
	 */
	public boolean contains(double pointPositionX, double pointPositionY)
	{
		if(pointPositionX > this.positionX && pointPositionX < this.positionX+this.rectangleWidth && 
				pointPositionY > this.positionY && pointPositionY < this.positionY+this.rectangleHeight) return true;
		return false;
	}
	
	
	public double getPositionX()
	{
		return positionX;
	}

	public void setPositionX(double positionX)
	{
		this.positionX = positionX;
	}

	public double getPositionY()
	{
		return positionY;
	}

	public void setPositionY(double positionY)
	{
		this.positionY = positionY;
	}

	public double getRectangleWidth()
	{
		return rectangleWidth;
	}

	public void setRectangleWidth(double rectangleWidth)
	{
		this.rectangleWidth = rectangleWidth;
	}

	public double getRectangleHeight()
	{
		return rectangleHeight;
	}

	public void setRectangleHeight(double rectangleHeight)
	{
		this.rectangleHeight = rectangleHeight;
	}
	
	public double getUpperBound()
	{
		return positionY;
	}
	
	public double getLowerBound()
	{
		return positionY+rectangleHeight;
	}
	public double getLeftBound()
	{
		return positionX;
	}
	public double getRightBound()
	{
		return positionX+rectangleWidth;
	}
	

}
