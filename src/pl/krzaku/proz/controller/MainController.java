package pl.krzaku.proz.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProviderListener;

import pl.krzaku.proz.model.GameState;
import pl.krzaku.proz.view.View;

/**
 * Main controller class which interpretates player input
 * @author Patryk Majkrzak
 * @version 1.1
 */
public class MainController implements InputProviderListener
{
	///Input providing class
	private PlayerInput playerInput;
	///Game state to control
	private GameState gameState;
	///Game container from which input is being get
	private GameContainer gameContainer;
	///Game view (currently unused)
	private View view;
	
	/**
	 * Constructor which initializes whole game controller part
	 * @param gameContainer game container to get input from
	 * @param gameState game state to control
	 * @param view game view to control
	 */
	public MainController(GameContainer gameContainer, GameState gameState, View view)
	{
		this.gameContainer = gameContainer;
		this.playerInput = new PlayerInput(gameContainer, this);
		this.gameState = gameState;
		this.view = view;		
	}
	
	/**
	 * Invoked when player presses button which is binded to some command
	 */
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

	/**
	 * Invoked when player releases button binded to some command
	 */
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
