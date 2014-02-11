package bubolo.graphics;

import java.util.UUID;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Drawable
{
	
	/**
	 * Get the UUID of this Entity.
	 * 
	 * @return the UUID object associated with this Entity.
	 */
	public UUID getId();

	
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

	
	/**
	 * Draw this Entity by passing the draw method to its associated Sprite.
	 * If there is no associated Sprite, do nothing.
	 * @param batch is the SpriteBatch that this Entity should be drawn in.
	 * @param camera is the camera from which this Entity should be seen.
	 * @param layer is the DrawLayer on which this Entity should be drawn, used to establish draw order. 
	 */
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer);
	
	/**
	 * Get the UUID of this Entity's Sprite object, if it exists.
	 * 
	 * @return the UUID of this Entity's Sprite object, or null if this Entity does not
	 *         have an associated Sprite.
	 */
	public UUID getSpriteId();
	
}
