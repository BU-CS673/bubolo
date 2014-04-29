package bubolo.world.entity.concrete;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;

import bubolo.util.TileUtil;
import bubolo.world.Damageable;
import bubolo.world.World;
import bubolo.world.entity.Effect;
import bubolo.world.entity.Entity;
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
	private int distanceTraveled;

	// The x movement per tick.
	private float movementX;

	// The y movement per tick.
	private float movementY;

	// The bullet's movement speed.
	private static final float SPEED = 6.f;
	
	// The bullet's movement speed.
	private static final int DAMAGEDONE = 10;

	// Specifies whether the bullet is initialized.
	private boolean initialized;
	
	private Entity parent = null;

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
	 * @param noSound
	 *            should be true if a sound should not be played upon Bullet construction, false
	 *            otherwise.
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
		// TODO (cdc - 2014-03-21): This could be made into a controller. However, it's so
		// simple, what's the point?
		move(world);
	}
	/**
	 * return the Entity that spawned this bullet
	 * @return
	 * 		the entity that spawned this bullet
	 */
	public Entity getParent()
	{
		return this.parent;
	}
	/**
	 *  sets the Parent field of this bullet
	 * @param parent
	 * 		the entity to set as the parent of this bullet
	 */
	public void setParent(Entity parent)
	{
		this.parent = parent;

	}

	/**
	 * Moves the bullet. Calls dispose() on this entity if the distance travelled has exceeded the
	 * MAX_DISTANCE value.
	 */
	private void move(World world)
	{
		if (distanceTraveled > MAX_DISTANCE)
		{
			dispose();
			return;
		}

		setX(getX() + movementX);
		setY(getY() + movementY);

		distanceTraveled += (Math.abs(movementX) + Math.abs(movementY));

		for(Entity collider:getLookaheadEntities(world))
		{
			if (collider instanceof Damageable)
			{
				if (Intersector.overlapConvexPolygons(collider.getBounds(), this.getBounds()))
				{
					Damageable damageableCollider = (Damageable)collider;
					damageableCollider.takeHit(DAMAGEDONE);
					dispose();
					return;
				}
			}
		}
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
	/**
	 * Returns a list of all Entities that would overlap with this Tank if it was where it
	 * will be in one game tick, along its current trajectory.
	 */
	private List<Entity> getLookaheadEntities(World w)
	{
		ArrayList<Entity> intersects = new ArrayList<Entity>();

		for (Entity localEntity: TileUtil.getLocalEntities(getX(),getY(), w))
		{
			if (localEntity!=this && localEntity!=this.parent)
			{
				if (overlapsEntity(localEntity)
						||Intersector.overlapConvexPolygons(lookAheadBounds(), localEntity.getBounds()))
				{
					intersects.add(localEntity);	
				}
			}
		}
		return intersects;
	}
	
	private Polygon lookAheadBounds()
	{
		Polygon lookAheadBounds = getBounds();

		float newX = (float) (getX() + Math.cos(getRotation()) * SPEED);
		float newY = (float) (getY() + Math.sin(getRotation()) * SPEED);

		lookAheadBounds.setPosition(newX, newY);
		return lookAheadBounds;
	}
}
