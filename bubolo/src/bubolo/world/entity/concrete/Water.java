package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.util.AdaptiveTileChecker;
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

	private boolean[] cornerStates = new boolean[4];

	/**
	 * Intended to be generic -- this is a list of all of the StationaryEntities classes that should
	 * result in a valid match when checking surrounding tiles to determine adaptive tiling state.
	 */
	private Class[] matchingTypes = new Class[] { Water.class, DeepWater.class, Crater.class };

	/**
	 * Construct a new Water with a random UUID.
	 */
	public Water()
	{
		super();
	}

	/**
	 * Construct a new Water with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Grass.
	 */
	public Water(UUID id)
	{
		super(id);
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
			setTilingState(AdaptiveTileChecker.getTilingState(this.getTile(), w, matchingTypes));
			cornerStates = AdaptiveTileChecker.getCornerMatches(this.getTile(), w, matchingTypes);
		}
		else
		{
			setTilingState(0);
		}
	}

	public boolean[] getCornerStates()
	{
		return cornerStates;
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

	// TODO: Add Water functionality!
}
