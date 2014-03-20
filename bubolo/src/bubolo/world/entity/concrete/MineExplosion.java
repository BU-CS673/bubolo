package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.World;
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
	 * Construct a new MineExplosion with a random UUID.
	 */
	public MineExplosion()
	{
		super();
		setWidth(60);
		setHeight(60);
		setXOffset(0);
		setYOffset(0);
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
		setXOffset(0);
		setYOffset(0);
	}

	// TODO: Add MineExplosion functionality!
}
