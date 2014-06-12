package pl.krzaku.proz.controller;

import org.newdawn.slick.Input;

/**
 * Class that contains key bindings for each player
 * @author Patryk Majkrzak
 * @version 1.1
 */
public final class KeyBinding
{
	///Key for increasing tower's shoot power
	private int[] keyMorePower = {Input.KEY_R, Input.KEY_UP};
	///Key for decreasing tower's shoot power
	private int[] keyLessPower = {Input.KEY_F, Input.KEY_DOWN};
	///Key for rotating tower counter-clockwise
	private int[] keyLeftRotate = {Input.KEY_D, Input.KEY_LEFT};
	///Key for rotating tower clockwise
	private int[] keyRightRotate = {Input.KEY_G, Input.KEY_RIGHT};
	///Key for switching to the next tower
	private int[] keyNextTower = {Input.KEY_W, Input.KEY_PERIOD};
	///Key for switching to the previous tower
	private int[] keyPrevTower = {Input.KEY_Q, Input.KEY_COMMA};
	///Key for shooting from active tower
	private int[] keyShoot = {Input.KEY_E, Input.KEY_SLASH};
	///Key for closing current game
	private int keyExit = Input.KEY_ESCAPE;
	
	///Singleton instance
	private static volatile KeyBinding singletonInstance = null;
	
	/**
	 * Gets class instance
	 * @return instance of class
	 */
	public static KeyBinding getInstance()
	{
		if(singletonInstance == null)
		{
			synchronized(KeyBinding.class)
			{
				if(singletonInstance == null)
				{	
					singletonInstance = new KeyBinding();				
				}
			}
		}
		return singletonInstance;
	}
	
	/**
	 * Private constructor to prevent generating default constructor
	 */
	private KeyBinding()
	{

	}

	public int getKeyMorePower(int playerNumber)
	{
		return keyMorePower[playerNumber];
	}

	public int getKeyLessPower(int playerNumber)
	{
		return keyLessPower[playerNumber];
	}

	public int getKeyLeftRotate(int playerNumber)
	{
		return keyLeftRotate[playerNumber];
	}

	public int getKeyRightRotate(int playerNumber)
	{
		return keyRightRotate[playerNumber];
	}

	public int getKeyNextTower(int playerNumber)
	{
		return keyNextTower[playerNumber];
	}
	
	public int getKeyPrevTower(int playerNumber)
	{
		return keyPrevTower[playerNumber];
	}

	public int getKeyExit()
	{
		return keyExit;
	}

	public int getKeyShoot(int playerNumber)
	{
		return keyShoot[playerNumber];
	}	
}
