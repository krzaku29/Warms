package pl.krzaku.proz.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProviderListener;

import pl.krzaku.proz.model.GameState;
import pl.krzaku.proz.model.GunBullet;
import pl.krzaku.proz.model.LaserBullet;
import pl.krzaku.proz.model.MortarBullet;
import pl.krzaku.proz.model.NormalBullet;
import pl.krzaku.proz.view.MusicID;
import pl.krzaku.proz.view.SoundID;
import pl.krzaku.proz.view.View;

public class MainController implements InputProviderListener
{
	private PlayerInput playerInput;
	private GameState gameState;
	private View view;
	
	public MainController(GameContainer gameContainer, GameState argGameState, View argView)
	{
		playerInput = new PlayerInput(gameContainer, this);
		gameState = argGameState;
		view = argView;		
		
		gameState.addBullet(new LaserBullet(40, 40, 40, -40));
		gameState.addBullet(new MortarBullet(40, 40, 40, 0));
		gameState.addBullet(new NormalBullet(40, 40, 80, 0));
		gameState.addBullet(new GunBullet(40, 40, 160, 0));
	}
	
	@Override
	public void controlPressed(Command command)
	{
		if(command.equals(playerInput.getTowerLeftRotateCommand())) 
		{
			gameState.setActiveTowerRotateLeft(true);
		}
		else if(command.equals(playerInput.getTowerRightRotateCommand()))
		{
			gameState.setActiveTowerRotateRight(true);
		}
		else if(command.equals(playerInput.getTowerMorePowerCommand()))
		{
			gameState.setActiveTowerMorePower(true);
		}
		else if(command.equals(playerInput.getTowerLessPowerCommand()))
		{
			gameState.setActiveTowerLessPower(true);
		}
	}

	@Override
	public void controlReleased(Command command)
	{
		if(command.equals(playerInput.getTowerLeftRotateCommand())) 
		{
			gameState.setActiveTowerRotateLeft(false);
		}
		else if(command.equals(playerInput.getTowerRightRotateCommand()))
		{
			gameState.setActiveTowerRotateRight(false);
		}
		else if(command.equals(playerInput.getTowerMorePowerCommand()))
		{
			gameState.setActiveTowerMorePower(false);
		}
		else if(command.equals(playerInput.getTowerLessPowerCommand()))
		{
			gameState.setActiveTowerLessPower(false);
		}
	}

}
