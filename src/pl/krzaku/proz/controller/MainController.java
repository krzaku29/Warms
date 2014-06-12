package pl.krzaku.proz.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProviderListener;

import pl.krzaku.proz.model.GameState;
import pl.krzaku.proz.view.View;

public class MainController implements InputProviderListener
{
	private PlayerInput playerInput;
	private GameState gameState;
	private GameContainer gameContainer;
	private View view;
	
	public MainController(GameContainer gameContainer, GameState argGameState, View argView)
	{
		this.gameContainer = gameContainer;
		playerInput = new PlayerInput(gameContainer, this);
		gameState = argGameState;
		view = argView;		
	}
	
	@Override
	public void controlPressed(Command command)
	{
		for(int i = 0; i < gameState.getNumberOfPlayers(); i++)
		{
			if(command.equals(playerInput.getTowerLeftRotateCommand(i))) 
			{
				gameState.setActiveTowerRotateLeft(true,i);
			}
			else if(command.equals(playerInput.getTowerRightRotateCommand(i)))
			{
				gameState.setActiveTowerRotateRight(true,i);
			}
			else if(command.equals(playerInput.getTowerMorePowerCommand(i)))
			{
				gameState.setActiveTowerMorePower(true,i);
			}
			else if(command.equals(playerInput.getTowerLessPowerCommand(i)))
			{
				gameState.setActiveTowerLessPower(true,i);
			}
			else if(command.equals(playerInput.getNextTowerCommand(i)))
			{
				gameState.nextTower(i);
			}
			else if(command.equals(playerInput.getPrevTowerCommand(i)))
			{
				gameState.previousTower(i);
			}
			else if(command.equals(playerInput.getTowerShootCommand(i)))
			{
				gameState.setActiveTowerShooting(true,i);
			}
			else if(command.equals(playerInput.getExitGameCommand()))
			{
				gameContainer.exit();
			}
		}
	}

	@Override
	public void controlReleased(Command command)
	{
		for(int i = 0; i < gameState.getNumberOfPlayers(); i++)
		{
			if(command.equals(playerInput.getTowerLeftRotateCommand(i))) 
			{
				gameState.setActiveTowerRotateLeft(false,i);
			}
			else if(command.equals(playerInput.getTowerRightRotateCommand(i)))
			{
				gameState.setActiveTowerRotateRight(false,i);
			}
			else if(command.equals(playerInput.getTowerMorePowerCommand(i)))
			{
				gameState.setActiveTowerMorePower(false,i);
			}
			else if(command.equals(playerInput.getTowerLessPowerCommand(i)))
			{
				gameState.setActiveTowerLessPower(false,i);
			}
			else if(command.equals(playerInput.getTowerShootCommand(i)))
			{
				gameState.setActiveTowerShooting(false,i);
			}
		}
	}

}
