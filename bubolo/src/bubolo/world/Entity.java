package bubolo.world;

import java.io.Serializable;
import java.util.UUID;

import bubolo.graphics.DrawLayer;
import bubolo.graphics.Sprite;

/**
 * Base class for game objects. Anything that is part of the game logic should inherit
 * from this class.
 * 
 * @author BU673 - Clone Industries
 */
public abstract class Entity implements Serializable
{
	private UUID myID;

	// Used when serializing and deserializing.
	private static final long serialVersionUID = -7558368147503376322L;

	/**
	 * Creates a new Entity with a randomly generated UUID.
	 */
	public Entity()
	{
		myID = UUID.randomUUID();
	}

	/**
	 * Creates an Entity with the specified UUID.
	 * 
	 * @param newID
	 *            is a pre-generated UUID.
	 */
	public Entity(UUID newID)
	{
		myID = newID;
	}

	/**
	 * Get the UUID of this Entity.
	 * 
	 * @return the UUID object associated with this Entity.
	 */
	public UUID getID()
	{
		return myID;
	}

}
