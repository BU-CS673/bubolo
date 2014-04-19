package bubolo.world.entity.concrete;

import java.util.List;
import java.util.UUID;

import bubolo.util.TileUtil;
import bubolo.world.Damageable;
import bubolo.world.Ownable;
import bubolo.world.World;
import bubolo.world.entity.Entity;
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
	 * The current number of hit points of the base.
	 */
	private int hitPoints;
	
	private static final int MAX_HIT_POINTS = 100;
	
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
		// TODO: This method is the first opportunity to set off "death" chain of events
		
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
	
	@Override
	public void update(World world)
	{
		if (this.hitPoints <= 0)
		{
			this.setOwned(false);
			List<Entity> entities = TileUtil.getLocalCollisions(this, world);
			for(Entity entity:entities)
			{
				if (entity instanceof Tank)
				{
					this.setOwned(true);
					if (((Tank) entity).isLocalPlayer())
					{
						this.setLocalPlayer(true);	
					}
					else
					{
						this.setLocalPlayer(false);
					}
				}
			}
		}
	}
}
