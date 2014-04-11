package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.util.TileUtil;
import bubolo.world.Adaptable;
import bubolo.world.World;
import bubolo.world.entity.Terrain;

/**
 * Water terrain can be crossed by a Tank, but can deal damage over time.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Water extends Terrain implements Adaptable
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = 7556626906247082191L;

	private int tilingState = 0;

	private boolean[] cornerMatches = new boolean[4];

	/**
	 * Intended to be generic -- this is a list of all of the StationaryEntities classes that should
	 * result in a valid match when checking surrounding tiles to determine adaptive tiling state.
	 */
	private Class<?>[] matchingTypes = new Class[] { Water.class, DeepWater.class, Crater.class };

	/**
	 * Modifier field used to reset an objects cap speed while traversing this terrain type.
	 */
	private static final float MAX_SPEED_MODIFIER = 0.4f;

	/**
	 * Construct a new Water with a random UUID.
	 */
	public Water()
	{
		this(UUID.randomUUID());
	}

	/**
	 * Construct a new Water with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Grass.
	 */
	public Water(UUID id)
	{
		super(id, MAX_SPEED_MODIFIER);
		setWidth(32);
		setHeight(32);
		updateBounds();
	}

	@Override
	public void update(World w)
	{
		super.update(w);
		updateTilingState(w);
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

	/**
	 * Return an array of booleans representing whether the tiles along the corners of this Water's
	 * tile contain a matching object for the adaptive tiling procedure.
	 * 
	 * @return an array of booleans, where the elements represent whether a matching object was
	 *         found to the top left, top right, bottom left, and bottom right of this object, in
	 *         order.
	 */
	public boolean[] getCornerMatches()
	{
		return cornerMatches;
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
