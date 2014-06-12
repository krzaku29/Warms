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
	private final Command towerLeftRotateCommand1 = new BasicCommand("leftRotate1");
	///Command for rotating active tower clockwise
	private final Command towerRightRotateCommand1 = new BasicCommand("rightRotate1");
	///Command for increasing active tower shoot power
	private final Command towerMorePowerCommand1 = new BasicCommand("morePower1");
	///Command for decreasing active tower shoot power
	private final Command towerLessPowerCommand1 = new BasicCommand("lessPower1");
	///Command for shooting from active tower
	private final Command towerShootCommand1 = new BasicCommand("shoot1");
	///Command for switching to the next tower
	private final Command nextTowerCommand1 = new BasicCommand("nextTower1");
	///Command for switching to the previous tower
	private final Command prevTowerCommand1 = new BasicCommand("prevTower1");
	
	///Command for rotating active tower counterclockwise
	private final Command towerLeftRotateCommand2 = new BasicCommand("leftRotate2");
	///Command for rotating active tower clockwise
	private final Command towerRightRotateCommand2 = new BasicCommand("rightRotate2");
	///Command for increasing active tower shoot power
	private final Command towerMorePowerCommand2 = new BasicCommand("morePower2");
	///Command for decreasing active tower shoot power
	private final Command towerLessPowerCommand2 = new BasicCommand("lessPower2");
	///Command for shooting from active tower
	private final Command towerShootCommand2 = new BasicCommand("shoot2");
	///Command for switching to the next tower
	private final Command nextTowerCommand2 = new BasicCommand("nextTower2");
	///Command for switching to the previous tower
	private final Command prevTowerCommand2 = new BasicCommand("prevTower2");
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

		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyLeftRotate(0)), towerLeftRotateCommand1);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyRightRotate(0)), towerRightRotateCommand1);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyMorePower(0)), towerMorePowerCommand1);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyLessPower(0)), towerLessPowerCommand1);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyShoot(0)), towerShootCommand1);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyNextTower(0)), nextTowerCommand1);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyPrevTower(0)), prevTowerCommand1);
		
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyLeftRotate(1)), towerLeftRotateCommand2);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyRightRotate(1)), towerRightRotateCommand2);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyMorePower(1)), towerMorePowerCommand2);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyLessPower(1)), towerLessPowerCommand2);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyShoot(1)), towerShootCommand2);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyNextTower(1)), nextTowerCommand2);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyPrevTower(1)), prevTowerCommand2);
		
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyExit()), exitGameCommand);
	}


	public Command getTowerLeftRotateCommand(int playerNumber)
	{
		if(playerNumber == 0) return towerLeftRotateCommand1;
		return towerLeftRotateCommand2;
	}


	public Command getTowerRightRotateCommand(int playerNumber)
	{
		if(playerNumber == 0) return towerRightRotateCommand1;
		return towerRightRotateCommand2;
	}


	public Command getTowerMorePowerCommand(int playerNumber)
	{
		if(playerNumber == 0) return towerMorePowerCommand1;
		return towerMorePowerCommand2;
	}


	public Command getTowerLessPowerCommand(int playerNumber)
	{
		if(playerNumber == 0) return towerLessPowerCommand1;
		return towerLessPowerCommand2;
	}


	public Command getTowerShootCommand(int playerNumber)
	{
		if(playerNumber == 0) return towerShootCommand1;
		return towerShootCommand2;
	}


	public Command getNextTowerCommand(int playerNumber)
	{
		if(playerNumber == 0) return nextTowerCommand1;
		return nextTowerCommand2;
	}
	
	public Command getPrevTowerCommand(int playerNumber)
	{
		if(playerNumber == 0) return prevTowerCommand1;
		return prevTowerCommand2;
	}


	public Command getExitGameCommand()
	{
		return exitGameCommand;
	}
	
	

}
