package pl.krzaku.proz.view;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Sound;

public class ContentManager 
{
	private ArrayList<Music> musicList = new ArrayList<Music>();
	private ArrayList<Sound> soundEffectList = new ArrayList<Sound>();
	private ArrayList<SpriteSheet> spriteList = new ArrayList<SpriteSheet>();
	
	public ContentManager()
	{
		
	}
	
	public void addMusic(String filename) 
	{
		try
		{
			musicList.add(new Music(filename));
		} 
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	
}
