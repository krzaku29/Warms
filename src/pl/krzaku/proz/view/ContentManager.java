package pl.krzaku.proz.view;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Sound;

/**
 * Class for content manager. Every resource used in the game must be loaded with it.
 * @author Patryk Majkrzak
 * @version 1.1
 */
public class ContentManager 
{
	///Set of loaded music content
	private Map<MusicID, Music> musicList;
	///Set of loaded sound content
	private Map<SoundID, Sound> soundList;
	///Set of loaded sprites
	private Map<SpriteID, SpriteSheet> spriteList;
	
	/**
	 * Content loading method
	 */
	private void loadContent()
	{
		addMusic(MusicID.FIVE_ARMIES, "res/music/fivearmies.ogg");
		addMusic(MusicID.FULL_ON, "res/music/fullon.ogg");

		addSound(SoundID.MORTAR_SHOT, "res/sound/mortarshot.wav");
		addSound(SoundID.MACHINE_GUN_SHOT, "res/sound/machinegunshot.wav");
		addSound(SoundID.LASER_SHOT, "res/sound/lasershot.wav");
		addSound(SoundID.NORMAL_SHOT, "res/sound/tankshot.wav");
		addSound(SoundID.EXPLOSION, "res/sound/explosion.wav");
		addSound(SoundID.REPULSE, "res/sound/repulse.wav");
		
		addSprite(SpriteID.TOWER_LASER, "res/sprites/laser.png", 16, 24);
		addSprite(SpriteID.TOWER_REPULSOR, "res/sprites/magnes.png",
				16, 24);
		addSprite(SpriteID.TOWER_GUN, "res/sprites/karabin.png", 16, 24);
		addSprite(SpriteID.TOWER_MORTAR, "res/sprites/mozdzierz.png",
				16, 24);
		addSprite(SpriteID.TOWER_NORMAL, "res/sprites/czolg.png", 16,
				24);

		addSprite(SpriteID.BULLET_LASER, "res/sprites/laserbullet.png",
				3, 3);
		addSprite(SpriteID.BULLET_GUN, "res/sprites/gunbullet.png", 3,
				3);
		addSprite(SpriteID.BULLET_MORTAR,
				"res/sprites/mortarbullet.png", 3, 3);
		addSprite(SpriteID.BULLET_NORMAL,
				"res/sprites/normalbullet.png", 3, 3);
		addSprite(SpriteID.PLAYER_1_LOST,
				"res/sprites/player1lost.png", 243, 39);
		addSprite(SpriteID.PLAYER_2_LOST,
				"res/sprites/player2lost.png", 243, 39);

		addSprite(SpriteID.MARKER, "res/sprites/marker.png", 12, 8);
		addSprite(SpriteID.CROSSHAIR, "res/sprites/crosshair.png", 3, 3);
	}
	
	/**
	 * Default constructor
	 */
	public ContentManager()
	{
		musicList = new HashMap<MusicID, Music>();
		soundList = new HashMap<SoundID, Sound>();	
		spriteList = new HashMap<SpriteID, SpriteSheet>();
		loadContent();
	}
	
	/**
	 * Adds music content with specified id
	 * @param id id for content for identification
	 * @param filename filename where to search for content
	 */
	public void addMusic(MusicID id, String filename) 
	{
		try
		{
			musicList.put(id, new Music(filename));
		} 
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds sound content with specified id
	 * @param id id for content for identification
	 * @param filename filename where to search for content
	 */	
	public void addSound(SoundID id, String filename) 
	{
		try
		{
			soundList.put(id, new Sound(filename));
		} 
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds sprite sheet with specified id
	 * @param id id for content for identification
	 * @param filename filename where to search for content
	 * @param tileWidth width of single sprite on the sheet
	 * @param tileHeight height of single sprite on the sheet
	 */
	public void addSprite(SpriteID id, String filename, int tileWidth, int tileHeight) 
	{
		try
		{
			spriteList.put(id, new SpriteSheet(filename, tileWidth, tileHeight));
		} 
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns music which is bound to specified id
	 * @param id id of music
	 * @return music object which has specified id
	 * @throws ContentNotFoundException thrown if there is not such content in content manager
	 */
	public Music getMusic(MusicID id) throws ContentNotFoundException
	{
		Music music = musicList.get(id);
		if(music == null) throw new ContentNotFoundException(id.toString());
		return music;
	}
	
	/**
	 * Returns sound which is bound to specified id
	 * @param id id of music
	 * @return music object which has specified id
	 * @throws ContentNotFoundException thrown if there is not such content in content manager
	 */
	public Sound getSound(SoundID id) throws ContentNotFoundException
	{
		Sound sound = soundList.get(id);
		if(sound == null) throw new ContentNotFoundException(id.toString());
		return sound;
	}
	
	/**
	 * Returns sprite which is bound to specified id
	 * @param id id of sprite
	 * @param number number of sprite on the sheet
	 * @return image object which has specified id and number
	 * @throws ContentNotFoundException thrown if there is not such content in content manager
	 */
	public Image getSprite(SpriteID id, int number) throws ContentNotFoundException
	{
		SpriteSheet spr = spriteList.get(id);
		if(spr == null) throw new ContentNotFoundException(id.toString());
		Image image = spr.getSubImage(number, 0);
		return image;
	}
	/**
	 * This method packs jukebox's playlist with all the music in this manager
	 * @param jukebox jukebox for loading music into
	 */
	public void loadJukebox(Jukebox jukebox)
	{
		for (Map.Entry<MusicID, Music> entry : musicList.entrySet()) 
		{
	        jukebox.addSong(entry.getKey());
	    }
	}
}
