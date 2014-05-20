/**
 * @author 		Patryk Majkrzak
 * @version	1.0
 */
package pl.krzaku.proz;

import org.newdawn.slick.*;

import pl.krzaku.proz.model.GameMap;
import pl.krzaku.proz.util.MapGenerator;

/**
 * Main game class. Starting point for everything else.
 * @author		Patryk Majkrzak
 */
public class Game extends BasicGame
{
	private static AppGameContainer appGameContainer;
	private static GameMap map;
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			appGameContainer = new AppGameContainer(new Game("Warms"));
			appGameContainer.setFullscreen(true);
			appGameContainer.setDisplayMode(1366, 768, true);
			appGameContainer.setMinimumLogicUpdateInterval(17);
			appGameContainer.setTargetFrameRate(60);
			appGameContainer.start();
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
	public void render(GameContainer gameContainer, Graphics graphics) throws SlickException
	{
		graphics.scale(2, 2);
		graphics.drawImage(map.getImage(), 0, 0);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException
	{
		// TODO Auto-generated method stub
		map = MapGenerator.getMap(100);
		
	}
	private static String x = "";
	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException
	{
		// TODO Auto-generated method stub
		x = String.valueOf(arg1);
	}

}
