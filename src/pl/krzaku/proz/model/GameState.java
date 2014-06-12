package pl.krzaku.proz.model;

import java.util.*;

import org.newdawn.slick.Image;

import pl.krzaku.proz.util.MapGenerator;
import pl.krzaku.proz.view.Layout;
import pl.krzaku.proz.view.Renderable;
import pl.krzaku.proz.view.SoundID;
import pl.krzaku.proz.view.Splash;

/**
 * Game state class. It manages game logic
 * @author Patryk Majkrzak
 * @version 1.5
 */
public class GameState
{
	///Number of players in the game
	private final int numberOfPlayers = 2;
	///Indicates whether any player lost the game by losing all his towers
	private int playerLost;
	///Terrain map
	private GameMap gameMap;
	///Tower manager
	private TowerManager towers;
	///List of bullets
	private LinkedList<Bullet> bulletList;
	///Contains sound to be played in current frame
	private LinkedList<SoundID> soundBuffer;
	
	/**
	 * Constructor. Initializes towers and game map
	 * @param mapSeed seed for map and tower generation
	 * @param numberOfTowers how many towers will each player have
	 */
	public GameState(int mapSeed, int numberOfTowers)
	{
		gameMap = MapGenerator.getMap(mapSeed, GameMap.getMapWidth(), GameMap.getMapHeight());
		towers = new TowerManager(gameMap, mapSeed, numberOfTowers, numberOfPlayers, this);
		bulletList = new LinkedList<Bullet>();
		soundBuffer = new LinkedList<SoundID>();
		playerLost = 0;
	}
	
	/**
	 * Adds a bullet to current game state
	 * @param add
	 */
	public void addBullet(Bullet add)
	{
		bulletList.add(add);
	}
	
	/**
	 * Updates game state based on specific delta time
	 * @param delta time elapsed from last frame
	 */
	public void updateGameState(int delta)
	{
		double deltaTime = delta/1000d;
		if(playerLost == 0)
		{
			ListIterator<Bullet> bulletListIterator = bulletList.listIterator();
			Bullet currentBullet;
			
			while(bulletListIterator.hasNext())
			{
				currentBullet = bulletListIterator.next();
				currentBullet.update(deltaTime);
				if(currentBullet instanceof MapBorderCollidable) MapCollisionManager.checkBorderCollision(gameMap, (MapBorderCollidable)currentBullet, deltaTime);
				if(currentBullet instanceof MapCollidable) MapCollisionManager.checkCollision(this, gameMap, (MapCollidable)currentBullet, deltaTime);
				if(currentBullet instanceof ObjectCollidable)
				{
					for(int i = 0; i < numberOfPlayers; i++)
					{
						ListIterator<Tower> iterator = towers.getTowerList(i).listIterator();
						Tower tower;
						while(iterator.hasNext())
						{
							tower = iterator.next();
							ObjectCollisionManager.checkObjectCollision(gameMap, (ObjectCollidable)currentBullet, (ObjectCollidable)tower, deltaTime);
						}	
					}
				}
				if(!currentBullet.isActive()) bulletListIterator.remove();
			}
			
			towers.update(deltaTime);
		}
	}
	
	/**
	 * Returns object layout for rendering
	 * @return
	 */
	public Layout getLayout()
	{
		Layout l = new Layout();
		Bullet bullet;
		SoundID sound;
		
		ListIterator<Bullet> bulletIterator = bulletList.listIterator();
		ListIterator<SoundID> soundIterator = soundBuffer.listIterator();

		towers.addTowersToLayout(l);
		
		
		while(bulletIterator.hasNext())
		{
			bullet = bulletIterator.next();
			l.add((Renderable)bullet);
		}
		
		if(playerLost != 0)
		{
			l.add((Renderable) new Splash(playerLost));
		}
		
		
		while(soundIterator.hasNext())
		{
			sound = soundIterator.next();
			l.addSound(sound);
		}
		
		soundBuffer.clear();		
		
		return l;
	}
	
	/**
	 * Returns map image for rendering
	 * @return map image
	 */
	public Image getMapImage()
	{
		return gameMap.getImage();
	}
	
	/**
	 * Called when object wants to inform that specific sound should be played
	 * @param sound sound to be played
	 */
	public void soundPlayed(SoundID sound)
	{
		soundBuffer.add(sound);
	}
	
	/**
	 * Called by repulsor tower. Changes velocities of all bullets
	 * @param speedX delta velocity along X axis
	 * @param speedY delta velocity along Y axis
	 */
	public void repulse(double speedX, double speedY)
	{
		ListIterator<Bullet> bulletListIterator = bulletList.listIterator();
		Bullet currentBullet;
		
		while(bulletListIterator.hasNext())
		{
			currentBullet = bulletListIterator.next();
			currentBullet.setVelocityX(currentBullet.getVelocityX()+speedX);
			currentBullet.setVelocityY(currentBullet.getVelocityY()+speedY);
		}
	}
	
	/**
	 * Called when any player loses all his towers	
	 * @param playerNumber number of player that have lost (0 or 1)
	 */
	public void playerLost(int playerNumber)
	{
		playerLost = playerNumber;
	}

	public void setActiveTowerRotateLeft(boolean activeTowerRotateLeft, int playerNumber)
	{
		towers.setActiveTowerRotateLeft(activeTowerRotateLeft, playerNumber);
	}

	public void setActiveTowerRotateRight(boolean activeTowerRotateRight, int playerNumber)
	{
		towers.setActiveTowerRotateRight(activeTowerRotateRight, playerNumber);
	}

	public void setActiveTowerMorePower(boolean activeTowerMorePower, int playerNumber)
	{
		towers.setActiveTowerMorePower(activeTowerMorePower, playerNumber);
	}

	public void setActiveTowerLessPower(boolean activeTowerLessPower, int playerNumber)
	{
		towers.setActiveTowerLessPower(activeTowerLessPower, playerNumber);
	}
	public void setActiveTowerShooting(boolean activeTowerShooting, int playerNumber)
	{
		towers.setActiveTowerShooting(activeTowerShooting, playerNumber);
	}
	
	/**
	 * Called for switching to the next tower
	 * @param playerNumber player number who wants to switch a tower
	 */
	public void nextTower(int playerNumber)
	{
		towers.nextTower(playerNumber);
	}
	
	/**
	 * Called for switching to the previous tower
	 * @param playerNumber player number who wants to switch a tower
	 */
	public void previousTower(int playerNumber)
	{
		towers.previousTower(playerNumber);
	}
	
	/**
	 * Returns number of players
	 * @return number of players
	 */
	public int getNumberOfPlayers()
	{
		return numberOfPlayers;
	}
	
}
