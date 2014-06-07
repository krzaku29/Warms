package pl.krzaku.proz.model;

import java.util.*;

import org.newdawn.slick.Image;

import pl.krzaku.proz.util.MapGenerator;
import pl.krzaku.proz.view.Layout;
import pl.krzaku.proz.view.Renderable;


public class GameState
{
	private GameMap gameMap;
	private LinkedList<Tower> towerList;
	private LinkedList<Bullet> bulletList;
	
	private boolean activeTowerRotateLeft;
	private boolean activeTowerRotateRight;
	private boolean activeTowerMorePower;
	private boolean activeTowerLessPower;
	private Tower activeTower;
	
	public GameState(int mapSeed)
	{
		towerList = new LinkedList<Tower>();
		bulletList = new LinkedList<Bullet>();
		gameMap = MapGenerator.getMap(mapSeed, GameMap.getMapWidth(), GameMap.getMapHeight());
		
		activeTowerLessPower = false;
		activeTowerMorePower = false;
		activeTowerRotateLeft = false;
		activeTowerRotateRight = false;		
	}
	
	public void addBullet(Bullet add)
	{
		bulletList.add(add);
	}
	
	public void addTower(Tower add)
	{
		towerList.add(add);
	}
	
	public void updateGameState(int delta)
	{
		double dt = delta/1000d;
		ListIterator<Bullet> bulletListIterator = bulletList.listIterator();
		Bullet currentBullet;
		ListIterator<Tower> towerListIterator = towerList.listIterator();
		Tower currentTower;
		
		while(bulletListIterator.hasNext())
		{
			currentBullet = bulletListIterator.next();
			currentBullet.update(dt);
			if(currentBullet instanceof MapBorderCollidable) MapCollisionManager.checkBorderCollision(gameMap, (MapBorderCollidable)currentBullet, dt);
			if(currentBullet instanceof MapCollidable) MapCollisionManager.checkCollision(gameMap, (MapCollidable)currentBullet, dt);
			if(currentBullet instanceof ObjectCollidable)
			{
				ListIterator<Tower> iterator = towerList.listIterator();
				Tower tower;
				while(iterator.hasNext())
				{
					tower = iterator.next();
					ObjectCollisionManager.checkObjectCollision(gameMap, (ObjectCollidable)currentBullet, (ObjectCollidable)tower, dt);
				}				
			}
			if(!currentBullet.isActive()) bulletListIterator.remove();
		}
		
		while(towerListIterator.hasNext())
		{
			currentTower = towerListIterator.next();
			currentTower.update(dt);
			if(currentTower instanceof MapBorderCollidable) MapCollisionManager.checkBorderCollision(gameMap, (MapBorderCollidable)currentTower, dt);
			if(currentTower instanceof MapCollidable) MapCollisionManager.checkCollision(gameMap, (MapCollidable)currentTower, dt);
			if(!currentTower.isActive()) towerListIterator.remove();
		}
		
	}
	
	public Layout getLayout()
	{
		Layout l = new Layout();
		Bullet bullet;
		Tower tower;
		
		ListIterator<Bullet> bulletIterator = bulletList.listIterator();
		ListIterator<Tower> towerIterator = towerList.listIterator();
		
		while(bulletIterator.hasNext())
		{
			bullet = bulletIterator.next();
			l.add((Renderable)bullet);
		}
		
		while(towerIterator.hasNext())
		{
			tower = towerIterator.next();
			l.add((Renderable)tower);
		}
		
		return l;
	}
	
	public Image getMapImage()
	{
		return gameMap.getImage();
	}
	
	public void setActiveTowerRotateLeft(boolean activeTowerRotateLeft)
	{
		this.activeTowerRotateLeft = activeTowerRotateLeft;
	}

	public void setActiveTowerRotateRight(boolean activeTowerRotateRight)
	{
		this.activeTowerRotateRight = activeTowerRotateRight;
	}

	public void setActiveTowerMorePower(boolean activeTowerMorePower)
	{
		this.activeTowerMorePower = activeTowerMorePower;
	}

	public void setActiveTowerLessPower(boolean activeTowerLessPower)
	{
		this.activeTowerLessPower = activeTowerLessPower;
	}
	
	
}
