package bubolo.world.entity.concrete;

import java.util.UUID;

import com.google.common.base.Preconditions;

import bubolo.util.GameMath;
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
	private static final float maxSpeed = 4.f;

	// The tank's current speed.
	private float speed = 0.f;

	// The rate of acceleration, in pixels per tick.
	private static final float accelerationRate = 0.01f;

	// The rate of deceleration, in pixels per tick.
	private static final float decelerationRate = 0.02f;

	// Specifies whether the tank accelerated this tick.
	private boolean accelerated;

	// The tank's rate of rotation per tick.
	private static final float rotationRate = 0.05f;

	// The reload speed of the tank's cannon, in milliseconds.
	private static final long cannonReloadSpeed = 500;

	// The last time that the cannon was fired. Populate this with
	// System.currentTimeMillis().
	private long cannonFireTime = 0;

	// Specifies whether the tank is local. The default is true.
	private boolean local = true;
	// Sanity check to ensure that local isn't modified after it has initially been set.
	private boolean localWasSet;

	/**
	 * Construct a new Tank with a random UUID.
	 */
	public Tank()
	{
		super();
		setWidth(21);
		setHeight(26);
		setXOffset(6);
		setYOffset(4);
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
		setWidth(21);
		setHeight(26);
		setXOffset(6);
		setYOffset(4);
	}

	/**
	 * Specifies whether the tank is local.
	 * 
	 * @return true if the tank is local.
	 */
	public boolean isLocal()
	{
		return local;
	}

	/**
	 * Sets whether the tank is local.
	 * 
	 * @param isLocalPlayer
	 *            true if the tank is local, false otherwise.
	 */
	public void setLocal(boolean isLocalPlayer)
	{
		Preconditions.checkState(!localWasSet,
						"setLocal in entity Tank was already called. This cannot be called more than once.");
		this.local = isLocalPlayer;
		localWasSet = true;
	}

	/**
	 * Returns the tank's speed.
	 * 
	 * @return the tank's speed.
	 */
	public float getSpeed()
	{
		return speed;
	}

	// TODO: Add Tank functionality!
	/**
	 * Accelerates the tank.
	 */
	public void accelerate()
	{
		if (speed < maxSpeed)
		{
			speed += accelerationRate;
			accelerated = true;
		}
	}

	/**
	 * Decelerates the tank.
	 */
	public void decelerate()
	{
		if (speed > 0)
		{
			speed -= decelerationRate;
			if (speed < 0)
			{
				speed = 0;
			}
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

	/**
	 * Returns true if the cannon is ready to fire.
	 * 
	 * @return true if the cannon is ready to fire.
	 */
	public boolean isCannonReady()
	{
		return (System.currentTimeMillis() - cannonFireTime > cannonReloadSpeed);
	}

	/**
	 * Fires the tank's cannon, which adds a bullet to the world and initiates a cannon
	 * reload.
	 * 
	 * @param world
	 *            reference to the world.
	 * @param startX
	 *            the bullet's start x position.
	 * @param startY
	 *            the bullet's start y position.
	 * @param directionX
	 *            the bullet's x direction.
	 * @param directionY
	 *            the bullet's y direction.
	 */
	public void fireCannon(World world, float startX, float startY, float directionX,
			float directionY)
	{
		cannonFireTime = System.currentTimeMillis();

		Bullet bullet = world.addEntity(Bullet.class);
		// TODO: start the bullet at an offset to the cannon.
		bullet.setX(startX).setY(startY);
		// TODO: test this; the angle portion may be wrong.
		bullet.setRotation(GameMath.angleInRadians(startX, startY, startX + directionX, startY
				+ directionY)
				- (float) Math.PI / 2);

		// TODO: Notify the network.
		// Network net = NetworkSystem.getInstance();

	}

	@Override
	public void update(World world)
	{
		accelerated = false;

		updateControllers(world);
		moveTank();

		// TODO (cdc - 3/14/2014): check for bullet collision? That is probably the
		// responsibility of a bullet.
	}

	private void moveTank()
	{
		// TODO (cdc - 3/14/2014): turn this into another controller?

		// TODO (cdc - 3/14/2014): check for movement collisions.
		if (speed > 0)
		{
			float newX = (float) (getX() + Math.cos(getRotation()) * speed);
			float newY = (float) (getY() + Math.sin(getRotation()) * speed);

			setX(newX);
			setY(newY);

			if (!accelerated)
			{
				decelerate();
			}
		}
	}
}
