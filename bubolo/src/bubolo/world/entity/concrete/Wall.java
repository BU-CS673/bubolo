package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.entity.StationaryElement;

/**
 * Walls are intended to impede Tank movement, and create Rubble Terrain when destroyed.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Wall extends StationaryElement
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = -4591161497141031916L;

	/**
	 * Construct a new Wall with a random UUID.
	 */
	public Wall()
	{
		super();
	}

	/**
	 * Construct a new Wall with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tree.
	 */
	public Wall(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}

	// TODO: Add Wall functionality!
}
