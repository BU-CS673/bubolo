package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.entity.StationaryEntity;

/**
 * spawn is an entity that marks a tile for tanks to re spawn at after dying
 * 
 * @author BU CS673 - Clone Productions
 */
public class Spawn extends StationaryEntity
{
	/**
	 * needed for serialization
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construct a new Spawn with a random UUID.
	 */
	public Spawn()
	{
		this(UUID.randomUUID());
	}

	/**
	 * Construct a new Spawn with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Spawn.
	 */
	public Spawn(UUID id)
	{
		super(id);
		setWidth(32);
		setHeight(32);
		updateBounds();
	}
}
