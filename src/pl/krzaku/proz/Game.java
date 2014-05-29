/**
 * @author 		Patryk Majkrzak
 * @version	1.0
 */
package pl.krzaku.proz;

import org.newdawn.slick.*;

import pl.krzaku.proz.util.Rectangle;
import pl.krzaku.proz.model.GameState;
import pl.krzaku.proz.model.LaserBullet;

/**
 * Main game class. Starting point for everything else.
 * @author		Patryk Majkrzak
 */
public class Game extends BasicGame
{
	private static AppGameContainer appGameContainer;
	private static Input input;
	private static GameState gameState;

	private static Rectangle a = new Rectangle(10, 10, 10, 10);
	private static Rectangle b = new Rectangle(15, 5, 3, 3);
	private static LaserBullet bul = new LaserBullet();
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			appGameContainer = new AppGameContainer(new Game("Warms"));
			appGameContainer.setFullscreen(false);
			appGameContainer.setDisplayMode(1366, 768, false);
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
		graphics.drawImage(gameState.getMapImage(), 0, 0);
		
		graphics.drawRect((float)a.getPositionX(), (float)a.getPositionY(), (float)a.getRectangleWidth(), (float)a.getRectangleHeight());
		graphics.drawRect((float)b.getPositionX(), (float)b.getPositionY(), (float)b.getRectangleWidth(), (float)b.getRectangleHeight());
		if(input.isKeyDown(Input.KEY_W)) a.setPositionY(a.getPositionY()-1);
		if(input.isKeyDown(Input.KEY_S)) a.setPositionY(a.getPositionY()+1);
		if(input.isKeyDown(Input.KEY_A)) a.setPositionX(a.getPositionX()-1);
		if(input.isKeyDown(Input.KEY_D)) a.setPositionX(a.getPositionX()+1);		
		if(input.isKeyDown(Input.KEY_UP)) 	b.setPositionY(b.getPositionY()-1);
		if(input.isKeyDown(Input.KEY_DOWN)) b.setPositionY(b.getPositionY()+1);
		if(input.isKeyDown(Input.KEY_LEFT)) b.setPositionX(b.getPositionX()-1);
		if(input.isKeyDown(Input.KEY_RIGHT))b.setPositionX(b.getPositionX()+1);
		if(input.isKeyDown(Input.KEY_SPACE)) 
		{
			bul.setVelocityX(50d);
			bul.setVelocityY(50d);
		}
		
		graphics.drawOval((float)gameState.getBullet().getPositionX(), (float)gameState.getBullet().getPositionY(), 3, 3);
		
		if(a.contains(b)) graphics.drawString("KOLIZJA!", 10, 10);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException
	{
		input = appGameContainer.getInput();
		gameState = new GameState(1000);
		gameState.addBullet(new LaserBullet());
		bul = (LaserBullet)gameState.getBullet();		
	}
	
	@Override
	public void update(GameContainer gameContainer, int deltaTime) throws SlickException
	{
		input.poll(1366, 768);
		gameState.updateGameState(deltaTime);
	}

}
