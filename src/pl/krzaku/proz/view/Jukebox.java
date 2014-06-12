package pl.krzaku.proz.view;

import java.util.ArrayList;

import org.newdawn.slick.Music;
import org.newdawn.slick.MusicListener;

public class Jukebox implements MusicListener
{
	private ArrayList<MusicID> playlist;
	private View view;
	private int lastPlayed;
	
	public Jukebox(View view)
	{
		playlist = new ArrayList<MusicID>();
		this.view = view;
		lastPlayed = 0;
	}
	
	public void addSong(MusicID song)
	{
		playlist.add(song);
	}
	
	public void startPlaying()
	{
		view.playMusic(playlist.get(0));
	}

	@Override
	public void musicEnded(Music song)
	{
		if(lastPlayed == playlist.size()-1)
		{
			lastPlayed = 0;
		}
		else lastPlayed++;
		
		view.playMusic(playlist.get(lastPlayed));
		
	}

	@Override
	public void musicSwapped(Music arg0, Music arg1)
	{
		//Nothing to do here :(		
	}
	
	
}
