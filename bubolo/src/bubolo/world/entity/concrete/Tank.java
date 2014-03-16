package bubolo.world.entity.concrete;

import java.util.UUID;

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
	private static final float maxSpeed = 1.f;
	
	// The tank's current speed.
	private float speed = 0.f;
	
	// The rate of acceleration, in pixels per tick.
	private static final float accelerationRate = 0.02f;
	
	// The rate of deceleration, in pixels per tick.
	private static final float decelerationRate = 0.03f;
	
	// The tank's rate of rotation per tick.
	private static final float rotationRate = 0.25f;
	
	// The reload speed of the tank's cannon, in milliseconds.
	private static final long cannonReloadSpeed = 500;
	
	// The last time that the cannon was fired. Populate this with System.currentTimeMillis().
	private long cannonFireTime = 0;
	
	// Specifies whether the tank is local. The default is true.
	private boolean local = true;
	
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
	 * Specifies whether the tank is local.
	 * @return true if the tank is local.
	 */
	public boolean isLocal()
	{
		return local;
	}
	
	/**
	 * Sets whether the tank is local.
	 * @param isLocalPlayer true if the tank is local, false otherwise.
	 */
	public void setLocal(boolean isLocalPlayer)
	{
		this.local = isLocalPlayer;
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
			speed -= (speed < 0) ? 0 : decelerationRate;
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
	 * @return true if the cannon is ready to fire.
	 */
	public boolean isCannonReady()
	{
		return (System.currentTimeMillis() - cannonFireTime > cannonReloadSpeed);
	}
	
	/**
	 * Fires the tank's cannon, which adds a bullet to the world and initiates a cannon reload.
	 * @param world reference to the world.
	 * @param startX the bullet's start x position.
	 * @param startY the bullet's start y position.
	 * @param directionX the bullet's x direction.
	 * @param directionY the bullet's y direction.
	 */
	public void fireCannon(World world, float startX, float startY, float directionX, float directionY)
	{
		cannonFireTime = System.currentTimeMillis();
		
		Bullet bullet = world.addEntity(Bullet.class);
		bullet.setX(startX).setY(startY);
		// TODO: test this; the angle portion may be wrong.
		bullet.setRotation(GameMath.angleInRadians(startX, startY, startX + directionX, startY + directionY));
		
		// TODO: Notify the network.
	}

	@Override
	public void update(World world)
	{
		updateControllers(world);
		moveTank();
		// TODO (cdc - 3/14/2014): turn this into another controller?
		// TODO (cdc - 3/14/2014): move the tank.
		
		// TODO (cdc - 3/14/2014): check for bullet collision? That is probably the responsibility of a bullet.
	}
	
	private void moveTank()
	{
		// TODO (cdc - 3/15/2014): decelerate automatically.
		
		// TODO (cdc - 3/14/2014): check for movement collisions.
		if (speed > 0)
		{
			// TODO: include x; adjust for rotation.
			setY(getY() + speed);
		}
	}
}
