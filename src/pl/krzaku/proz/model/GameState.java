package pl.krzaku.proz.model;

import java.util.*;

import org.newdawn.slick.Image;

import pl.krzaku.proz.util.MapGenerator;
import pl.krzaku.proz.view.Crosshair;
import pl.krzaku.proz.view.Layout;
import pl.krzaku.proz.view.Marker;
import pl.krzaku.proz.view.Renderable;
import pl.krzaku.proz.view.SoundID;
import pl.krzaku.proz.view.Splash;


public class GameState
{
	private final int numberOfPlayers = 2;
	private int playerLost;
	private GameMap gameMap;
	private TowerManager towers;
	private LinkedList<Bullet> bulletList;
	private LinkedList<SoundID> soundBuffer;
	
	
	public GameState(int mapSeed, int numberOfTowers)
	{
		gameMap = MapGenerator.getMap(mapSeed, GameMap.getMapWidth(), GameMap.getMapHeight());
		towers = new TowerManager(gameMap, mapSeed, numberOfTowers, numberOfPlayers, this);
		bulletList = new LinkedList<Bullet>();
		soundBuffer = new LinkedList<SoundID>();
		playerLost = 0;
	}
	
	public void addBullet(Bullet add)
	{
		bulletList.add(add);
	}
	
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
	
	public Image getMapImage()
	{
		return gameMap.getImage();
	}
	
	public void soundPlayed(SoundID sound)
	{
		soundBuffer.add(sound);
	}
	
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
	
	public void nextTower(int playerNumber)
	{
		towers.nextTower(playerNumber);
	}
	
	public void previousTower(int playerNumber)
	{
		towers.previousTower(playerNumber);
	}
	
	public int getNumberOfPlayers()
	{
		return numberOfPlayers;
	}
	
}
