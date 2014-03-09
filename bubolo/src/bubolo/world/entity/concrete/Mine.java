package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.entity.StationaryElement;

/**
 * Mines can be placed by Tanks to do damage to enemy Tanks, or to destroy/modify
 * Terrain/structures.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Mine extends StationaryElement
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = -4956203172414751370L;

	/**
	 * Construct a new Mine with a random UUID.
	 */
	public Mine()
	{
		super();
	}

	/**
	 * Construct a new Mine with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tree.
	 */
	public Mine(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}

	// TODO: Add Mine functionality!
}
