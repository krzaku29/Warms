package pl.krzaku.proz.view;

import java.util.ListIterator;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import pl.krzaku.proz.model.GameState;

public class View
{
	private ContentManager content;
	private GameState gameState;
	private Jukebox jukebox;
	private float xScale;
	private float yScale;

	public View(GameState gState, int baseResolutionX, int baseResolutionY,
			int targetResolutionX, int targetResolutionY)
	{
		content = new ContentManager();
		gameState = gState;
		jukebox = new Jukebox(this);
		
		content.loadJukebox(jukebox);
		
		jukebox.startPlaying();
		
		xScale = (float) targetResolutionX / baseResolutionX;
		yScale = (float) targetResolutionY / baseResolutionY;
	}
	
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
		
		while (soundIterator.hasNext())
		{
			sound = soundIterator.next();
			playSound(sound);
		}
		
	}

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
