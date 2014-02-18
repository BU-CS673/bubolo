package bubolo.world.entity;

import java.util.UUID;

/**
 * Basic class for DrawableEntities that do not move (i.e., those that are fixed a single
 * location on the map grid).
 * 
 * @author BU CS673 - Clone Productions
 */
public abstract class StationaryEntity extends Entity
{

	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = 7748173162492885868L;

	/**
	 * Construct a new StationaryEntity with a random UUID.
	 */
	public StationaryEntity()
	{
		super();
	}

	/**
	 * Construct a new StationaryEntity with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new StationaryEntity.
	 */
	public StationaryEntity(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}

	// TODO: Add StationaryEntity functionality!
}
