/**
 * @author 		Patryk Majkrzak
 * @version	1.0
 */
package pl.krzaku.proz;

import org.newdawn.slick.*;

/**
 * Main game class. Starting point for everything else.
 * @author		Patryk Majkrzak
 */
public class Game extends BasicGame
{
	private static AppGameContainer appGameContainer;
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			appGameContainer = new AppGameContainer(new Game("Warms"));
		} 
		catch (SlickException e)
		{
			e.printStackTrace();
		}

	}
	
	public Game(String gamename)
	{
		super(gamename);
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException
	{
		// TODO Auto-generated method stub
		
	}

}
