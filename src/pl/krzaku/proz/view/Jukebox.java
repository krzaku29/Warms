package pl.krzaku.proz.view;

import java.util.ArrayList;

import org.newdawn.slick.Music;
import org.newdawn.slick.MusicListener;

/**
 * Jukebox. Changes music.
 * @author Patryk Majkrzak
 * @version 1.1
 */
public class Jukebox implements MusicListener
{
	///Playlist
	private ArrayList<MusicID> playlist;
	///Game view (plays music)
	private View view;
	///Index of last played song
	private int lastPlayed;
	
	/**
	 * Constructor.
	 * @param view game view
	 */
	public Jukebox(View view)
	{
		playlist = new ArrayList<MusicID>();
		this.view = view;
		lastPlayed = 0;
	}
	
	/**
	 * Adds a song to playlist
	 * @param song song to be added
	 */
	public void addSong(MusicID song)
	{
		playlist.add(song);
	}
	
	/**
	 * Starts playing songs
	 */
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
