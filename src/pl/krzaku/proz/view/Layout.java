package pl.krzaku.proz.view;

import java.util.LinkedList;
import java.util.List;

/**
 * Layout of sprites to be rendered by game view
 * @author Patryk Majkrzak
 * @version 1.1
 */
public class Layout
{
	///List of sprites to render
	private List<Renderable> renderList;
	///List of sounds to play
	private List<SoundID> soundList;
	
	//TODO Should be parameterized
	private int player1ActiveTowerHealth;
	private int player2ActiveTowerHealth;
	
	public Layout()
	{
		renderList = new LinkedList<Renderable>();
		soundList = new LinkedList<SoundID>();
		player1ActiveTowerHealth = 0;
		player2ActiveTowerHealth = 0;
	}
	
	/**
	 * Add renderable object to layout
	 * @param object object to add
	 */
	public void add(Renderable object)
	{
		renderList.add(object);
	}
	
	/**
	 * Adds sound to be played
	 * @param sound sound to add
	 */
	public void addSound(SoundID sound)
	{
		soundList.add(sound);
	}
	
	/**
	 * Gets render list
	 * @return render list
	 */
	public List<Renderable> getList()
	{
		return renderList;
	}
	
	/**
	 * Gets sound list
	 * @return sound list
	 */
	public List<SoundID> getSoundList()
	{
		return soundList;
	}

	public int getPlayer1ActiveTowerHealth()
	{
		return player1ActiveTowerHealth;
	}

	public int getPlayer2ActiveTowerHealth()
	{
		return player2ActiveTowerHealth;
	}

	public void setPlayer1ActiveTowerHealth(int player1ActiveTowerHealth)
	{
		this.player1ActiveTowerHealth = player1ActiveTowerHealth;
	}

	public void setPlayer2ActiveTowerHealth(int player2ActiveTowerHealth)
	{
		this.player2ActiveTowerHealth = player2ActiveTowerHealth;
	}
	
	
}
