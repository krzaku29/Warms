package pl.krzaku.proz.view;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Sound;

public class ContentManager 
{
	private Map<MusicID, Music> musicList;
	private Map<SoundID, Sound> soundList;
	private Map<SpriteID, SpriteSheet> spriteList;
	
	public ContentManager()
	{
		musicList = new HashMap<MusicID, Music>();
		soundList = new HashMap<SoundID, Sound>();	
		spriteList = new HashMap<SpriteID, SpriteSheet>();
	}
	
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
	
	public void addSprite(SpriteID id, String filename, int tileWidth, int tileHeight) 
	{
		try
		{
			spriteList.put(id, new SpriteSheet(filename, tileHeight, tileHeight));
		} 
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public Music getMusic(MusicID id) throws ContentNotFoundException
	{
		Music music = musicList.get(id);
		if(music == null) throw new ContentNotFoundException(id.toString());
		return music;
	}
	
	public Sound getSound(SoundID id) throws ContentNotFoundException
	{
		Sound sound = soundList.get(id);
		if(sound == null) throw new ContentNotFoundException(id.toString());
		return sound;
	}
	
	public Image getSprite(SpriteID id, int number) throws ContentNotFoundException
	{
		Image m = spriteList.get(id).getSubImage(number, 0);
		if(m == null) throw new ContentNotFoundException(id.toString());
		return m;
	}
}
