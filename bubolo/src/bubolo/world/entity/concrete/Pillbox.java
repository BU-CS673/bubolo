package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.Ownable;
import bubolo.world.World;
import bubolo.world.entity.StationaryElement;

/**
 * Pillboxes are stationary defensive structures that can be placed by a Tank. They shoot
 * at an enemy Tank until destroyed, at which point they can be retrieved and used again.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Pillbox extends StationaryElement implements Ownable
{
	private long cannonFireTime = 0;
	private static final long cannonReloadSpeed=500;
	private float cannonRotation = 0;
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
		super();
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
		// TODO Auto-generated constructor stub
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
	 * fire pillbox
	 */
	/**
	 * Returns cannon status
	 * 
	 * @param isCannonReady
	 *            is the pillbox ready to fire.
	 */
	public boolean isCannonReady()
	{
		return (System.currentTimeMillis()-this.cannonFireTime>Pillbox.cannonReloadSpeed);
	}
	/**
	 * Aim the Cannon
	 * 
	 */
	public void aimCannon(float rotation)
	{
		cannonRotation = rotation;
	}
	/**
	 * get cannon rotation
	 * 
	 */
	public float getCannonRotation()
	{
		return cannonRotation;
	}
	/**
	 * Fire the Cannon
	 * 
	 * @param world
	 *            reference to world.
	 * @param startX
	 *            starting position of bullet
	 * @param startY
	 *            starting position of bullet
	 */
	public void fireCannon(World world)
	{
		cannonFireTime = System.currentTimeMillis();

		Bullet bullet = world.addEntity(Bullet.class);

		bullet.setX(this.getCenterX()).setY(this.getCenterY());
		bullet.setRotation(getCannonRotation());
	}
	// TODO: Add Pillbox functionality!
}
