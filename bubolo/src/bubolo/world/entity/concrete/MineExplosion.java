package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.entity.Effect;

/**
 * MineExplosions are created when mines blow up! They're large, and create Craters on top of whatever Terrain is underneath them.
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
	 * Construct a new Bullet with a random UUID.
	 */
	public MineExplosion()
	{
		super();
	}

	/**
	 * Construct a new Bullet with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tank.
	 */
	public MineExplosion(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}

	// TODO: Add Bullet functionality!
}
