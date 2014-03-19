package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.Adaptable;
import bubolo.world.entity.Terrain;

/**
 * Craters are created when another Terrain type is blown up using a Mine. They reduce
 * Tank movement speed and may be flooded upon contact with Water.
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
	 * Construct a new Crater with a random UUID.
	 */
	public Crater()
	{
		super();
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

	// TODO: Add Crater functionality!
}
