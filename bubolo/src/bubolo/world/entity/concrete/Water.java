package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.entity.Terrain;

/**
 * Water terrain can be crossed by a Tank, but can deal damage over time.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Water extends Terrain
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = 7556626906247082191L;

	/**
	 * Construct a new Water with a random UUID.
	 */
	public Water()
	{
		super();
	}

	/**
	 * Construct a new Water with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Grass.
	 */
	public Water(UUID id)
	{
		super(id);
	}

	// TODO: Add Water functionality!
}
