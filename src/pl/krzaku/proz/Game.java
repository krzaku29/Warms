/**
 * @author 		Patryk Majkrzak
 * @version	1.0
 */
package pl.krzaku.proz;

import org.newdawn.slick.*;

import pl.krzaku.proz.util.Rectangle;
import pl.krzaku.proz.view.SpriteID;
import pl.krzaku.proz.view.View;
import pl.krzaku.proz.model.GameState;
import pl.krzaku.proz.model.LaserBullet;

/**
 * Main game class. Starting point for everything else.
 * @author		Patryk Majkrzak
 */
public class Game extends BasicGame
{
	private static AppGameContainer appGameContainer;
	private static GameState gameState;
	private static View gameView;

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			appGameContainer = new AppGameContainer(new Game("Warms"));
			appGameContainer.setDisplayMode(1366, 768, false);
			appGameContainer.setMinimumLogicUpdateInterval(17);
			appGameContainer.setTargetFrameRate(60);
			appGameContainer.start();
		} 
		catch (SlickException e)
		{
			e.printStackTrace();
			System.out.println("DSFSDF");
		}

	}
	
	public Game(String gamename)
	{
		super(gamename);
	}

	@Override
	public void render(GameContainer gameContainer, Graphics graphics) throws SlickException
	{
		gameView.draw(graphics);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException
	{
		gameState = new GameState(1000);
		gameView = new View(gameState, 683, 384, 1366, 768);
		gameState.addBullet(new LaserBullet());
	}
	
	@Override
	public void update(GameContainer gameContainer, int deltaTime) throws SlickException
	{
		gameState.updateGameState(deltaTime);
	}

}
