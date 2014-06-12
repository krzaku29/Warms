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
	///Game window container
	private AppGameContainer appGameContainer;
	///State of game (towers, bullets etc.)
	private GameState gameState;
	///Game view (graphics, sound, music)
	private View gameView;
	///Controller responsible for interpretation of input
	private MainController mainController;
	///X resolution of game window
	private int xResolution;
	///Y resolution of game window
	private int yResolution;
	///Seed for generating random map
	private int mapSeed;
	///Number of towers for each player
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
			appGameContainer.setShowFPS(false);
			appGameContainer.start();
		} 
		catch (SlickException e)
		{
			e.printStackTrace();
		}

	}
	
	/**
	 * Constructor for game object
	 * @param gamename name of the game displayed on window title bar
	 * @param xResolution X resolution of game window
	 * @param yResolution Y resolution of game window
	 * @param mapSeed seed for map generation
	 * @param numberOfTowers number of towers for each player
	 */
	public Game(String gamename, int xResolution, int yResolution, int mapSeed, int numberOfTowers)
	{
		super(gamename);
		this.xResolution = xResolution;
		this.yResolution = yResolution;
		this.mapSeed = mapSeed;
		this.numberOfTowers = numberOfTowers;
	}

	/**
	 * Method called for rendering every frame 
	 */
	@Override
	public void render(GameContainer gameContainer, Graphics graphics) throws SlickException
	{
		gameView.draw(graphics);
	}

	/**
	 * Method called before starting the game. Game components are initialized here
	 */
	@Override
	public void init(GameContainer arg0) throws SlickException
	{
		gameState = new GameState(mapSeed,numberOfTowers);
		gameView = new View(gameState, GameMap.getMapWidth(), GameMap.getMapHeight(), xResolution, yResolution);
		mainController = new MainController(appGameContainer, gameState, gameView);
	}
	
	/**
	 * Method called for updating game state	
	 */
	@Override
	public void update(GameContainer gameContainer, int deltaTime) throws SlickException
	{
		gameState.updateGameState(deltaTime);
	}

}
