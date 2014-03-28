package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.Ownable;
import bubolo.world.World;
import bubolo.world.entity.StationaryElement;

/**
 * Pillboxes are stationary defensive structures that can be placed by a Tank. They shoot at an
 * enemy Tank until destroyed, at which point they can be retrieved and used again.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Pillbox extends StationaryElement implements Ownable
{
	/*
	 * time at witch cannon was last fired
	 */
	private long cannonFireTime = 0;
	/*
	 * time required to reload cannon
	 */
	private static final long cannonReloadSpeed=500;
	/*
	 * current direction pillbox is going to fire 
	 */
	private float cannonRotation = 0;
	/*
	 * Max range to locate a target.  Pillbox will not fire unless there is a tank within this range
	 */
	private double range = 300;
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = 278726024001386941L;

	/**
	 * Boolean representing whether this Pillbox belongs to the local player.
	 */
	private boolean isLocalPlayer = true;

	/**
	 * Boolean representing whether this Pillbox is owned by a player.
	 */
	private boolean isOwned = true;

	/**
	 * Construct a new Pillbox with a random UUID.
	 */
	public Pillbox()
	{
		this(UUID.randomUUID());
	}

	/**
	 * Construct a new Pillbox with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tree.
	 */
	public Pillbox(UUID id)
	{
		super(id);
		setWidth(27);
		setHeight(27);
		updateBounds();
	}

	@Override
	public boolean isLocalPlayer()
	{
		return isLocalPlayer;
	}

	@Override
	public void setLocalPlayer(boolean local)
	{
		this.isLocalPlayer = local;
	}

	@Override
	public boolean isOwned()
	{
		return isOwned;
	}

	@Override
	public void setOwned(boolean owned)
	{
		this.isOwned = owned;
	}
	/**
	 * Returns cannon status
	 * 
	 * @return isCannonReady
	 *            is the pillbox ready to fire.
	 */
	public boolean isCannonReady()
	{
		return (System.currentTimeMillis()-this.cannonFireTime>Pillbox.cannonReloadSpeed);
	}
	/**
	 * Aim the Cannon
	 * 
	 * @param rotation
	 * 			direction to aim the cannon
	 */
	public void aimCannon(float rotation)
	{
		cannonRotation = rotation;
	}
	/**
	 * get cannon rotation
	 * 
	 * @return cannonRotation
	 * 			the direction the pillbox is set to fire
	 */
	public float getCannonRotation()
	{
		return cannonRotation;
	}
	/**
	 * Fire the pillbox
	 * 
	 * @param world
	 *            reference to world.
	 */
	public void fireCannon(World world)
	{
		cannonFireTime = System.currentTimeMillis();

		Bullet bullet = world.addEntity(Bullet.class);

		bullet.setX(this.getX()).setY(this.getY());
		bullet.setRotation(getCannonRotation());
	}
	/**
	 * returns the range of this pillbox
	 * 
	 * @return range
	 * 			distance at which the pillbox will attempt to fire at an enemy
	 */
	public double getRange()
	{
		return this.range;
	}
	/**
	 * sets the static range of this pillbox
	 * 
	 * @param range
	 * 			distance at which the pillbox will attempt to fire at an enemy
	 */
	public void setRange(double range)
	{
		this.range = range;
	}
}
