package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.util.TileUtil;
import bubolo.world.Adaptable;
import bubolo.world.World;
import bubolo.world.entity.Terrain;

/**
 * Craters are created when another Terrain type is blown up using a Mine. They reduce Tank movement
 * speed and may be flooded upon contact with Water.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Crater extends Terrain implements Adaptable
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = -6010471913649546792L;

	private int tilingState = 0;

	/**
	 * Modifier field used to reset an objects cap speed while traversing this terrain type.
	 */
	private static final float MAX_SPEED_MODIFIER = 0.3F;
	
	/**
	 * Intended to be generic -- this is a list of all of the StationaryEntities classes that should
	 * result in a valid match when checking surrounding tiles to determine adaptive tiling state.
	 */
	private Class<?>[] matchingTypes = new Class[] { Crater.class, Water.class };

	/**
	 * Construct a new Crater with a random UUID.
	 */
	public Crater()
	{
		this(UUID.randomUUID());
	}

	/**
	 * Construct a new Crater with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Grass.
	 */
	public Crater(UUID id)
	{
		super(id, MAX_SPEED_MODIFIER);
		setWidth(32);
		setHeight(32);
		updateBounds();
	}

	@Override
	public void updateTilingState(World w)
	{
		if (this.getTile() != null)
		{
			setTilingState(TileUtil.getTilingState(this.getTile(), w, matchingTypes));
		}
		else
		{
			setTilingState(0);
		}
	}

	@Override
	public void update(World w)
	{
		updateTilingState(w);
	}

	@Override
	public int getTilingState()
	{
		return tilingState;
	}

	@Override
	public void setTilingState(int newState)
	{
		tilingState = newState;
	}
}
