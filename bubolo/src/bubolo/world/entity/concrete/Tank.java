package bubolo.world.entity.concrete;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;

import bubolo.world.World;
import bubolo.world.entity.Actor;
import bubolo.world.entity.Entity;

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
	private static final float decelerationRate = 0.03f;

	// Specifies whether the tank accelerated this tick.
	private boolean accelerated;

	// Specifies whether the tank decelerated this tick.
	private boolean decelerated;

	// The tank's rate of rotation per tick.
	private static final float rotationRate = 0.05f;

	// The reload speed of the tank's cannon, in milliseconds.
	private static final long cannonReloadSpeed = 500;

	// The last time that the cannon was fired. Populate this with
	// System.currentTimeMillis().
	private long cannonFireTime = 0;

	private Polygon leftBumper;
	private Polygon rightBumper;

	/**
	 * Construct a new Tank with a random UUID.
	 */
	public Tank()
	{
		this(UUID.randomUUID());
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
		setWidth(20);
		setHeight(22);
		updateBounds();
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
		if (speed < maxSpeed && !accelerated)
		{
			speed += accelerationRate;
			if (speed > maxSpeed)
			{
				speed = maxSpeed;
			}
			accelerated = true;
		}
	}

	/**
	 * Decelerates the tank.
	 */
	public void decelerate()
	{
		if (speed > 0 && !decelerated)
		{
			speed -= decelerationRate;
			if (speed < 0)
			{
				speed = 0;
			}
			decelerated = true;
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
	 * Fires the tank's cannon, which adds a bullet to the world and initiates a cannon reload.
	 * 
	 * @param world
	 *            reference to the world.
	 * @param startX
	 *            the bullet's start x position.
	 * @param startY
	 *            the bullet's start y position.
	 * 
	 * @return bullet reference to the new bullet.
	 */
	public Bullet fireCannon(World world, float startX, float startY)
	{
		cannonFireTime = System.currentTimeMillis();

		Bullet bullet = world.addEntity(Bullet.class);

		bullet.setX(startX).setY(startY);
		bullet.setRotation(getRotation());

		return bullet;
	}

	private Polygon lookAheadBounds()
	{
		Polygon lookAheadBounds = getBounds();

		float newX = (float)(getX() + Math.cos(getRotation()) * speed);
		float newY = (float)(getY() + Math.sin(getRotation()) * speed);

		lookAheadBounds.setPosition(newX, newY);
		return lookAheadBounds;
	}

	@Override
	public List<Entity> getIntersectingEntities(World w)
	{

		ArrayList<Entity> intersects = new ArrayList<Entity>();
		List<Entity> allEntities = w.getEntities();
		for (int ii = 0; ii < allEntities.size(); ii++)
		{

			if (intersectsEntity(allEntities.get(ii)) || Intersector.overlapConvexPolygons(lookAheadBounds(), allEntities.get(ii).getBounds()))
			{
				intersects.add(allEntities.get(ii));
			}
		}
		return intersects;
	}

	/**
	 * Updates the bounding polygon for this Entity with its current position and rotation.
	 */
	private void updateLeftBumper()
	{
		float x = getX();
		float y = getY();
		float w = getWidth();
		float h = getHeight();

		float[] corners = new float[] {
				-w / 2, h / 2f,
				0, h / 2f,
				-w / 2, 0,
				0, 0 };
		leftBumper = new Polygon();
		leftBumper.setPosition(x, y);
		leftBumper.setOrigin(0, 0);
		leftBumper.setVertices(corners);
		leftBumper.rotate((float)Math.toDegrees(getRotation() - Math.PI / 2));
	}

	/**
	 * Updates the bounding polygon for this Entity with its current position and rotation.
	 */
	private void updateRightBumper()
	{
		float newX = (float)(getX() + Math.cos(getRotation()) * speed);
		float newY = (float)(getY() + Math.sin(getRotation()) * speed);
		float w = getWidth();
		float h = getHeight();

		float[] corners = new float[] {
				w / 2, h / 2f,
				0, h / 2f,
				w / 2, 0,
				0, 0 };
		rightBumper = new Polygon();
		rightBumper.setPosition(newX, newY);
		rightBumper.setOrigin(0, 0);
		rightBumper.setVertices(corners);
		rightBumper.rotate((float)Math.toDegrees(getRotation() - Math.PI / 2));
	}

	public boolean hitLeftBumper(Entity e)
	{
		return Intersector.overlapConvexPolygons(e.getBounds(), leftBumper);
	}

	public boolean hitRightBumper(Entity e)
	{
		return Intersector.overlapConvexPolygons(e.getBounds(), rightBumper);
	}

	public void checkIntersections(World w)
	{
		// do something interesting
	}

	private boolean facingNE()
	{
		if (getRotation() >= 0 && getRotation() < (Math.PI / 2))
		{
			return true;
		}
		else
			return false;
	}

	private boolean facingNW()
	{
		if (getRotation() >= (Math.PI / 2) && getRotation() < Math.PI)
		{
			return true;
		}
		else
			return false;
	}

	private boolean facingSW()
	{
		if (getRotation() >= Math.PI && getRotation() < (3 * Math.PI) / 2)
		{
			return true;
		}
		else
			return false;
	}

	private boolean facingSE()
	{
		if (getRotation() >= (3 * Math.PI) / 2 && getRotation() < (2 * Math.PI))
		{
			return true;
		}
		else
			return false;
	}

	@Override
	public void update(World world)
	{
		updateControllers(world);
		updateLeftBumper();
		updateRightBumper();
		moveTank(world);

		// TODO (cdc - 3/14/2014): check for bullet collision? That is probably the
		// responsibility of a bullet.
	}

	private void moveTank(World w)
	{
		// TODO (cdc - 3/14/2014): turn this into another controller?

		boolean collidingLeft = false;
		boolean collidingRight = false;
		float positionOffsetAmount = 0.25f;
		float rotationOffsetAmount = (float)Math.toRadians(0.5);
		float rotationOffset = 0f;
		float xOffset = 0;
		float yOffset = 0;
		float x = getX();
		float y = getY();
		float newX = (float)(getX() + Math.cos(getRotation()) * (speed));
		float newY = (float)(getY() + Math.sin(getRotation()) * (speed));

		List<Entity> intersects = getIntersectingEntities(w);
		for (int i = 0; i < intersects.size(); i++)
		{
			Entity collider = intersects.get(i);
			if (collider.getClass() == Wall.class)
			{
				if (hitLeftBumper(collider))
				{
					collidingLeft = true;
				}
				if (hitRightBumper(collider))
				{
					collidingRight = true;
				}
			}
		}

		if (collidingLeft)
		{
			if (facingNE())
			{
				if (newY > y)
				{
					newY = y;
					yOffset -= positionOffsetAmount;
				}
			}
			else if (facingNW())
			{
				if (newX < x)
				{
					newX = x;
					xOffset += positionOffsetAmount;
				}
			}
			else if (facingSW())
			{
				if (newY < y)
				{
					newY = y;
					yOffset += positionOffsetAmount;
				}
			}
			else if (facingSE())
			{
				if (newX > x)
				{
					newX = x;
					xOffset -= positionOffsetAmount;

				}
			}
		}

		if (collidingRight)
		{
			if (facingNE())
			{
				if (newX > x)
				{
					newX = x;
					xOffset -= positionOffsetAmount;
				}
			}
			else if (facingNW())
			{
				if (newY > y)
				{
					newY = y;
					yOffset -= positionOffsetAmount;
				}
			}
			else if (facingSW())
			{
				if (newX < x)
				{
					newX = x;
					xOffset += positionOffsetAmount;
				}
			}
			else if (facingSE())
			{
				if (newY < y)
				{
					newY = y;
					yOffset += positionOffsetAmount;
				}
			}
		}

		if (collidingLeft)
		{
			rotationOffset -= rotationOffsetAmount;
		}
		if (collidingRight)
		{
			rotationOffset += rotationOffsetAmount;
		}

		setRotation(getRotation() + rotationOffset);

		if (speed > 0)
		{
			setX(newX + xOffset);
			setY(newY + yOffset);

			if (!accelerated)
			{
				decelerate();
			}
		}

		accelerated = false;
		decelerated = false;
	}
}
