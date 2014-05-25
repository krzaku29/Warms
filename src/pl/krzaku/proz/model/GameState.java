package pl.krzaku.proz.model;

import java.util.*;

import org.newdawn.slick.Image;

import pl.krzaku.proz.util.MapGenerator;

public class GameState
{
	private GameMap gameMap;
	private LinkedList<Tower> towerList;
	private LinkedList<Bullet> bulletList;
	
	public GameState(int mapSeed)
	{
		towerList = new LinkedList<Tower>();
		bulletList = new LinkedList<Bullet>();
		gameMap = MapGenerator.getMap(mapSeed);
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
		Bullet currentBullet = bulletList.getFirst();
		for(int i = 0; i<bulletList.size(); i++)
		{
			currentBullet.update(dt);
			if(Test.class.isInstance(currentBullet)) gameMap.destroyTerrain(100, 100, 100);
			
			if(MapCollisionManager.checkCollision(gameMap, (MapCollidable)currentBullet))
			{
				gameMap.destroyTerrain(((MapCollidable) currentBullet).getMapCollisionXPoint(), 
										((MapCollidable) currentBullet).getMapCollisionYPoint(), 
										((MapCollidable) currentBullet).getMapExplosionRadius());
			}
			currentBullet = bulletList.get(i);
		}
		
	}
	
	public Bullet getBullet()
	{
		return bulletList.get(0);
	}
	
	public Image getMapImage()
	{
		return gameMap.getImage();
	}
}