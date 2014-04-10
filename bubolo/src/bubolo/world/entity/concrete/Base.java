package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.Ownable;
import bubolo.world.entity.StationaryElement;

/**
 * Bases allow Tanks to heal and recover their mines, and capturing them is the primary
 * goal of the game.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Base extends StationaryElement implements Ownable
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = 7700096718365746352L;

	/**
	 * Boolean representing whether this Base belongs to the local player.
	 */
	private boolean isLocalPlayer = true;

	/**
	 * Boolean representing whether this Base is owned by a player.
	 */
	private boolean isOwned = true;

	/**
	 * Boolean representing whether this Base is currently charging a Tank.
	 * 
	 */
	private boolean isCharging = false;

	/**
	 * Construct a new Base with a random UUID.
	 */
	public Base()
	{
		this(UUID.randomUUID());
	}

	/**
	 * Construct a new Base with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tree.
	 */
	public Base(UUID id)
	{
		super(id);
		setWidth(32);
		setHeight(32);
		updateBounds();
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

	/**
	 * Checks whether or not this base is currently charging a tank.
	 * 
	 * @return the current charging status of this base.
	 */
	public boolean isCharging()
	{
		return isCharging;
	}

	/**
	 * Sets the charging state of this base.
	 * 
	 * @param charge
	 *            represents whether or not this base should be in a charging state. False
	 *            = not charging!
	 */
	public void setCharging(boolean charge)
	{
		isCharging = charge;
	}
}
