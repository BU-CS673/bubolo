package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.util.TileUtil;
import bubolo.world.Adaptable;
import bubolo.world.World;
import bubolo.world.entity.Terrain;

/**
 * Deep water is intended to act as a barrier to prevent Tanks from leaving the map.
 * 
 * @author BU CS673 - Clone Productions
 */
public class DeepWater extends Terrain implements Adaptable
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = -4427335167013500776L;

	private int tilingState = 0;

	private boolean[] cornerMatches = new boolean[4];

	/**
	 * Modifier field used to reset an objects cap speed while traversing this terrain type.
	 */
	private static final float MAX_SPEED_MODIFIER = 0.2F;
	
	/**
	 * Intended to be generic -- this is a list of all of the StationaryEntities classes that should
	 * result in a valid match when checking surrounding tiles to determine adaptive tiling state.
	 */
	private Class<?>[] matchingTypes = new Class[] { Water.class };

	/**
	 * Construct a new DeepWater with a random UUID.
	 */
	public DeepWater()
	{
		this(UUID.randomUUID());
	}

	/**
	 * Construct a new DeepWater with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Grass.
	 */
	public DeepWater(UUID id)
	{
		super(id, MAX_SPEED_MODIFIER);
		setWidth(32);
		setHeight(32);
		updateBounds();
	}

	/**
	 * Return an array of booleans representing whether the tiles along the corners of this object's
	 * tile contain a matching object for the adaptive tiling procedure.
	 * 
	 * @return an array of booleans, where the elements represent whether a matching object was
	 *         found to the top left, top right, bottom left, and bottom right of this obect, in
	 *         order.
	 */
	public boolean[] getCornerMatches()
	{
		return cornerMatches;
	}

	@Override
	public void updateTilingState(World w)
	{
		if (this.getTile() != null)
		{
			setTilingState(TileUtil.getTilingState(this.getTile(), w, matchingTypes));
			cornerMatches = TileUtil.getCornerMatches(this.getTile(), w, matchingTypes);
		}
		else
		{
			setTilingState(0);
		}
	}

	@Override
	public void update(World w)
	{
		super.update(w);
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
