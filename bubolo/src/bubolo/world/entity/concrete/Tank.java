package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.World;
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
	private static final float accelerationRate = 0.02f;
	
	// The rate of deceleration, in pixels per tick.
	private static final float decelerationRate = 0.04f;
	
	// The tank's rate of rotation per tick.
	private static final float rotationRate = 0.03f;
	
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
		if (speed < maxSpeed)
		{
			speed += accelerationRate;
		}
	}
	
	/**
	 * Decelerates the tank.
	 */
	public void decelerate()
	{
		if (speed > 0)
		{
			speed = (speed < decelerationRate) ? 0 : decelerationRate;
		}
	}
	
	/**
	 * Rotates the tank clockwise.
	 */
	public void rotateRight()
	{
		setRotation(getRotation() + rotationRate);
	}
	
	/**
	 * Rotates the tank counter-clockwise.
	 */
	public void rotateLeft()
	{
		setRotation(getRotation() - rotationRate);
	}

	@Override
	public void update(World world)
	{
		updateControllers(world);
	}
}
