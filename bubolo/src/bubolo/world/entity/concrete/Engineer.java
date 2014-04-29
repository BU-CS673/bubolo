package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.util.TileUtil;
import bubolo.world.Damageable;
import bubolo.world.World;
import bubolo.world.entity.Actor;
import bubolo.world.entity.Terrain;
import bubolo.world.entity.concrete.Tank;

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
	 * The tank that this engineer is attached to
	 */
	public Tank tank;
	
	// Max speed in pixels per tick.
	private static final float maxSpeed = 2.f;

	// Next Waypoint for the engineer.
	private boolean atWaypoint = true;
	private float xWaypoint;
	private float yWaypoint;
	
	/**
	 * Used to calculate the maxSpeed based upon the interaction with the intersected terrains
	 */
	private float modifiedMaxSpeed = maxSpeed;

	/**
	 * Construct a new Engineer with a random UUID.
	 */
	public Engineer()
	{
		this(UUID.randomUUID(), null);
	}

	/**
	 * Construct a new Engineer with a random UUID.
	 */
	public Engineer(Tank tank)
	{
		this(UUID.randomUUID(), tank);
	}

	/**
	 * Construct a new Engineer with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Man.
	 */
	public Engineer(UUID id, Tank tank)
	{
		super(id);
		setWidth(14);
		setHeight(9);
		updateBounds();
		hitPoints = MAX_HIT_POINTS;
		this.tank = tank;
	}

	/**
	 * @return the tank
	 */
	public Tank getTank()
	{
		return tank;
	}

	/**
	 * @param tank the tank to set
	 */
	public void setTank(Tank tank)
	{
		this.tank = tank;
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
	 * Sets the next Waypoint for the engineer.
	 * 
	 * @param x
	 *            x co-ordinate of the Waypoint
	 * @param y
	 *            y co-ordinate of the Waypoint
	 */
	public void setWaypoint(float x, float y)
	{
		xWaypoint = x;
		yWaypoint = y;
		atWaypoint = false;
	}

	/**
	 * Determines whether the Engineer has reached its last Waypoint.
	 * 
	 * @return true of the Engineer has reached its last Waypoint.
	 */
	public boolean isAtWaypoint()
	{
		return atWaypoint;
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
		moveEngineer(world);
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
	 * Increments the engineer's health by a given amount
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

	/**
	 * Updates the Engineer's world position according to the Waypoint
	 * position provided by the controller.
	 * 
	 * @param world
	 *            is a reference to the world that this Engineer belongs to.
	 */
	private void moveEngineer(World world)
	{
		// If the engineer is already at its Waypoint, there is nothing to do.
		if (atWaypoint)
		{
			return;
		}
		
		Terrain currentTerrain = TileUtil.getTileTerrain(getX(), getY(), world);
		if (currentTerrain != null)
		{
			modifiedMaxSpeed = maxSpeed * currentTerrain.getMaxSpeedModifier();
		}

		// Store the Engineer's current positioning and speed data, for use in calculations.
		float xPos = getX();
		float yPos = getY();
		
		// The position where the Engineer will be after one game tick.
		float xNew;
		float yNew;
		
		// The engineer should attempt to move towards WaypointX, WaypointY at full speed. 
		float xDelta = xWaypoint - xPos;
		float yDelta = yWaypoint - yPos;
		float distanceToWaypoint = (float)Math.sqrt((xDelta * xDelta) + (yDelta * yDelta));
		float slopeToWaypoint = yDelta / xDelta;
		float rotation = (float)Math.atan(slopeToWaypoint);
		
		// Correct rotation.
		if (xDelta < 0)
		{
			rotation = (float)Math.PI + rotation;
		}

		// Can the engineer cover the entire distance in this game tick?
		float maxDistance = (float)(modifiedMaxSpeed * 1.0);
		if (maxDistance >= distanceToWaypoint)
		{
			// Yes, Waypoint can be reached in this game tick.
			xNew = xWaypoint;
			yNew = yWaypoint;
			atWaypoint = true;
		}
		else
		{
			// No, Waypoint cannot be reached in this game tick. Advance the
			// engineer along the vector to the Waypoint.
			xNew = xPos + maxDistance * (float)Math.cos(rotation);
			yNew = yPos + maxDistance * (float)Math.sin(rotation);
		}
		
		setX(xNew);
		setY(yNew);
		setRotation(rotation);
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
