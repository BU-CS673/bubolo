package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.util.AdaptiveTileChecker;
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

	private boolean[] cornerStates = new boolean[4];

	/**
	 * Intended to be generic -- this is a list of all of the StationaryEntities classes that should
	 * result in a valid match when checking surrounding tiles to determine adaptive tiling state.
	 */
	private Class[] matchingTypes = new Class[] { Water.class };

	/**
	 * Construct a new DeepWater with a random UUID.
	 */
	public DeepWater()
	{
		super();
	}

	/**
	 * Construct a new DeepWater with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Grass.
	 */
	public DeepWater(UUID id)
	{
		super(id);
	}

	public boolean[] getCornerStates()
	{
		return cornerStates;
	}

	@Override
	public void updateTilingState(World w)
	{
		setTilingState(AdaptiveTileChecker.getTilingState(this.getTile(), w, matchingTypes));
		cornerStates = AdaptiveTileChecker.getCornerMatches(this.getTile(), w, matchingTypes);
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

	// TODO: Add DeepWater functionality!
}
