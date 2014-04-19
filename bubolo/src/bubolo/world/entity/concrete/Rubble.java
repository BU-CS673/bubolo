package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.entity.Terrain;

/**
 * Rubble is created when structures (like Walls) are destroyed. It replaces whatever Terrain type
 * was previously present, and reduces tank movement speed.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Rubble extends Terrain
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = 6510667006657276377L;

	/**
	 * Modifier field used to reset an objects cap speed while traversing this terrain type.
	 */
	private static final float MAX_SPEED_MODIFIER = 0.4F;
	
	/**
	 * Construct a new Rubble with a random UUID.
	 */
	public Rubble()
	{
		this(UUID.randomUUID());
	}

	/**
	 * Construct a new Rubble with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Grass.
	 */
	public Rubble(UUID id)
	{
		super(id, MAX_SPEED_MODIFIER);
		setWidth(32);
		setHeight(32);
		updateBounds();
	}
}
