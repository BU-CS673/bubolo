package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.audio.Audio;
import bubolo.audio.Sfx;
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
	 * the UID of the Tank that owns this Mine
	 */
	private UUID ownerUID;
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
	 *  amount of time before mine becomes active in milliseconds
	 */
	private static int FUSE_TIME = 5000;
	
	/**
	 * time the mine was created in milliseconds 
	 */
	private long createdTime;
	
	/**
	 * Construct a new Mine with a random UUID.
	 */
	public Mine()
	{
		this(UUID.randomUUID());
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
		setWidth(25);
		setHeight(25);
		this.createdTime = System.currentTimeMillis();
		setLocalPlayer(true);
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
	 * Checks to see if this mine is currently exploding!
	 * 
	 * @return true if this mine is in the process of exploding, false otherwise.
	 */
	public boolean isExploding()
	{
		return isExploding;
	}

	/**
	 * Sets the explosion status of this Mine.
	 * 
	 * @param explode
	 *            should be true if this mine should be exploding, false otherwise.
	 */
	public void setExploding(boolean explode)
	{
		this.isExploding = explode;
	}
	
	/**
	 * get the status of this mine. will be inactive until the fuse time has elapsed since the mine was created
	 * @return 
	 * 		whether or not this mine is active
	 */
	public boolean isActive()
	{
		boolean active = false;
		if ((this.createdTime+this.FUSE_TIME) < System.currentTimeMillis())
		{
			active = true;
		}
		return active;
	}

	@Override
	public UUID getOwnerUID() 
	{
		return this.ownerUID;
	}

	@Override
	public void setOwnerUID(UUID ownerUID) 
	{
		this.ownerUID = ownerUID;
	}
	
	@Override
	protected void onDispose()
	{
		Audio.play(Sfx.MINE_EXPLOSION);
	}
}
