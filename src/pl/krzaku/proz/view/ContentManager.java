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
	 * Default constructor
	 */
	public ContentManager()
	{
		musicList = new HashMap<MusicID, Music>();
		soundList = new HashMap<SoundID, Sound>();	
		spriteList = new HashMap<SpriteID, SpriteSheet>();
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
}
