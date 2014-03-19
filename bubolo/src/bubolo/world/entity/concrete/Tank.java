package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.entity.Actor;

/**
 * The tank, which may be controlled by a local player, a networked player, or an AI bot.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Tank extends Actor
{
	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = 457933513574468829L;

	/**
	 * Boolean representing whether or not this Tank is driving forward.
	 */
	private boolean isDrivingForward = false;

	/**
	 * Construct a new Tank with a random UUID.
	 */
	public Tank()
	{
		super();
	}

	/**
	 * Construct a new Tank with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tank.
	 */
	public Tank(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Determines whether this Tank is driving forward or not.
	 * 
	 * @return true if the tank is driving forward, false otherwise.
	 */
	public boolean isDrivingForward()
	{
		return isDrivingForward;
	}

	/**
	 * Sets whether the tank is driving forward.
	 * 
	 * @param driving
	 *            represents whether this Tank should be driving forward. Pass false if
	 *            not.
	 */
	public void setDrivingForward(boolean driving)
	{
		this.isDrivingForward = driving;
	}

	// TODO: Add Tank functionality!
}
