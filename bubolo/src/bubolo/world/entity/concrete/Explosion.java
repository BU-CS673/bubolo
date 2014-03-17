package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.entity.Actor;

/**
 * Explosion Animation of B.U.B.O.L.O.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Explosion extends Actor
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = 42424242L;

	/**
	 * Construct a new Explosion with a random UUID.
	 */
	public Explosion()
	{
		super();
	}

	/**
	 * Construct a new Explosion with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Explosion.
	 */
	public Explosion(UUID id)
	{
		super(id);
	}

	// TODO: Add Explosion functionality! (is there any?)
}
