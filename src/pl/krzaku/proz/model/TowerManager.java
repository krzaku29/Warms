package pl.krzaku.proz.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import pl.krzaku.proz.view.Crosshair;
import pl.krzaku.proz.view.Layout;
import pl.krzaku.proz.view.Marker;
import pl.krzaku.proz.view.Renderable;

/**
 * Class managing the towers
 * @author Patryk Majkrzak
 * @version 1.0
 */
public class TowerManager
{
	///How many players on the map
	private int numberOfPlayers;
	///Game state
	private GameState gameState;
	///Game map
	private GameMap gameMap;
	
	///List of lists of towers (outer list for players inner for towers)
	private ArrayList<LinkedList<Tower>> towerList;
	///Is player's active tower rotating left
	private boolean[] activeTowerRotateLeft;
	///Is player's active tower rotating right
	private boolean[] activeTowerRotateRight;
	///Is player's active tower increasing power
	private boolean[] activeTowerMorePower;
	///Is player's active tower decreasing power
	private boolean[] activeTowerLessPower;
	///Is player's active tower shooting
	private boolean[] activeTowerShooting;
	///Player's acitve tower
	private Tower[] activeTower;
	
	/**
	 * Constructor. Generates towers.
	 * @param map game map
	 * @param seed seed for generation
	 * @param numberOfTowers how many towers to generate
	 * @param numberOfPlayers how many players there are
	 * @param gameState game state
	 */
	public TowerManager(GameMap map, int seed, int numberOfTowers, int numberOfPlayers, GameState gameState)
	{
		this.gameState = gameState;
		this.gameMap = map;
		this.numberOfPlayers = numberOfPlayers;
		
		towerList = new ArrayList<LinkedList<Tower>>();
		for(int playerNumber = 0; playerNumber < numberOfPlayers; playerNumber++)
		{
			towerList.add(TowerGenerator.generatePlayer(map, seed, numberOfTowers, playerNumber));
		}
		activeTowerRotateLeft = new boolean[numberOfPlayers];
		activeTowerRotateRight = new boolean[numberOfPlayers];
		activeTowerMorePower = new boolean[numberOfPlayers];
		activeTowerLessPower = new boolean[numberOfPlayers];
		activeTowerShooting = new boolean[numberOfPlayers];
		activeTower = new Tower[numberOfPlayers];
		
		for(int i = 0; i < numberOfPlayers; i++)
		{
			activeTower[i] = towerList.get(i).getFirst();
		}
	}
	
	/**
	 * Method for updating tower controls
	 * @param deltaTime time elapsed from the last frame
	 */
	private void updateControls(double deltaTime)
	{
		for(int i = 0; i < numberOfPlayers; i++)
		{
			if(activeTowerRotateLeft[i])
			{
				activeTower[i].rotateLeft(deltaTime);
			}
			if(activeTowerRotateRight[i])
			{
				activeTower[i].rotateRight(deltaTime);
			}
			if(activeTowerMorePower[i])
			{
				activeTower[i].morePower(deltaTime);
			}
			if(activeTowerLessPower[i])
			{
				activeTower[i].lessPower(deltaTime);
			}
			if(activeTowerShooting[i])
			{
				activeTower[i].shoot(gameState);
			}
		}
	}
	
	/**
	 * Returns the first active tower in specified player's list. Or null of there isn't any
	 * @param playerArrayNumber player number
	 * @return first active tower of null if not found
	 */
	private Tower getFirstActiveTower(int playerArrayNumber)
	{
		Tower t = null;
		ListIterator<Tower> iter = towerList.get(playerArrayNumber).listIterator();
		while(iter.hasNext())
		{
			t = iter.next();
			if(t.isActive()) break;
		}
		
		return t;
	}
	
