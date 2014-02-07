package bubolo.world;

import java.io.Serializable;

/**
 * Base class for game objects. Anything that is part of the game logic should
 * inherit from this class.
 * @author BU673 - Clone Industries
 */
public abstract class Entity implements Serializable
{
	// Used when serializing and deserializing.
	private static final long serialVersionUID = -7558368147503376322L;

	
}
