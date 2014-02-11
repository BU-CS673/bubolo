package bubolo.world;

import java.util.UUID;

/**
 * Terrain objects represent the base of the game world at a given grid position.
 * A grid location must contain one Terrain object.
 * @author BU CS673 - Clone Productions
 */
public abstract class Terrain extends StationaryEntity
{

	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = 6373697443259757484L;
	
	/**
	 * Construct a new Terrain with a random UUID.
	 */
	public Terrain()
	{
		super();
	}

	/**
	 * Construct a new Terrain with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Terrain.
	 */
	public Terrain(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construct a new Terrain with the given initial parameters and a random UUID.
	 * 
	 * @param x
	 *            is the initial x position in world coordinates.
	 * @param y
	 *            is the initial y position in world coordinates.
	 * @param w
	 *            is the initial width in world coordinates.
	 * @param h
	 *            is the initial height in world coordinates.
	 * @param rot
	 *            is the initial rotation in radians.
	 */
	public Terrain(float x, float y, int w, int h, float rot)
	{
		super(x, y, w, h, rot);
	}

	/**
	 * Construct a new Terrain with the given initial parameters and the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Terrain.
	 * @param x
	 *            is the initial x position in world coordinates.
	 * @param y
	 *            is the initial y position in world coordinates.
	 * @param w
	 *            is the initial width in world coordinates.
	 * @param h
	 *            is the initial height in world coordinates.
	 * @param rot
	 *            is the initial rotation in radians.
	 */
	public Terrain(float x, float y, int w, int h, float rot, UUID id)
	{
		super(x, y, w, h, rot, id);
	}
	

	//TODO: Add Terrain functionality!
}
