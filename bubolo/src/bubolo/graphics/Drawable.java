package bubolo.graphics;

/**
 * Drawable objects contain the information necessary to allow them to be drawn by the Graphics system.
 * @author BU CS673 - Clone Productions
 */
public interface Drawable
{
	/**
	 * Get the x position of this Entity.
	 * 
	 * @return the entity's x position in world coordinates.
	 */
	public float getX();

	/**
	 * Get the y position of this Entity.
	 * 
	 * @return the entity's y position in world coordinates.
	 */
	public float getY();

	/**
	 * Get the width of this Entity.
	 * 
	 * @return the height of this Entity in world coordinates.
	 */
	public int getWidth();

	/**
	 * Get the height of this Entity.
	 * 
	 * @return the height of this Entity in world coordinates.
	 */
	public int getHeight();

	/**
	 * Get the current rotation of this Entity.
	 * 
	 * @return the rotation of this Entity in radians.
	 */
	public float getRotation();
}
