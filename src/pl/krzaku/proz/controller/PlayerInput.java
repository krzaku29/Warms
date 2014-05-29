package pl.krzaku.proz.controller;

import org.newdawn.slick.AppGameContainer;
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
	private Command towerLeftRotate = new BasicCommand("leftRotate");
	///Command for rotating active tower clockwise
	private Command towerRightRotate = new BasicCommand("rightRotate");
	///Command for increasing active tower shoot power
	private Command towerMorePower = new BasicCommand("morePower");
	///Command for decreasing active tower shoot power
	private Command towerLessPower = new BasicCommand("lessPower");
	///Command for shooting from active tower
	private Command towerShoot = new BasicCommand("shoot");
	///Command for switching to the next tower
	private Command nextTower = new BasicCommand("nextTower");
	///Command for exiting game
	private Command exitGame = new BasicCommand("exitGame");
	
	
	/**
	 * Constructor which adds main controller as listener
	 * @param gameContainer game container to get input from
	 * @param mainController main controller which listens for commands
	 */
	public PlayerInput(AppGameContainer gameContainer, MainController mainController)
	{
		input = new InputProvider(gameContainer.getInput());
		input.addListener(mainController);

		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyLeftRotate()), towerLeftRotate);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyRightRotate()), towerRightRotate);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyMorePower()), towerMorePower);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyLessPower()), towerLessPower);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyShoot()), towerShoot);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyNextTower()), nextTower);
		input.bindCommand(new KeyControl(KeyBinding.getInstance().getKeyExit()), exitGame);
	}

}
