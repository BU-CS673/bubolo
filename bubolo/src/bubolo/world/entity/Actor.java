package bubolo.world.entity;

import java.util.UUID;

import bubolo.world.Ownable;

/**
 * Basic class representing MobileEntities that exhibit some kind of behavior in the game
 * world, such as tanks and humans.
 * 
 * @author BU CS673 - Clone Productions
 */
public abstract class Actor extends Entity implements Ownable
{

	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = 6062132322107891442L;

	/**
	 * Boolean representing whether this Actor belongs to the local player.
	 */
	private boolean isLocalPlayer;

	/**
	 * Boolean representing whether this Actor is owned by a player.
	 */
	private boolean isOwned = true;

	/**
	 * Construct a new Actor with a random UUID.
	 */
	public Actor()
	{
		this(UUID.randomUUID());
	}

	/**
	 * Construct a new Actor with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Actor.
	 */
	public Actor(UUID id)
	{
		super(id);
	}

	@Override
	public boolean isLocalPlayer()
	{
		return isLocalPlayer;
	}

	@Override
	public void setLocalPlayer(boolean local)
	{
		this.isLocalPlayer = local;
	}

	@Override
	public boolean isOwned()
	{
		return isOwned;
	}

	@Override
	public void setOwned(boolean owned)
	{
		this.isOwned = owned;
	}
}
