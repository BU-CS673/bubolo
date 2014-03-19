package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.World;
import bubolo.world.entity.Actor;

/**
 * The Man represents the driver of a Tank. The Man can take actions outside of the Tank,
 * and if killed, the player must wait for a new Man to spawn before taking any further
 * Tank actions.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Engineer extends Actor
{
	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = -2524255979041335716L;

	/**
	 * Construct a new Man with a random UUID.
	 */
	public Engineer()
	{
		super();
	}

	/**
	 * Construct a new Man with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Man.
	 */
	public Engineer(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(World world)
	{
		// TODO Implement this.
	}

	// TODO: Add Man functionality!
}
