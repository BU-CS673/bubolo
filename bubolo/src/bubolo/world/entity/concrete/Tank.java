package bubolo.world.entity.concrete;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;

import bubolo.util.TileUtil;
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
	
	// Specifies whether the tank is hidden in trees
	private boolean hidden;

	// The tank's rate of rotation per tick.
	private static final float rotationRate = 0.05f;

	// The reload speed of the tank's cannon, in milliseconds.
	private static final long cannonReloadSpeed = 500;

	// The last time that the cannon was fired. Populate this with
	// System.currentTimeMillis().
	private long cannonFireTime = 0;

	private Polygon leftBumper = new Polygon();
	private Polygon rightBumper = new Polygon();
	private float bumperWidth = 4.0f;
	private float bumperHeight = 4.0f;

	/**
	 * The default amount to rotate the Tank by when a bumper collision is detected. Used
	 * to prevent getting 'stuck' on walls.
	 */
	private float rotationOffsetAmount = (float) Math.toRadians(1);

	/**
	 * The default amount to reposition the Tank by when a bumper collision is detected.
	 * Used to prevent getting 'stuck' on walls.
	 */
	private float positionOffsetAmount = 0.1f;

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
		setSolid(true);
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
	 * Fires the tank's cannon, which adds a bullet to the world and initiates a cannon
	 * reload.
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

		float newX = (float) (getX() + Math.cos(getRotation()) * speed);
		float newY = (float) (getY() + Math.sin(getRotation()) * speed);

		lookAheadBounds.setPosition(newX, newY);
		return lookAheadBounds;
	}

	/**
	 * Returns a list of all Entities that would overlap with this Tank if it was where it
	 * will be in one game tick, along its current trajectory.
	 */
	private List<Entity> getLookaheadEntities(World w)
	{
		ArrayList<Entity> intersects = new ArrayList<Entity>();
		List<Entity> localEntities = TileUtil.getLocalEntities(getX(), getY(), w);
		for (int ii = 0; ii < localEntities.size(); ii++)
		{
			if (localEntities.get(ii) != this)
			{
				if (overlapsEntity(localEntities.get(ii))
						|| Intersector.overlapConvexPolygons(lookAheadBounds(), localEntities.get(ii)
								.getBounds()))
				{
					intersects.add(localEntities.get(ii));
				}
			}
		}
		return intersects;
	}

	/**
	 * Update the left and right bumpers to use current positioning and speed information.
	 */
	private void updateBumpers()
	{
		updateLeftBumper();
		updateRightBumper();
	}

	/**
	 * Updates the bounding polygon for this Entity with its current position and
	 * rotation.
	 */
	private void updateLeftBumper()
	{
		float newX = (float) (getX() + Math.cos(getRotation()) * (speed));
		float newY = (float) (getY() + Math.sin(getRotation()) * (speed));
		float w = getWidth();
		float h = getHeight();
		// Defines the corners of the left bumper as a 4x4 pixel box, placed at the
		// top-left edge of
		// the tank, with
		// its left edge along the left edge of the tank and its topmost edge aligned with
		// the front
		// edge of the tank.
		float[] corners = new float[] { -w / 2f, h / 2f, -w / 2f + 4, h / 2f, -w / 2f, h / 2f - 4,
				-w / 2f + 4, h / 2f - 4 };
		leftBumper = new Polygon();
		leftBumper.setPosition(newX, newY);
		leftBumper.setOrigin(0, 0);
		leftBumper.setVertices(corners);
		leftBumper.rotate((float) Math.toDegrees(getRotation() - Math.PI / 2));
	}

	/**
	 * Updates the bounding polygon for this Entity with its current position and
	 * rotation.
	 */
	private void updateRightBumper()
	{
		float newX = (float) (getX() + Math.cos(getRotation()) * (speed));
		float newY = (float) (getY() + Math.sin(getRotation()) * (speed));
		float w = getWidth();
		float h = getHeight();

		// Defines the corners of the right bumper as a 4x4 pixel box, placed at the
		// top-left edge
		// of the tank, with
		// its left edge along the left edge of the tank and its topmost edge aligned with
		// the front
		// edge of the tank.
		float[] corners = new float[] { w / 2f, h / 2f, w / 2f - bumperWidth, h / 2f, w / 2f,
				h / 2f - bumperHeight, w / 2f - bumperWidth, h / 2f - bumperHeight };
		rightBumper = new Polygon();
		rightBumper.setPosition(newX, newY);
		rightBumper.setOrigin(0, 0);
		rightBumper.setVertices(corners);
		rightBumper.rotate((float) Math.toDegrees(getRotation() - Math.PI / 2));
	}

	/**
	 * Checks to see if an Entity overlaps with this Tank's left bumper.
	 */
	private boolean hitLeftBumper(Entity e)
	{
		return Intersector.overlapConvexPolygons(e.getBounds(), leftBumper);
	}

	/**
	 * Checks to see if an Entity overlaps with this Tank's right bumper.
	 */
	private boolean hitRightBumper(Entity e)
	{
		return Intersector.overlapConvexPolygons(e.getBounds(), rightBumper);
	}

	/**
	 * Checks to see if this Tank is facing to the Northeast (for bumper collisions)
	 */
	private boolean facingNE()
	{
		if (getRotation() >= 0 && getRotation() < (Math.PI / 2))
		{
			return true;
		}
		else
			return false;
	}

	/**
	 * Checks to see if this Tank is facing to the Northwest (for bumper collisions)
	 */
	private boolean facingNW()
	{
		if (getRotation() >= (Math.PI / 2) && getRotation() < Math.PI)
		{
			return true;
		}
		else
			return false;
	}

	/**
	 * Checks to see if this Tank is facing to the Southwest (for bumper collisions)
	 */
	private boolean facingSW()
	{
		if (getRotation() >= Math.PI && getRotation() < (3 * Math.PI) / 2)
		{
			return true;
		}
		else
			return false;
	}

	/**
	 * Checks to see if this Tank is facing to the Southeast (for bumper collisions)
	 */
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
		moveTank(world);

		// TODO (cdc - 3/14/2014): check for bullet collision? That is probably the
		// responsibility of a bullet.
	}

	/**
	 * Updates the Tank's world position according to its speed, acceleration/deceleration
	 * state, and collision information.
	 * 
	 * @param world
	 *            is a reference to the world that this Tank belongs to.
	 */
	private void moveTank(World world)
	{

		/**
		 * Booleans used to record which, if any, bumpers were hit.
		 */
		boolean collidingLeft = false;
		boolean collidingRight = false;

		/**
		 * Floats used the offset that should be applied to the Tank to record wall
		 * collisions.
		 */
		float rotationOffset = 0f;
		float xOffset = 0;
		float yOffset = 0;

		/**
		 * Store the Tank's current positioning and speed data, for use in calculations.
		 */
		float xPos = getX();
		float yPos = getY();
		float rotation = getRotation();

		/**
		 * The position where the Tank will be after one game tick, if it continues its
		 * current trajectory and speed.
		 */
		float newX = (float) (xPos + Math.cos(rotation) * (speed));
		float newY = (float) (yPos + Math.sin(rotation) * (speed));

		/**
		 * Update (replace) the right and left bumper polygons to make sure collisions are
		 * accurate.
		 */
		updateBumpers();

		// Currently checks against all Entities in the world, then checks each of the
		// ones that
		// overlap to see if they overlap the bumpers.
		List<Entity> possibleCollisions = getLookaheadEntities(world);
		for (int i = 0; i < possibleCollisions.size(); i++)
		{
			Entity collider = possibleCollisions.get(i);
			if (collider.isSolid())
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

		/**
		 * If the Tank hit something with its left bumper, restrict travel in the
		 * appropriate direction, and offset/rotate the Tank to 'slide' away from the
		 * collision.
		 */
		if (collidingLeft)
		{
			rotationOffset -= rotationOffsetAmount;
			if (facingNE())
			{
				if (newY > yPos)
				{
					newY = yPos;
					yOffset -= positionOffsetAmount;
				}
			}
			else if (facingNW())
			{
				if (newX < xPos)
				{
					newX = xPos;
					xOffset += positionOffsetAmount;
				}
			}
			else if (facingSW())
			{
				if (newY < yPos)
				{
					newY = yPos;
					yOffset += positionOffsetAmount;
				}
			}
			else if (facingSE())
			{
				if (newX > xPos)
				{
					newX = xPos;
					xOffset -= positionOffsetAmount;

				}
			}
		}

		/**
		 * If the Tank hit something with its right bumper, restrict travel in the
		 * appropriate direction, and offset/rotate the Tank to 'slide' away from the
		 * collision.
		 */
		if (collidingRight)
		{
			rotationOffset += rotationOffsetAmount;
			if (facingNE())
			{
				if (newX > xPos)
				{
					newX = xPos;
					xOffset -= positionOffsetAmount;
				}
			}
			else if (facingNW())
			{
				if (newY > yPos)
				{
					newY = yPos;
					yOffset -= positionOffsetAmount;
				}
			}
			else if (facingSW())
			{
				if (newX < xPos)
				{
					newX = xPos;
					xOffset += positionOffsetAmount;
				}
			}
			else if (facingSE())
			{
				if (newY < yPos)
				{
					newY = yPos;
					yOffset += positionOffsetAmount;
				}
			}
		}

		/**
		 * If the speed of the Tank is greater than zero, modify its position and rotation
		 * by the offsets given earlier. Note that if a Tank collides on the left and
		 * right bumpers simultaneously, the rotational offsets will cancel each other
		 * out.
		 */
		if (speed > 0)
		{
			setX(newX + xOffset);
			setY(newY + yOffset);
			setRotation(rotation + rotationOffset);

			if (!accelerated)
			{
				decelerate();
			}
		}

		accelerated = false;
		decelerated = false;
	}
}
