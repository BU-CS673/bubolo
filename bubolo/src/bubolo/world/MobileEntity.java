package bubolo.world;

import java.util.UUID;

/**
 * Basic class for DrawableEntities that move.
 * 
 * @author BU673 - Clone Industries
 */
public abstract class MobileEntity extends Entity
{
	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = -7956746424636939370L;

	/**
	 * Construct a new MobileEntity with a random UUID.
	 */
	public MobileEntity()
	{
		super();
	}

	/**
	 * Construct a new MobileEntity with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new MobileEntity.
	 */
	public MobileEntity(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construct a new MobileEntity with the given initial parameters and a random UUID.
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
	public MobileEntity(float x, float y, int w, int h, float rot)
	{
		super(x, y, w, h, rot);
	}

	/**
	 * Construct a new MobileEntity with the given initial parameters and the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new MobileEntity.
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
	public MobileEntity(float x, float y, int w, int h, float rot, UUID id)
	{
		super(x, y, w, h, rot, id);
	}
	
	//TODO: Add MobileEntity functionality!
}
