package pl.krzaku.proz.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.KeyControl;


/**
 * Class for managing player input 
 * @author Patryk Majkrzak
 *
 */
public class PlayerInput
{
	///Input provider for listener
	private InputProvider input;
	///Command for rotating active tower counterclockwise
	private final Command towerLeftRotateCommand = new BasicCommand("leftRotate");
	///Command for rotating active tower clockwise
	private final Command towerRightRotateCommand = new BasicCommand("rightRotate");
	///Command for increasing active tower shoot power
	private final Command towerMorePowerCommand = new BasicCommand("morePower");
	///Command for decreasing active tower shoot power
	private final Command towerLessPowerCommand = new BasicCommand("lessPower");
	///Command for shooting from active tower
	private final Command towerShootCommand = new BasicCommand("shoot");
	///Command for switching to the next tower
	private final Command nextTowerCommand = new BasicCommand("nextTower");
	///Command for exiting game
	private final Command exitGameCommand = new BasicCommand("exitGame");
	
	
	/**
	 * Constructor which adds main controller as listener
	 * @param gameContainer game container to get input from
	 * @param mainController main controller which listens for commands
	 */
	public PlayerInput(GameContainer gameContainer, MainController mainController)
	{
		input = new InputProvider(gameContainer.getInput());
		input.addListener(mainController);

		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyLeftRotate()), towerLeftRotateCommand);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyRightRotate()), towerRightRotateCommand);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyMorePower()), towerMorePowerCommand);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyLessPower()), towerLessPowerCommand);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyShoot()), towerShootCommand);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyNextTower()), nextTowerCommand);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyExit()), exitGameCommand);
	}


	public Command getTowerLeftRotateCommand()
	{
		return towerLeftRotateCommand;
	}


	public Command getTowerRightRotateCommand()
	{
		return towerRightRotateCommand;
	}


	public Command getTowerMorePowerCommand()
	{
		return towerMorePowerCommand;
	}


	public Command getTowerLessPowerCommand()
	{
		return towerLessPowerCommand;
	}


	public Command getTowerShootCommand()
	{
		return towerShootCommand;
	}


	public Command getNextTowerCommand()
	{
		return nextTowerCommand;
	}


	public Command getExitGameCommand()
	{
		return exitGameCommand;
	}
	
	

}
