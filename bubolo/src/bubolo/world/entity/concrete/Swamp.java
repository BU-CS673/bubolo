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
	private static final float MAX_SPEED_MODIFIER = 0.75F;
	
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
		super(id, MAX_SPEED_MODIFIER);
		setWidth(32);
		setHeight(32);
		updateBounds();
	}
}