	/**
	 * Updates tower state
	 * @param deltaTime time elapsed from the last frame
	 */
	public void update(double deltaTime)
	{
		updateControls(deltaTime);
		
		Tower tower;
		for(int i = 0; i < numberOfPlayers; i++)
		{
			if(towerList.get(i).size() == 0) gameState.playerLost(i+1);
			ListIterator<Tower> it = towerList.get(i).listIterator();
			while(it.hasNext())
			{
				tower = it.next();
				tower.update(deltaTime);
				if(tower instanceof MapBorderCollidable) MapCollisionManager.checkBorderCollision(gameMap, (MapBorderCollidable)tower, deltaTime);
				if(tower instanceof MapCollidable) MapCollisionManager.checkCollision(gameState, gameMap, (MapCollidable)tower, deltaTime);
				if(!tower.isActive()) 
				{
					if(activeTower[i]== tower) activeTower[i] = getFirstActiveTower(i);
					it.remove();
				}
			}
		}
	}
	
	/**
	 * Returns list of towers for specified player
	 * @param playerNumber number of player
	 * @return tower list for specified player
	 */
	public LinkedList<Tower> getTowerList(int playerNumber)
	{
		return towerList.get(playerNumber);		
	}
	
	/**
	 * Changes active tower to next tower
	 * @param playerNumber player number
	 */
	public void nextTower(int playerNumber)
	{
		int i = towerList.get(playerNumber).indexOf(activeTower[playerNumber]);
		if(i!=towerList.get(playerNumber).size()-1) activeTower[playerNumber] = towerList.get(playerNumber).get(i+1);
		else activeTower[playerNumber] = towerList.get(playerNumber).getFirst();
	}
	
	/**
	 * Changes active tower to previous tower
	 * @param playerNumber player number
	 */
	public void previousTower(int playerNumber)
	{
		int i = towerList.get(playerNumber).indexOf(activeTower[playerNumber]);
		if(i!=0) activeTower[playerNumber] = towerList.get(playerNumber).get(i-1);
		else activeTower[playerNumber] = towerList.get(playerNumber).getLast();
	}
	
	public void setActiveTowerRotateLeft(boolean activeTowerRotateLeft, int playerNumber)
	{
		this.activeTowerRotateLeft[playerNumber] = activeTowerRotateLeft;
	}
	public void setActiveTowerRotateRight(boolean activeTowerRotateRight, int playerNumber)
	{
		this.activeTowerRotateRight[playerNumber] = activeTowerRotateRight;
	}
	public void setActiveTowerMorePower(boolean activeTowerMorePower, int playerNumber)
	{
		this.activeTowerMorePower[playerNumber] = activeTowerMorePower;
	}
	public void setActiveTowerLessPower(boolean activeTowerLessPower, int playerNumber)
	{
		this.activeTowerLessPower[playerNumber] = activeTowerLessPower;
	}
	public void setActiveTowerShooting(boolean activeTowerShooting, int playerNumber)
	{
		this.activeTowerShooting[playerNumber] = activeTowerShooting;
	}
	
	/**
	 * Adds towers to the layout
	 * @param layout layout for adding to
	 */
	public void addTowersToLayout(Layout layout)
	{
		Tower tower;
		for(int i = 0; i<numberOfPlayers; i++)
		{
			ListIterator<Tower> towerIterator = towerList.get(i).listIterator();
			
			while(towerIterator.hasNext())
			{
				tower = towerIterator.next();
				layout.add((Renderable)tower);
			}
			
			if(activeTower[i] != null)
			{
				if(i == 0 )layout.setPlayer1ActiveTowerHealth(activeTower[0].getHealth());
				else layout.setPlayer2ActiveTowerHealth(activeTower[i].getHealth());
			}
			
			Marker marker = new Marker(activeTower[i].getCenterXPosition(),activeTower[i].getCenterYPosition()-activeTower[i].getMarkerYOffset());
			Crosshair crosshair = new Crosshair(activeTower[i].getCenterXPosition()+activeTower[i].getCrosshairXOffset(),activeTower[i].getCenterYPosition()+activeTower[i].getCrosshairYOffset());
			
			layout.add((Renderable)marker);
			layout.add((Renderable)crosshair);
		}
	}
}
