package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.Ownable;
import bubolo.world.entity.StationaryElement;

/**
 * Mines can be placed by Tanks to do damage to enemy Tanks, or to destroy/modify
 * Terrain/structures.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Mine extends StationaryElement implements Ownable
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = -4956203172414751370L;

	/**
	 * Boolean representing whether this Mine belongs to the local player.
	 */
	private boolean isLocalPlayer = true;

	/**
	 * Boolean representing whether this Mine is owned by a player.
	 */
	private boolean isOwned = false;
	
	/**
	 * Boolean representing whether this Mine is exploding! OH NO!
	 */
	private boolean isExploding = false;

	/**
	 * Construct a new Mine with a random UUID.
	 */
	public Mine()
	{
		super();
	}

	/**
	 * Construct a new Mine with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tree.
	 */
	public Mine(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
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
	 * Checks to see if this mine is currently exploding!
	 * @return true if this mine is in the process of exploding, false otherwise.
	 */
	public boolean isExploding()
	{
		return isExploding;
	}

	/**
	 * Sets the explosion status of this Mine.
	 * @param explode should be true if this mine should be exploding, false otherwise.
	 */
	public void setExploding(boolean explode)
	{
		this.isExploding = explode;
	}

	// TODO: Add Mine functionality!
}
