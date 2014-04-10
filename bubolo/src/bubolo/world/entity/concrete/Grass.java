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
	
	/**
	 * Modifier field used to reset an objects cap speed while traversing this terrain type.
	 */
	private static float maxSpeedModifier = 1.0F;
	
	/**
	 * Construct a new Grass with a random UUID.
	 */
	public Grass()
	{
		this(UUID.randomUUID());
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
		setWidth(32);
		setHeight(32);
		updateBounds();
	}

	/**
	 * Returns a percentage factor for capping an objects speed while traversing this terrain 
	 * @return maxSpeedModifier
	 */
	@Override
	public float getMaxSpeedModifier() 
	{
		return maxSpeedModifier;
	}

	// TODO: Add Grass functionality!
}
