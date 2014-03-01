package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.entity.Terrain;

/**
 * Grass is the standard Terrain of B.U.B.O.L.O., and offers no special movement effects.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Grass extends Terrain
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = 5319713357245800006L;

	/**
	 * Construct a new Grass with a random UUID.
	 */
	public Grass()
	{
		super();
	}

	/**
	 * Construct a new Grass with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Grass.
	 */
	public Grass(UUID id)
	{
		super(id);
	}

	// TODO: Add Grass functionality!
}
