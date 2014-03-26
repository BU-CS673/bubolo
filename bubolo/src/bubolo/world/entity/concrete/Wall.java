package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.util.AdaptiveTileChecker;
import bubolo.world.Adaptable;
import bubolo.world.World;
import bubolo.world.entity.StationaryElement;

/**
 * Walls are intended to impede Tank movement, and create Rubble Terrain when destroyed.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Wall extends StationaryElement implements Adaptable
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = -4591161497141031916L;

	private int tilingState = 0;

	/**
	 * Intended to be generic -- this is a list of all of the StationaryEntities classes that should
	 * result in a valid match when checking surrounding tiles to determine adaptive tiling state.
	 */
	private Class[] matchingTypes = new Class[] { Wall.class };

	/**
	 * Construct a new Wall with a random UUID.
	 */
	public Wall()
	{
		super();
	}

	/**
	 * Construct a new Wall with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tree.
	 */
	public Wall(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateTilingState(World w)
	{
		setTilingState(AdaptiveTileChecker.getTilingState(this.getTile(), w, matchingTypes));
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

	// TODO: Add Wall functionality!
}
