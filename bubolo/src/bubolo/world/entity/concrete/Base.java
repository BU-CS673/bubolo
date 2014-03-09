package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.entity.StationaryElement;

/**
 * Bases allow Tanks to heal and recover their mines, and capturing them is the primary
 * goal of the game.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Base extends StationaryElement
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = 7700096718365746352L;

	/**
	 * Construct a new Base with a random UUID.
	 */
	public Base()
	{
		super();
	}

	/**
	 * Construct a new Base with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tree.
	 */
	public Base(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}

	// TODO: Add Base functionality!
}
