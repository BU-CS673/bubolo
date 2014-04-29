package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.audio.Audio;
import bubolo.audio.Sfx;
import bubolo.net.Network;
import bubolo.net.NetworkSystem;
import bubolo.net.command.UpdateOwnable;
import bubolo.world.Damageable;
import bubolo.world.Ownable;
import bubolo.world.World;
import bubolo.world.entity.StationaryElement;

/**
 * Pillboxes are stationary defensive structures that can be placed by a Tank. They shoot at an
 * enemy Tank until destroyed, at which point they can be retrieved and used again.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Pillbox extends StationaryElement implements Ownable, Damageable
{
	/**
	 * UID of tank that owns this Pillbox
	 */
	private UUID ownerUID;
	/*
	 * time at witch cannon was last fired
	 */
	private long cannonFireTime = 0;
	
	/*
	 * time required to reload cannon
	 */
	private static final long cannonReloadSpeed = 500;
	
	/*
	 * current direction pillbox is going to fire
	 */
	private float cannonRotation = 0;
	
	/*
	 * Max range to locate a target. Pillbox will not fire unless there is a tank within this range
	 */
	private double range = 300;
	
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = 278726024001386941L;

	/**
	 * Boolean representing whether this Pillbox belongs to the local player.
	 */
	private boolean isLocalPlayer = false;

	/**
	 * Boolean representing whether this Pillbox is owned by a player.
	 */
	private boolean isOwned = false;
	
	/**
	 * The health of the pillbox
	 */
	private int hitPoints;

	/**
	 * The maximum amount of hit points of the pillbox
	 */
	public static final int MAX_HIT_POINTS = 100;
	
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
		setSolid(true);
		hitPoints = MAX_HIT_POINTS;
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
	 * @return isCannonReady is the pillbox ready to fire.
	 */
	public boolean isCannonReady()
	{
		return (System.currentTimeMillis() - this.cannonFireTime > Pillbox.cannonReloadSpeed);
	}

	/**
	 * Aim the Cannon
	 * 
	 * @param rotation
	 *            direction to aim the cannon
	 */
	public void aimCannon(float rotation)
	{
		cannonRotation = rotation;
	}

	/**
	 * get cannon rotation
	 * 
	 * @return cannonRotation the direction the pillbox is set to fire
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
		bullet.setParent(this);
		
		bullet.setX(this.getX()).setY(this.getY());
		bullet.setRotation(getCannonRotation());
	}

	/**
	 * returns the range of this pillbox
	 * 
	 * @return range distance at which the pillbox will attempt to fire at an enemy
	 */
	public double getRange()
	{
		return this.range;
	}

	/**
	 * sets the static range of this pillbox
	 * 
	 * @param range
	 *            distance at which the pillbox will attempt to fire at an enemy
	 */
	public void setRange(double range)
	{
		this.range = range;
	}

	/**
	 * Returns the current health of the pillbox
	 * 
	 * @return current hit point count
	 */
	@Override
	public int getHitPoints() 
	{
		return hitPoints;
	}

	/**
	 * Method that returns the maximum number of hit points the entity can have. 
	 * @return - Max Hit points for the entity
	 */
	@Override
	public int getMaxHitPoints()
	{
		return MAX_HIT_POINTS;
	}

	/**
	 * Changes the hit point count after taking damage
	 * 
	 * @param damagePoints
	 *            how much damage the pillbox has taken
	 */
	@Override
	public void takeHit(int damagePoints) 
	{
		Audio.play(Sfx.PILLBOX_HIT);
		hitPoints -= Math.abs(damagePoints);
		if (hitPoints <= 0)
		{
			if (this.isLocalPlayer())
			{
				this.setLocalPlayer(false);
				this.setOwned(false);
				this.ownerUID = null;
				Network net = NetworkSystem.getInstance();
				net.send(new UpdateOwnable(this));
			}
		}
	}

	/**
	 * Increments the pillbox's health by a given amount
	 * 
	 * @param healPoints - how many points the pillbox is given
	 */
	@Override
	public void heal(int healPoints) 
	{
		if (hitPoints + Math.abs(healPoints) < MAX_HIT_POINTS)
		{
			hitPoints += Math.abs(healPoints);
		}

		else
		{
			hitPoints = MAX_HIT_POINTS;
		}		
	}

	@Override
	public UUID getOwnerUID() 
	{
		return this.ownerUID;
	}

	@Override
	public void setOwnerUID(UUID ownerUID) 
	{
		this.ownerUID = ownerUID;
	}
}
