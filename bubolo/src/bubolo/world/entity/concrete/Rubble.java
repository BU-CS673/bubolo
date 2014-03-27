package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.entity.StationaryElement;

/**
 * Rubble is created when structures (like Walls) are destroyed. It replaces whatever
 * Terrain type was previously present, and reduces tank movement speed.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Rubble extends StationaryElement
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = 6510667006657276377L;

	/**
	 * Construct a new Rubble with a random UUID.
	 */
	public Rubble()
	{
		super();
	}

	/**
	 * Construct a new Rubble with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Grass.
	 */
	public Rubble(UUID id)
	{
		super(id);
	}

	// TODO: Add Rubble functionality!
}
