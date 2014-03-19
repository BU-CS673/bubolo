package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.Adaptable;
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

	@Override
	public void updateState()
	{
		// Do nothing -- this needs to be written!
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

	// TODO: Add DeepWater functionality!
}
