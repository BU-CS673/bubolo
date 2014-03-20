package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.Adaptable;
import bubolo.world.entity.Terrain;

/**
 * Road is a Terrain type that offers tanks improved movement speed.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Road extends Terrain implements Adaptable
{
	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = -5302600252810938564L;

	private int tilingState = 0;
	/**
	 * Construct a new Road with a random UUID.
	 */
	public Road()
	{
		super();
		setWidth(32);
		setHeight(32);
		setXOffset(0);
		setYOffset(0);
	}

	/**
	 * Construct a new Road with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Road.
	 */
	public Road(UUID id)
	{
		super(id);
		setWidth(32);
		setHeight(32);
		setXOffset(0);
		setYOffset(0);
	}

	@Override
	public void updateState()
	{
		//TODO: Add adaptive tiling logic for 4x4 grid.
		throw new UnsupportedOperationException("Adaptive tiling state updates are not implemented for Road yet!");

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

	// TODO: Add Road functionality!
}
