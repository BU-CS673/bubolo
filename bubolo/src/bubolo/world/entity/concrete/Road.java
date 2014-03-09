package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.entity.Terrain;


/**
 * Road is a Terrain type that offers tanks improved movement speed. 
 * 
 * @author BU CS673 - Clone Productions
 */
public class Road extends Terrain
{
	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = -5302600252810938564L;

	/**
	 * Construct a new Road with a random UUID.
	 */
	public Road()
	{
		super();
	}

	/**
	 * Construct a new Road with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Road.
	 */
	public Road(UUID id)
	{
		super(id);
	}

	// TODO: Add Road functionality!
}
