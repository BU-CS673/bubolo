package bubolo.world.entity;

import java.util.UUID;

import bubolo.world.World;

/**
 * Basic class representing effects that are generally drawn overtop of other Entities.
 * Includes things like bullets and explosions, which can move, cause damage, and trigger
 * collisions, but are not controlled by a player and are not Damageable.
 * 
 * @author BU CS673 - Clone Productions
 */
public abstract class Effect extends Entity
{

	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = 4414632253389640152L;

	/**
	 * Construct a new Effect with a random UUID.
	 */
	public Effect()
	{
		this(UUID.randomUUID());
	}

	/**
	 * Construct a new Effect with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Actor.
	 */
	public Effect(UUID id)
	{
		super(id);
	}
	
	@Override
	public void update(World world)
	{
		// TODO Establish updating logic for Effects.
	}
}
