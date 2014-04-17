package bubolo.world.entity;

import java.util.UUID;

/**
 * A StationaryElement is a StationaryEntity that can take Damage and take actions in the
 * game world.
 * 
 * @author BU CS673 - Clone Productions
 */
public abstract class StationaryElement extends StationaryEntity
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = -1849311149500334067L;

	/**
	 * Construct a new StationaryElement with a random UUID.
	 */
	public StationaryElement()
	{
		this(UUID.randomUUID());
	}

	/**
	 * Construct a new StationaryElement with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new StationaryElement.
	 */
	public StationaryElement(UUID id)
	{
		super(id);
	}
}
