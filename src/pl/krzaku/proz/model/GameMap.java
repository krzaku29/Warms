package pl.krzaku.proz.model;

import org.newdawn.slick.Color;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.Image;

/**Class for game terrain map
 * @author Patryk Majrzak
 * @version 1.1 
 */

public class GameMap
{
	///Map width in pixels
	private static final int mapWidth = 683;
	///Map height in pixels
	private static final int mapHeight = 384;
	///Red component in RGB terrain color
	private static final int redMapColor = 116;
	///Green component in RGB terrain color
	private static final int greenMapColor = 96;
	///Blue component in RGB terrain color
	private static final int blueMapColor = 69;
	
	///Image buffer that map is drawn on
	private ImageBuffer mapImage;
	///Copy of mapImage which is drawn on game screen (better performance)
	private Image imageForDrawing;
	///Flag which tells if we need to refresh imageForDrawing
	private boolean modified = false;
	
	/**
	 * Default constructor
	 */
	public GameMap()
	{
		mapImage = new ImageBuffer(mapWidth+1, mapHeight+1);
	}
	
	/**
	 * Sets terrain height in specified point	
	 * @param x position along X axis of map
	 * @param howmuch how high is terrain at point x
	 */
	public void setHeight(int x, int howmuch)
	{
		for (int i = 0; i < howmuch; i++)
		{
			mapImage.setRGBA(x, mapHeight-i, redMapColor, greenMapColor, blueMapColor, 255);
		}
		modified = true;
	}
	
	
	/**
	 * Makes a circle-shaped hole in map
	 * @param x	position of the circle's center along X axis
	 * @param y	position of the circle's center along X axis
	 * @param r radius of the circle
	 */
	public void destroyTerrain(int x, int y, int r)
	{
		int startX 	= x-r,
			endX 	= x+r,
			startY 	= y-r,
			endY	= y+r;
			
		for(int i = startX; i <= endX; i++)
		{
			for(int j = startY; j <= endY; j++)
			{
				if((i-x)*(i-x)+(j-y)*(j-y) <= r*r) mapImage.setRGBA(i, j, 0, 0, 0, 0);
			}
		}
		
		modified = true;
	}
	
	/**
	 * Tests if position on map is terrain or not. If point is out of bounds it returns false
	 * @param x position of the point along X axis
	 * @param y position of the point along Y axis
	 * @return true if point is on the map and is terrain
	 */
	public boolean isTerrain(int x, int y)
	{
		if(x >= 0 && x <= mapWidth && y>=0 && y <= mapHeight) return imageForDrawing.getColor(x, y).equals(new Color(redMapColor, greenMapColor, blueMapColor, 255));
		return false;
	}
	
	/**
	 * Returns map image for drawing.
	 * @return map image
	 */
	
	public Image getImage()
	{
		if(modified) 
		{
			imageForDrawing = mapImage.getImage();
			//imageForDrawing.setFilter(Image.FILTER_NEAREST);
			modified = false;
		}
		return imageForDrawing;
	}
}
