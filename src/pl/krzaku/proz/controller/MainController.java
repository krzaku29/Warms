package pl.krzaku.proz.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProviderListener;

import pl.krzaku.proz.model.GameState;

public class MainController implements InputProviderListener
{
	private PlayerInput playerInput;
	private GameState gameState;
	
	public MainController(GameContainer gameContainer, GameState argGameState)
	{
		playerInput = new PlayerInput(gameContainer, this);
		gameState = argGameState;
	}
	
	@Override
	public void controlPressed(Command command)
	{
		if(command.equals(playerInput.getTowerLeftRotateCommand())) 
		{
			
		}
	}

	@Override
	public void controlReleased(Command command)
	{
		
	}

}
