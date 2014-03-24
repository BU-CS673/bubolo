package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.Adaptable;
import bubolo.world.entity.StationaryElement;

/**
 * Craters are created when another Terrain type is blown up using a Mine. They reduce
 * Tank movement speed and may be flooded upon contact with Water.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Crater extends StationaryElement implements Adaptable
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = -6010471913649546792L;

	private int tilingState = 0;

	/**
	 * Construct a new Crater with a random UUID.
	 */
	public Crater()
	{
		super();
		setWidth(32);
		setHeight(32);
		setXOffset(0);
		setYOffset(0);
	}

	/**
	 * Construct a new Crater with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Grass.
	 */
	public Crater(UUID id)
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
		// TODO: Add adapative tiling state logic for 4x4 grid.
		throw new UnsupportedOperationException(
				"Adaptive tiling state updates are not implemented for Crater yet!");
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

	// TODO: Add Crater functionality!
}
