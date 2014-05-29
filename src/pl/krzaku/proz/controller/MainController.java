package pl.krzaku.proz.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProviderListener;

public class MainController implements InputProviderListener
{
	private PlayerInput playerInput;
	
	public MainController(GameContainer gameContainer)
	{
		playerInput = new PlayerInput(gameContainer, this);
	}
	
	@Override
	public void controlPressed(Command command)
	{
		if(command == playerInput.getTowerLeftRotateCommand()) 
		{
			
		}
	}

	@Override
	public void controlReleased(Command command)
	{
		
	}

}
