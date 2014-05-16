package pl.krzaku.proz.util;

import pl.krzaku.proz.model.GameMap;

public class MapGenerator
{
	private static final int mapWidth = 683;
	private static final int mapHeight = 384;
	
	private static double getNoise(int x)
	{
		double a;
		x = (x << 13) ^ x;
		a = (1.0 - ((x * (x * x * 15731 + 789221) + 1376312589) & 0x7fffffff) / 1073741824.0);
		return a;
	}

	private static double cosineInterpolate(double a, double b, double x)
	{
		double ft = x * 3.1415927;

		double f = (1 - Math.cos(ft)) * 0.5;

		return a * (1 - f) + b * f;
	}

	public static GameMap getMap(int seed)
	{
		double tab[] = new double[9];
		GameMap genMap = new GameMap();
		int segmentWidth = mapWidth/8;
		int height = mapHeight/3;

		for (int i = 0; i <= 8; i++)
		{
			tab[i] = mapHeight*2/5 + getNoise(seed^i)*height;
		}

		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j <= segmentWidth; j++)
			{
				genMap.setHeight(i * segmentWidth + j, (int)cosineInterpolate(tab[i], tab[i + 1], j/(float)segmentWidth));
			}
		}

		return genMap;
	}

}
