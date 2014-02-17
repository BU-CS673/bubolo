package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.entity.Modifier;


/**
 * Roads are Modifiers for Terrain that allow Tanks to drive more quickly. They can be
 * created and destroyed by Tanks.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Road extends Modifier
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
