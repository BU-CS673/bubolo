package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.util.TileUtil;
import bubolo.world.Damageable;
import bubolo.world.World;
import bubolo.world.entity.Effect;
import bubolo.world.entity.Entity;

/**
 * MineExplosions are created when mines blow up! They're large, and create Craters on top of
 * whatever Terrain is underneath them.
 * 
 * @author BU CS673 - Clone Productions
 */
public class MineExplosion extends Effect
{
	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = -8107393112729824023L;
	
	/**
	 * Damage done on explosion
	 */
	private static final int DAMAGE_PER_TICK = 2;
	
	/**
	 * length of explosion in milliseconds
	 */
	private static final long EXPLOSION_LENGTH = 500;
	
	/**
	 * time the explosion started
	 */
	private long explosionStart;
	
	/**
	 * Construct a new MineExplosion with a random UUID.
	 */
	public MineExplosion()
	{
		this(UUID.randomUUID());
	}

	/**
	 * Construct a new MineExplosion with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tank.
	 */
	public MineExplosion(UUID id)
	{
		super(id);
		setWidth(60);
		setHeight(60);
		explosionStart = System.currentTimeMillis();
		updateBounds();
	}
	/**
	 * returns the length of the explosion in millisends
	 * 
	 * @return
	 * 		returns true if the animation has completed
	 */
	
	public long getExplosionLength()
	{
		return this.EXPLOSION_LENGTH;
	}
	
	
	@Override
	public void update(World world)
	{
		if((this.EXPLOSION_LENGTH + this.explosionStart) > System.currentTimeMillis())
		{
			for(Entity collider:TileUtil.getLocalCollisions(this, world))
			{
				if (collider instanceof Damageable)
				{
					Damageable damageableCollider = (Damageable)collider;
					damageableCollider.takeHit(DAMAGE_PER_TICK);
				}
			}
		}
		else
		{
			this.dispose();
		}
	}
}
