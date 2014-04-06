package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.entity.Terrain;

/**
 * Swamp Terrain can be traversed by Tanks, but will reduce movement speed.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Swamp extends Terrain
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = -4507866752457694356L;
	
	/**
	 * Modifier field used to reset an objects cap speed while traversing this terrain type.
	 */
	private static float maxSpeedModifier = 0.75F;
	
	/**
	 * Construct a new Swamp with a random UUID.
	 */
	public Swamp()
	{
		this(UUID.randomUUID());
	}

	/**
	 * Construct a new Swamp with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Grass.
	 */
	public Swamp(UUID id)
	{
		super(id);
		setWidth(32);
		setHeight(32);
		updateBounds();
	}

	/**
	 * Returns a percentage factor for capping an objects speed while travesing this terrain 
	 * @return maxSpeedModifier
	 */
	@Override
	public float getMaxSpeedModifier() {
		return maxSpeedModifier;
	}
	// TODO: Add Swamp functionality!
}
