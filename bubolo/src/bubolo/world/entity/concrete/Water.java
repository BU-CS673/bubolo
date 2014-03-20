package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.Adaptable;
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
	public void updateState()
	{
		//TODO: Add adaptive tiling state logic for 4x4 + 3x3 + 3x3 grid.
		throw new UnsupportedOperationException("Adaptive tiling state updates are not implemented for Water yet!");
	}

	@Override
	public int getState()
	{
		return tilingState;
	}

	@Override
	public void setState(int newState)
	{
		tilingState = newState;
	}

	// TODO: Add Water functionality!
}
