package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.Ownable;
import bubolo.world.entity.StationaryElement;

/**
 * Pillboxes are stationary defensive structures that can be placed by a Tank. They shoot
 * at an enemy Tank until destroyed, at which point they can be retrieved and used again.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Pillbox extends StationaryElement implements Ownable
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = 278726024001386941L;

	/**
	 * Boolean representing whether this Pillbox belongs to the local player.
	 */
	private boolean isLocalPlayer = true;

	/**
	 * Boolean representing whether this Pillbox is owned by a player.
	 */
	private boolean isOwned = true;

	/**
	 * Boolean representing whether this Pillbox is alive.
	 */
	private boolean isAlive = super.isAlive();
	
	/**
	 * Construct a new Pillbox with a random UUID.
	 */
	public Pillbox()
	{
		super();
	}

	/**
	 * Construct a new Pillbox with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tree.
	 */
	public Pillbox(UUID id)
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
	
	// TODO: Add Pillbox functionality!
	/**
	 * Call this method when a tank's trying to capture or re-build a pillbox 
	 * (move to the same spot with pillbox?)
	 * 
	 * Capturable Pillboxes -- Destroyed Pillbox can be picked up, re-build and will be
	 * allied with that player (will not fire at that player)
	 */
	public void capturePillbox()
	{
		if (isAlive == false)
		{
			// pillbox is dead, can be pick up by anyone and need to re-build
			setOwned(true);
			setLocalPlayer(true);
			super.setHP(getMaxHP());
		}
		else if (isOwned)
		{
			// pillbox is alive, but it's owned by someone, can be re-build by owner
			if (isLocalPlayer)
			{
				super.setHP(getMaxHP());
			}
		}
		else
		{
			// pillbox is alive, and it's not owned by anyone, can be pick up
			setOwned(true);
			setLocalPlayer(true);
		}
	}
}