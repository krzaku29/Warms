/**
 * @author 		Patryk Majkrzak
 * @version	1.0
 */
package pl.krzaku.proz;

import org.newdawn.slick.*;

import pl.krzaku.proz.view.View;
import pl.krzaku.proz.controller.MainController;
import pl.krzaku.proz.model.GameMap;
import pl.krzaku.proz.model.GameState;

/**
 * Main game class. Starting point for everything else.
 * @author		Patryk Majkrzak
 */
public class Game extends BasicGame
{
	private AppGameContainer appGameContainer;
	private GameState gameState;
	private View gameView;
	private MainController mainController;
	private int xResolution;
	private int yResolution;
	private int mapSeed;
	private int numberOfTowers;
	
	/**
	 * Main method
	 * @param args
	 */
	public void start()
	{
		try
		{
			appGameContainer = new AppGameContainer(this);
			appGameContainer.setDisplayMode(xResolution, yResolution, false);
			appGameContainer.setMinimumLogicUpdateInterval(8);
			appGameContainer.setTargetFrameRate(60);
			appGameContainer.start();
		} 
		catch (SlickException e)
		{
			e.printStackTrace();
		}

	}
	
	public Game(String gamename, int xResolution, int yResolution, int mapSeed, int numberOfTowers)
	{
		super(gamename);
		this.xResolution = xResolution;
		this.yResolution = yResolution;
		this.mapSeed = mapSeed;
		this.numberOfTowers = numberOfTowers;
	}

	@Override
	public void render(GameContainer gameContainer, Graphics graphics) throws SlickException
	{
		gameView.draw(graphics);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException
	{
		gameState = new GameState(mapSeed,numberOfTowers);
		gameView = new View(gameState, GameMap.getMapWidth(), GameMap.getMapHeight(), xResolution, yResolution);
		mainController = new MainController(appGameContainer, gameState, gameView);
	}
	
	@Override
	public void update(GameContainer gameContainer, int deltaTime) throws SlickException
	{
		gameState.updateGameState(deltaTime);
	}

}
