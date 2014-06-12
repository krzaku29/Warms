package pl.krzaku.proz.view;

import java.util.ListIterator;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import pl.krzaku.proz.model.GameMap;
import pl.krzaku.proz.model.GameState;

/**
 * Class managing rendering and playing sound in the game
 * @author Patryk Majkrzak
 * @version 1.3
 */
public class View
{
	///Content manager
	private ContentManager content;
	///State of game
	private GameState gameState;
	///Jukebox
	private Jukebox jukebox;
	///X scale between window resolution and map resolution
	private float xScale;
	///Y scale between window resolution and map resolution
	private float yScale;

	/**
	 * Constructor. Initializes other view classes
	 * @param gameState game state
	 * @param baseResolutionX map X resolution
	 * @param baseResolutionY map Y resolution
	 * @param targetResolutionX game window X resolution
	 * @param targetResolutionY game window Y resolution
	 */
	public View(GameState gameState, int baseResolutionX, int baseResolutionY,
			int targetResolutionX, int targetResolutionY)
	{
		content = new ContentManager();
		this.gameState = gameState;
		jukebox = new Jukebox(this);
		
		content.loadJukebox(jukebox);
		
		jukebox.startPlaying();
		
		xScale = (float) targetResolutionX / baseResolutionX;
		yScale = (float) targetResolutionY / baseResolutionY;
	}
	
	/**
	 * Method invoked every frame is drawn
	 * @param graphics device to draw on
	 */
	public void draw(Graphics graphics)
	{
		Layout layout = gameState.getLayout();
		Image mapImage = gameState.getMapImage();

		graphics.scale(xScale, yScale);
		
		graphics.drawImage(mapImage, 0, 0);
		
		ListIterator<Renderable> renderIterator = layout.getList()
				.listIterator();
		ListIterator<SoundID> soundIterator = layout.getSoundList()
				.listIterator();
		SoundID sound;
		Renderable renderable;		
		
		while (renderIterator.hasNext())
		{
			renderable = renderIterator.next();
			try
			{
				if (renderable.isFlipped())
				{
					graphics.drawImage(
							content.getSprite(renderable.getSpriteID(),
									renderable.getSpriteNumber())
									.getFlippedCopy(true, false), renderable
									.getRenderPositionX(), renderable
									.getRenderPositionY());

				} 
				else
				{
					graphics.drawImage(content.getSprite(
							renderable.getSpriteID(),
							renderable.getSpriteNumber()), renderable
							.getRenderPositionX(), renderable
							.getRenderPositionY());
				}

			} 
			catch (ContentNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		
		graphics.drawString("Player 1", 10, 10);
		graphics.drawString("Tower HP: " + layout.getPlayer1ActiveTowerHealth(), 10, 20);
		
		graphics.drawString("Player 2", GameMap.getMapWidth()-150, 10);
		graphics.drawString("Tower HP: " + layout.getPlayer2ActiveTowerHealth(), GameMap.getMapWidth()-150, 20);

		
		
		while (soundIterator.hasNext())
		{
			sound = soundIterator.next();
			playSound(sound);
		}
		
	}

	/**
	 * Plays song with specified ID
	 * @param id song ID
	 */
	public void playMusic(MusicID id)
	{
		try
		{
			content.getMusic(id).play();
		} catch (ContentNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Plays sound with specified ID
	 * @param id song ID
	 */
	public void playSound(SoundID id)
	{
		try
		{
			content.getSound(id).play();
		} 
		catch (ContentNotFoundException e)
		{
			e.printStackTrace();
		}
	}

}
