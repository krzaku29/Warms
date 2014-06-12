package pl.krzaku.proz.view;

/**
 * Interface for object that can be marked by marker and use crosshair
 * @author pmajk_000
 *
 */
public interface Markable
{
	///Get center of object X position
	public double getCenterXPosition();
	///Get center of object Y position
	public double getCenterYPosition();
	///Get marker Y offset from center
	public double getMarkerYOffset();
	///Get crosshair X offset from center
	public double getCrosshairXOffset();
	///Get crosshair Y offset from center
	public double getCrosshairYOffset();
}
