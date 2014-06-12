package pl.krzaku.proz.view;

/**
 * Interface for objects that are able to be rendered in game window
 * @author Patryk Majkrzak
 * @version 1.1
 */
public interface Renderable
{
	///Get ID of object's sprite
	public SpriteID getSpriteID();
	///Get sprite number on sprite sheet
	public int getSpriteNumber();
	///Get X position to render
	public float getRenderPositionX();
	///Get Y position to render
	public float getRenderPositionY();
	///Get if the sprite if flipped horizontally
	public boolean isFlipped();
}
