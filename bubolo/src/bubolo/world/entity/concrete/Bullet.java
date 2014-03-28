package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.World;
import bubolo.world.entity.Effect;
import bubolo.audio.Audio;
import bubolo.audio.Sfx;

/**
 * Bullets are shot by Tanks and Pillboxes, and can cause damage to StationaryElements and other
 * Actors.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Bullet extends Effect
{
	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = -9153862417398330898L;

	// The max distance the bullet can travel, in world units.
	private static final float MAX_DISTANCE = 600;

	// The distance the bullet has traveled.
	private int distanceTravelled;

	// The x movement per tick.
	private float movementX;

	// The y movement per tick.
	private float movementY;

	// The bullet's movement speed.
	private static final float SPEED = 6.f;

	// Specifies whether the bullet is initialized.
	private boolean initialized;

	/**
	 * Construct a new Bullet with a random UUID.
	 */
	public Bullet()
	{
		this(false);
	}

	/**
	 * Package-private constructor for testing. Allows the sound to be suppressed.
	 * 
	 * @param noSound
	 *            true if there should be no bullet creation sound, or false otherwise.
	 */
	Bullet(boolean noSound)
	{
		this(UUID.randomUUID(), noSound);
	}

	/**
	 * Construct a new Bullet with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Bullet.
	 */
	public Bullet(UUID id, boolean noSound)
	{
		super(id);
		setWidth(4);
		setHeight(8);
		updateBounds();

		// Play cannon fired sound effect.
		if (!noSound)
		{
			Audio.play(Sfx.CANNON_FIRED);
		}
	}

	/**
	 * Construct a new Bullet with the specified UUID, and play the bullet created sound by default.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Bullet.
	 */
	public Bullet(UUID id)
	{
		this(id, false);
	}

	@Override
	public void update(World world)
	{
		if (!initialized)
		{
			initialize();
		}

		// TODO: add collision detection, once the required interfaces into the
		// world have been added.
		// TODO (cdc - 2014-03-21): This could be made into a controller. However, it's so
		// simple, what's the point?
		move();
	}

	/**
	 * Moves the bullet. Calls dispose() on this entity if the distance travelled has exceeded the
	 * MAX_DISTANCE value.
	 */
	private void move()
	{
		if (distanceTravelled > MAX_DISTANCE)
		{
			dispose();
			return;
		}

		setX(getX() + movementX);
		setY(getY() + movementY);

		distanceTravelled += (movementX + movementY);
	}

	/**
	 * Sets the x and y movement values. Should be called once (but not in the constructor, since
	 * the rotation is not yet set).
	 */
	private void initialize()
	{
		movementX = (float)(Math.cos(getRotation()) * SPEED);
		movementY = (float)(Math.sin(getRotation()) * SPEED);

		initialized = true;
	}
}
