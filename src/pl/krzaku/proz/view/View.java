package pl.krzaku.proz.view;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import pl.krzaku.proz.model.GameState;

public class View
{
	private ContentManager content;
	private GameState gameState;
	private float xScale;
	private float yScale;

	public View(GameState gState, int baseResolutionX, int baseResolutionY, int targetResolutionX, int targetResolutionY)
	{
		content = new ContentManager();
		gameState = gState;

		xScale = (float)targetResolutionX/baseResolutionX;
		yScale = (float)targetResolutionY/baseResolutionY;		
	}
	
	public void draw(Graphics graphics)
	{
		Layout layout = gameState.getLayout();
		Image mapImage = gameState.getMapImage();
		
		graphics.scale(xScale, yScale);
		graphics.drawImage(mapImage, 0, 0);
		
	}
}
