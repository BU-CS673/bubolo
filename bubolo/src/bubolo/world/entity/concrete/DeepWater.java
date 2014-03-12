package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.entity.Terrain;

/**
 * Deep water is intended to act as a barrier to prevent Tanks from leaving the map.
 * 
 * @author BU CS673 - Clone Productions
 */
public class DeepWater extends Terrain
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = -4427335167013500776L;

	/**
	 * Construct a new DeepWater with a random UUID.
	 */
	public DeepWater()
	{
		super();
	}

	/**
	 * Construct a new DeepWater with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Grass.
	 */
	public DeepWater(UUID id)
	{
		super(id);
	}

	// TODO: Add DeepWater functionality!
}
