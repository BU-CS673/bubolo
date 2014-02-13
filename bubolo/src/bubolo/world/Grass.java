package bubolo.world;

import java.util.UUID;

/**
 * Grass is the standard Terrain of B.U.B.O.L.O., and offers no special movement effects.
 * @author BU CS673 - Clone Productions
 */
public class Grass extends Terrain
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = 5319713357245800006L;

	/**
	 * Construct a new Grass with a random UUID.
	 */
	public Grass()
	{
		super();
		//sprite = Sprite.create(this);
		// TODO: Create GrassSprite class!
	}

	/**
	 * Construct a new Grass with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Grass.
	 */
	public Grass(UUID id)
	{
		super(id);
		//sprite = Sprite.create(this);
		// TODO: Create GrassSprite class!
	}

	/**
	 * Construct a new Grass with the given initial parameters and a random UUID.
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
	public Grass(float x, float y, int w, int h, float rot)
	{
		super(x, y, w, h, rot);
		//sprite = Sprite.create(this);
		// TODO: Create GrassSprite class!
	}

	/**
	 * Construct a new Grass with the given initial parameters and the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Grass.
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
	public Grass(float x, float y, int w, int h, float rot, UUID id)
	{
		super(x, y, w, h, rot, id);
		//sprite = Sprite.create(this);
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub

	}

	//TODO: Add Grass functionality!
}
