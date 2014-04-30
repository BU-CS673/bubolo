package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.net.Network;
import bubolo.net.NetworkSystem;
import bubolo.net.command.UpdateOwnable;
import bubolo.world.Damageable;
import bubolo.world.Ownable;
import bubolo.world.entity.StationaryElement;

/**
 * Bases allow Tanks to heal and recover their mines, and capturing them is the primary
 * goal of the game.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Base extends StationaryElement implements Ownable, Damageable
{
	/**
	 * the uid of the tank that owns this Base
	 */
	private UUID ownerUID;
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = 7700096718365746352L;

	/**
	 * Boolean representing whether this Base belongs to the local player.
	 */
	private boolean isLocalPlayer = false;

	/**
	 * Boolean representing whether this Base is owned by a player.
	 */
	private boolean isOwned = false;

	/**
	 * Boolean representing whether this Base is currently charging a Tank.
	 * 
	 */
	private boolean isCharging = false;

	/**
	 * The current number of hit points of the base.
	 */
	private int hitPoints;
	
	private static final int MAX_HIT_POINTS = 100;
	
	private static final int HIT_POINTS_REPLENISH_RATE = 5;
	
	private int mineCount;
	
	private static final int MAX_MINE_COUNT = 10;
	
	private static final int MINE_REPLENISH_RATE = 1;
	
	private int ammoCount;

	private static final int MAX_AMMO_COUNT = 100;
	
	private static final int AMMO_REPLENISH_RATE = 5;
	
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
		hitPoints = MAX_HIT_POINTS;
		ammoCount = MAX_AMMO_COUNT;
		mineCount = MAX_MINE_COUNT;
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

	/**
	 * Returns the current health of the base
	 * 
	 * @return current hit point count
	 */
	@Override
	public int getHitPoints() 
	{
		return hitPoints;
	}

	/**
	 * Method that returns the maximum number of hit points the entity can have. 
	 * @return - Max Hit points for the entity
	 */
	@Override
	public int getMaxHitPoints() 
	{
		return MAX_HIT_POINTS;
	}
	
	/**
	 * Changes the hit point count after taking damage
	 * 
	 * @param damagePoints
	 *            how much damage the base has taken
	 */
	@Override
	public void takeHit(int damagePoints) 
	{
		hitPoints -= Math.abs(damagePoints);
		if (this.hitPoints <= 0)
		{
			if (this.isLocalPlayer())
			{
				this.setLocalPlayer(false);
				this.setOwned(false);
				this.ownerUID = null;
				Network net = NetworkSystem.getInstance();
				net.send(new UpdateOwnable(this));
			}
		}
	}

	/**
	 * Increments the base's health by a given amount
	 * 
	 * @param healPoints - how many points the base is given
	 */
	@Override
	public void heal(int healPoints) 
	{
		if (hitPoints + Math.abs(healPoints) < MAX_HIT_POINTS)
		{
			hitPoints += Math.abs(healPoints);
		}

		else
		{
			hitPoints = MAX_HIT_POINTS;
		}		
	}
	
	/**
	 * The current amount of ammo at the base
	 * @return the current amount of ammo
	 */
	public int getAmmoCount() 
	{
		return ammoCount;
	}
	

	/**
	 * The maximum amount of ammo a base can have
	 * @return the maximum amount of ammo storage at a base
	 */
	public static int getMaxAmmoCount() 
	{
		return MAX_AMMO_COUNT;
	}

	/**
	 * Replenishes the ammo for the base
	 */
	public void gatherAmmo()
	{
		if(ammoCount + AMMO_REPLENISH_RATE < MAX_AMMO_COUNT)
		{
			ammoCount += AMMO_REPLENISH_RATE;
		}
		else 
		{
			ammoCount = MAX_AMMO_COUNT;
		}
	}
	
	/**
	 * Method that deducts ammo from supply to give to tank
	 * @return - amount of ammo capable of being supplied at request
	 */
	public int giveAmmo()
	{
		if(ammoCount - AMMO_REPLENISH_RATE < 0)
		{
			int ammoGiven = ammoCount;
			ammoCount = 0;
			return ammoGiven;
		}
		else 
		{
			ammoCount -= AMMO_REPLENISH_RATE;
			return AMMO_REPLENISH_RATE;
		}
	}
	
	/**
	 * The current number of mines at a base
	 * @return the current number of mines
	 */
	public int getMineCount() 
	{
		return mineCount;
	}
	
	/**
	 * The maximum number of mines a base can have
	 * @return the maximum storage for mines at a base
	 */
	public static int getMaxMineCount() 
	{
		return MAX_MINE_COUNT;
	}
	
	/**
	 * Replenishes the mines for the base
	 */
	public void gatherMines()
	{
		if(mineCount + MINE_REPLENISH_RATE < MAX_MINE_COUNT)
		{
			mineCount += MINE_REPLENISH_RATE;
		}
		else
		{
			mineCount = MAX_MINE_COUNT;
		}
	}
	
	/**
	 * Method that deducts mines from supply to give to tank
	 * @return - amount of mines capable of being supplied at request
	 */
	public int giveMine()
	{
		if(mineCount - MINE_REPLENISH_RATE < 0)
		{
			int minesGiven = mineCount;
			mineCount = 0;
			return minesGiven;
		}
		else 
		{
			mineCount -= MINE_REPLENISH_RATE;
			return MINE_REPLENISH_RATE;
		}
	}

	
	/**
	 * Donates hit points to an object to heal
	 * @return the amount of hit points to give
	 */
	public int giveHitPoints()
	{
		if(hitPoints - HIT_POINTS_REPLENISH_RATE < 0)
		{
			int hitPointsGiven = hitPoints;
			hitPoints = 0;
			return hitPointsGiven;
		}
		else
		{
			hitPoints -= HIT_POINTS_REPLENISH_RATE;
			return HIT_POINTS_REPLENISH_RATE;
		}
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

}
