package pl.krzaku.proz.controller;

import org.newdawn.slick.Input;

public final class KeyBinding
{
	private int[] keyMorePower = {Input.KEY_R, Input.KEY_UP};
	private int[] keyLessPower = {Input.KEY_F, Input.KEY_DOWN};
	private int[] keyLeftRotate = {Input.KEY_D, Input.KEY_LEFT};
	private int[] keyRightRotate = {Input.KEY_G, Input.KEY_RIGHT};
	private int[] keyNextTower = {Input.KEY_W, Input.KEY_PERIOD};
	private int[] keyPrevTower = {Input.KEY_Q, Input.KEY_COMMA};
	private int[] keyShoot = {Input.KEY_E, Input.KEY_SLASH};
	private int keyExit = Input.KEY_ESCAPE;
	
	private static volatile KeyBinding singletonInstance = null;
	
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
