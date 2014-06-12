package pl.krzaku.proz.launcher;

/**
 * Screen resolution class
 * @author Patryk Majkrzak
 * @version 1.0
 */
public class Resolution
{
	///Screen width
	private int width;
	///Screen height
	private int height;
	
	/**
	 * Constructor
	 * @param width screen width
	 * @param height screen height
	 */
	public Resolution(int width, int height)
	{
		this.width = width;
		this.height = height;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}
	
	/**
	 * Returns resolution string in format (X x Y)
	 */
	public String toString()
	{
		return width + "x" + height;
	}
}
