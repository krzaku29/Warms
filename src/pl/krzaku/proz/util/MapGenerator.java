package pl.krzaku.proz.util;

import pl.krzaku.proz.model.GameMap;

/**
 * Map generator. Generates game maps using specified seed.
 * @author Patryk Majkrzak
 * @version 1.2
 */ 
public class MapGenerator
{
	/**
	 * Returns random noise value (always the same) for specified seed
	 * @param x seed
	 * @return random value for seed
	 */
	private static double getNoise(int x)
	{
		double a;
		x = (x << 13) ^ x;
		a = (1.0 - ((x * (x * x * 15731 + 789221) + 1376312589) & 0x7fffffff) / 1073741824.0);
		return a;
	}

	/**
	 * Returns value of cosine interpolation between two values at specified point.
	 * @param a first value for interpolating
	 * @param b second value for interpolating
	 * @param x position between interpolated values from range (0,1)
	 * @return value of cosine interpolation between two values
	 */
	private static double cosineInterpolate(double a, double b, double x)
	{
		double ft = x * 3.1415927;

		double f = (1 - Math.cos(ft)) * 0.5;

		return a * (1 - f) + b * f;
	}

	/**
	 * Returns generated game map object with specified arguments
	 * @param seed seed for generation
	 * @param mapWidth width of map in pixels
	 * @param mapHeight height of map in pixels
	 * @return generated game map
	 */
	public static GameMap getMap(int seed, int mapWidth, int mapHeight)
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
