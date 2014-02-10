package bubolo.world;

import java.io.Serializable;

import org.apache.commons.lang3.NotImplementedException;

/**
 * Base class for game objects. Anything that is part of the game logic should
 * inherit from this class.
 * @author BU673 - Clone Industries
 */
public abstract class Entity implements Serializable
{
	// Used when serializing and deserializing.
	private static final long serialVersionUID = -7558368147503376322L;

	/**
	 * Gets the entity's x position.
	 * @return the entity's x position.
	 */
	public float getX()
	{
		throw new NotImplementedException("Entity.getX() is not yet implemented.");
	}
	
	/**
	 * Gets the entity's y position.
	 * @return the entity's y position.
	 */
	public float getY()
	{
		throw new NotImplementedException("Entity.getY() is not yet implemented.");
	}
}
