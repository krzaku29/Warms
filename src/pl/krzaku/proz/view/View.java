package pl.krzaku.proz.view;

import java.util.ListIterator;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import pl.krzaku.proz.model.GameState;

public class View
{
	private ContentManager content;
	private GameState gameState;
	private float xScale;
	private float yScale;

	private void loadContent()
	{
		content.addMusic(MusicID.FIVE_ARMIES, "res/music/fivearmies.ogg");
		content.addMusic(MusicID.FULL_ON, "res/music/fullon.ogg");

		content.addSound(SoundID.MORTAR_SHOT, "res/sound/mortarshot.wav");
		content.addSound(SoundID.MACHINE_GUN_SHOT,
				"res/sound/machinegunshot.wav");

		content.addSprite(SpriteID.TOWER_LASER, "res/sprites/laser.png", 16, 24);
		content.addSprite(SpriteID.TOWER_REPULSOR, "res/sprites/magnes.png",
				16, 24);
		content.addSprite(SpriteID.TOWER_GUN, "res/sprites/karabin.png", 16, 24);
		content.addSprite(SpriteID.TOWER_MORTAR, "res/sprites/mozdzierz.png",
				16, 24);
		content.addSprite(SpriteID.TOWER_NORMAL, "res/sprites/czolg.png", 16,
				24);

		content.addSprite(SpriteID.BULLET_LASER, "res/sprites/laserbullet.png",
				3, 3);
		content.addSprite(SpriteID.BULLET_GUN, "res/sprites/gunbullet.png", 3,
				3);
		content.addSprite(SpriteID.BULLET_MORTAR,
				"res/sprites/mortarbullet.png", 3, 3);
		content.addSprite(SpriteID.BULLET_NORMAL,
				"res/sprites/normalbullet.png", 3, 3);

		content.addSprite(SpriteID.MARKER, "res/sprites/marker.png", 12, 8);
	}

	public View(GameState gState, int baseResolutionX, int baseResolutionY,
			int targetResolutionX, int targetResolutionY)
	{
		content = new ContentManager();
		gameState = gState;

		xScale = (float) targetResolutionX / baseResolutionX;
		yScale = (float) targetResolutionY / baseResolutionY;

		loadContent();
	}

	public void draw(Graphics graphics)
	{
		Layout layout = gameState.getLayout();
		Image mapImage = gameState.getMapImage();

		graphics.scale(xScale, yScale);

		ListIterator<Renderable> renderIterator = layout.getList()
				.listIterator();
		Renderable renderable;

		try
		{
			graphics.drawImage(content.getSprite(SpriteID.TOWER_LASER, 0), 110,
					16);
			graphics.drawImage(content.getSprite(SpriteID.TOWER_GUN, 0), 130,
					16);
			graphics.drawImage(content.getSprite(SpriteID.TOWER_REPULSOR, 0),
					150, 16);
			graphics.drawImage(content.getSprite(SpriteID.TOWER_MORTAR, 0),
					170, 16);
			graphics.drawImage(content.getSprite(SpriteID.TOWER_NORMAL, 0),
					190, 16);
			graphics.drawImage(content.getSprite(SpriteID.MARKER, 0), 192, 8);

			graphics.drawLine(30, 30, 50, 50);
		} catch (ContentNotFoundException e)
		{
			e.printStackTrace();
		}

		graphics.drawImage(mapImage, 0, 0);
		
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
		} catch (ContentNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
