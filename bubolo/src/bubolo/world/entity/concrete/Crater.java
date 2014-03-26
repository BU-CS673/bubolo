package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.util.AdaptiveTileChecker;
import bubolo.world.Adaptable;
import bubolo.world.World;
import bubolo.world.entity.StationaryElement;

/**
 * Craters are created when another Terrain type is blown up using a Mine. They reduce Tank movement
 * speed and may be flooded upon contact with Water.
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
	 * Intended to be generic -- this is a list of all of the StationaryEntities classes that should
	 * result in a valid match when checking surrounding tiles to determine adaptive tiling state.
	 */
	private Class[] matchingTypes = new Class[] { Road.class, Water.class };

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
	public void updateTilingState(World w)
	{
		setTilingState(AdaptiveTileChecker.getTilingState(this.getTile(), w, matchingTypes));
	}

	@Override
	public void update(World w)
	{
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

	// TODO: Add Crater functionality!
}
