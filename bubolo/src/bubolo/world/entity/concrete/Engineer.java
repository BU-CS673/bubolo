package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.World;
import bubolo.world.entity.Actor;

/**
 * The Engineer represents the driver of a Tank. The Engineer can take actions outside of
 * the Tank, and if killed, the player must wait for a new Man to spawn before taking any
 * further Engineering actions.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Engineer extends Actor
{
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
		// TODO Auto-generated method stub
		
	}

	// TODO: Add Engineer functionality!
}
