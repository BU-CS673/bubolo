package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.Damageable;
import bubolo.world.World;
import bubolo.world.entity.Actor;

/**
 * The Engineer represents the driver of a Tank. The Engineer can take actions outside of
 * the Tank, and if killed, the player must wait for a new Man to spawn before taking any
 * further Engineering actions.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Engineer extends Actor implements Damageable
{
	/**
	 * the UID of the tank that owns this Engineer
	 */
	private UUID ownerUID;
	
	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = -2524255979041335716L;

	/**
	 * Boolean representing whether or not this Engineer is running forward.
	 */
	private boolean isRunning = false;

	/**
	 * Boolean representing whether or not this Tank is buiilding something.
	 */
	private boolean isBuilding = false;
	
	/**
	 * The health of the engineer
	 */
	private int hitPoints;

	/**
	 * The maximum amount of hit points of the engineer
	 */
	public static final int MAX_HIT_POINTS = 1;

	/**
	 * Construct a new Engineer with a random UUID.
	 */
	public Engineer()
	{
		this(UUID.randomUUID());
	}

	/**
	 * Construct a new Engineer with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Man.
	 */
	public Engineer(UUID id)
	{
		super(id);
		setWidth(14);
		setHeight(9);
		updateBounds();
		hitPoints = MAX_HIT_POINTS;
	}

	/**
	 * Determines whether this Engineer is running forward or not.
	 * 
	 * @return true if the Engineer is running forward, false otherwise.
	 */
	public boolean isRunning()
	{
		return isRunning;
	}

	/**
	 * Sets whether this Engineer is running forward or not.
	 * 
	 * @param run
	 *            should be true if this Engineer should be building something, false
	 *            otherwise.
	 */
	public void setRunning(boolean run)
	{
		isRunning = run;
	}

	/**
	 * Determines whether this Engineer is building or not.
	 * 
	 * @return true if the this Engineer is building something, false otherwise.
	 */
	public boolean isBuilding()
	{
		return isBuilding;
	}

	/**
	 * Sets whether this Engineer is building something or not.
	 * 
	 * @param building
	 *            should be true if this Engineer should be building something, false
	 *            otherwise.
	 */
	public void setBuilding(boolean building)
	{
		isBuilding = building;
	}

	@Override
	public void update(World world)
	{
		super.updateControllers(world);
	}

	/**
	 * Returns the current health of the engineer
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
	 *            how much damage the engineer has taken
	 */
	@Override
	public void takeHit(int damagePoints) 
	{
		hitPoints -= Math.abs(damagePoints);
		// TODO: This method is the first opportunity to set off "death" chain of events		
	}

	/**
	 * Increments the pillbox's health by a given amount
	 * 
	 * @param healPoints - how many points the engineer is given
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
