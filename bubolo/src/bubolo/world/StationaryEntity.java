package bubolo.world;

import java.util.UUID;

/**
 * Basic class for DrawableEntities that do not move (i.e., those that are fixed a single
 * location on the map grid).
 * 
 * @author BU673 - Clone Industries
 */
public abstract class StationaryEntity extends Entity
{

	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = 7748173162492885868L;

	/**
	 * Construct a new StationaryEntity with a random UUID.
	 */
	public StationaryEntity()
	{
		super();
	}

	/**
	 * Construct a new StationaryEntity with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new StationaryEntity.
	 */
	public StationaryEntity(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construct a new StationaryEntity with the given initial parameters and a random UUID.
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
	public StationaryEntity(float x, float y, int w, int h, float rot)
	{
		super(x, y, w, h, rot);
	}

	/**
	 * Construct a new StationaryEntity with the given initial parameters and the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new StationaryEntity.
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
	public StationaryEntity(float x, float y, int w, int h, float rot, UUID id)
	{
		super(x, y, w, h, rot, id);
	}
	
	//TODO: Add StationaryEntity functionality!
}
