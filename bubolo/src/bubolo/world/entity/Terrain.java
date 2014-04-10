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

	/**
	 * Construct a new Terrain with a random UUID.
	 */
	public Terrain()
	{
		this(UUID.randomUUID());
	}

	/**
	 * Construct a new Terrain with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Terrain.
	 */
	public Terrain(UUID id)
	{
		super(id);
	}
}
