package bubolo.world.entity;

import java.util.UUID;

/**
 * Terrain objects represent the base of the game world at a given grid position. A grid location
 * must contain one Terrain object.
 * 
 * @author BU CS673 - Clone Productions
 */
public abstract class Terrain extends StationaryEntity
{

	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = 6373697443259757484L;

	private final float maxSpeedModifier;

	/**
	 * Construct a new Terrain with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Terrain.
	 * @param maxSpeedModifier
	 *            the percentage factor for capping an objects speed while traversing this terrain.
	 */
	public Terrain(UUID id, float maxSpeedModifier)
	{
		super(id);
		this.maxSpeedModifier = maxSpeedModifier;
	}

	/**
	 * Returns the percentage factor for capping an objects speed while traversing this terrain.
	 * 
	 * @return maxSpeedModifier the percentage factor for capping an objects speed while traversing
	 *         this terrain.
	 */
	public final float getMaxSpeedModifier()
	{
		return maxSpeedModifier;
	}
}
