package pl.krzaku.proz.model;

import java.util.LinkedList;
import java.util.Random;

public class TowerGenerator
{
	private static final int startXPosition = 15;
	
	private static int getGroundPosition(GameMap gameMap, int position)
	{
		int height = GameMap.getMapHeight()-1;
		while(gameMap.isTerrain(position, height))
		{
			height--;
		}
		return height;
	}
	
	public static LinkedList<Tower> generatePlayer(GameMap gameMap, int seed, int numberOfTowers, int playerNumber)
	{
		LinkedList<Tower> list = new LinkedList<Tower>();
		int xPosition;
		int delta;
		boolean flipped;

		if(playerNumber == 0) 
		{
			flipped = true;
			xPosition = startXPosition;
			delta = GameMap.getMapWidth()/3/numberOfTowers;
		}
		else if(playerNumber == 1) 
		{
			seed = -seed;
			flipped = false;
			xPosition = GameMap.getMapWidth()-startXPosition;
			delta = -GameMap.getMapWidth()/3/numberOfTowers;
		}
		else 
		{
			//FIXME
			return list;
		}
		
		Random r = new Random(seed);
		
		for(int i = 0; i < numberOfTowers; i++)
		{
			int yPosition = getGroundPosition(gameMap, xPosition);
			int type = r.nextInt(5);
			
			if(type == 0) list.add(new LaserTower(xPosition, yPosition, flipped));
			if(type == 1) list.add(new GunTower(xPosition, yPosition, flipped));
			if(type == 2) list.add(new NormalTower(xPosition, yPosition, flipped));
			if(type == 3) list.add(new MortarTower(xPosition, yPosition, flipped));
			if(type == 4) list.add(new RepulsorTower(xPosition, yPosition, flipped));
			
			xPosition += delta;
		}
		
		return list;
	}
	
}
