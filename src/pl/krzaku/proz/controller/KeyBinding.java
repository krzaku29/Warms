package pl.krzaku.proz.controller;

import org.newdawn.slick.Input;

public final class KeyBinding
{
	private int keyMorePower;
	private int keyLessPower;
	private int keyLeftRotate;
	private int keyRightRotate;
	private int keyNextTower;
	private int keyExit;
	private int keyShoot;
	
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
		keyMorePower = Input.KEY_UP;
		keyLessPower = Input.KEY_DOWN;
		keyLeftRotate = Input.KEY_LEFT;
		keyRightRotate = Input.KEY_RIGHT;
		keyNextTower = Input.KEY_A;
		keyExit = Input.KEY_ESCAPE;
		keyShoot = Input.KEY_S;
	}

	public int getKeyMorePower()
	{
		return keyMorePower;
	}

	public int getKeyLessPower()
	{
		return keyLessPower;
	}

	public int getKeyLeftRotate()
	{
		return keyLeftRotate;
	}

	public int getKeyRightRotate()
	{
		return keyRightRotate;
	}

	public int getKeyNextTower()
	{
		return keyNextTower;
	}

	public int getKeyExit()
	{
		return keyExit;
	}

	public int getKeyShoot()
	{
		return keyShoot;
	}	
}
