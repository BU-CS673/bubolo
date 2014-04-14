package bubolo.world.entity.concrete;

import java.util.UUID;

import com.badlogic.gdx.math.Intersector;

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
	private static final int DAMAGE_DONE = 10;
	
	//set to true when animation complete
	private boolean finished = false;

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
		updateBounds();
	}
	/**
	 * returns whether or not the explosion animation has completed
	 * 
	 * @return
	 * 		returns true if the animation has completed
	 */
	
	public boolean isFinished()
	{
		return finished;
	}
	
	/**
	 * returns whether or not the explosion animation has completed
	 * 
	 * @return
	 * 		returns true if the animation has completed
	 */
	
	public void setFinished(boolean f)
	{
		finished = f;
	}
	
	@Override
	public void update(World world)
	{
		if(!this.finished)
		{
			for(Entity collider:TileUtil.getLocalCollisions(this, world))
			{
				if (collider instanceof Damageable)
				{
					((Damageable) collider).modifyHP(DAMAGE_DONE);
				}
			}
		}
	}
}
