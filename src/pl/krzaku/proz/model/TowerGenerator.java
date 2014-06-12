package pl.krzaku.proz.model;

import java.util.LinkedList;
import java.util.Random;

/**
 * Generates towers according to a seed
 * @author Patryk Majkrzak
 * @version 1.1
 */
public class TowerGenerator
{
	///Distance of outermost tower from map edge
	private static final int startXPosition = 15;
	
	/**
	 * Returns an Y position of first non-terrain pixel on game map at specified X position
	 * @param gameMap game map to check
	 * @param position X position to search on
	 * @return Y position of first non terrain pixel on the position
	 */
	private static int getGroundPosition(GameMap gameMap, int position)
	{
		int height = GameMap.getMapHeight()-1;
		while(gameMap.isTerrain(position, height))
		{
			height--;
		}
		return height;
	}
	
	/**
	 * Generates towers for a player
	 * @param gameMap game map
	 * @param seed seed for generation
	 * @param numberOfTowers how may towers to generate
	 * @param playerNumber number of player to generate for
	 * @return list of towers for specified player and seed
	 */
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
		else 
		{
			seed = -seed;
			flipped = false;
			delta = GameMap.getMapWidth()/3/numberOfTowers;
			
			xPosition = GameMap.getMapWidth()-startXPosition -delta*(numberOfTowers-1);
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
