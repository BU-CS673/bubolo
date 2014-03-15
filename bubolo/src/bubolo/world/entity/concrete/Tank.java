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
	
	// Max speed in pixels per tick.
	private static final float maxSpeed = 1.f;
	
	// The tank's current speed.
	private float speed = 0.f;
	
	// The rate of acceleration, in pixels per tick.
	private static final float maxAccelerationRate = 0.02f;
	
	// The rate of deceleration, in pixels per tick.
	private static final float maxDecelerationRate = 0.04f;
	
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
	}

	/**
	 * Accelerates the tank.
	 */
	public void accelerate()
	{
		
	}
	
	/**
	 * Decelerates the tank.
	 */
	public void decelerate()
	{
		
	}
	
	/**
	 * Rotates the tank clockwise.
	 */
	public void rotateRight()
	{
		
	}
	
	/**
	 * Rotates the tank counter-clockwise.
	 */
	public void rotateLeft()
	{
		
	}
}
