package bubolo.world;

import java.util.UUID;
import bubolo.graphics.Sprite;

/**
 * The tank, which may be controlled by a local player, a networked player, or an AI bot.
 * 
 * @author BU673 - Clone Industries
 */
public class Tank extends Actor
{
	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = 457933513574468829L;

	/**
	 * Construct a new Tank with a random UUID.
	 */
	public Tank()
	{
		super();
		sprite = Sprite.create(this);
	}

	/**
	 * Construct a new Tank with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tank.
	 */
	public Tank(UUID id)
	{
		super(id);
		sprite = Sprite.create(this);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construct a new Tank with the given initial parameters and a random UUID.
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
	public Tank(float x, float y, int w, int h, float rot)
	{
		super(x, y, w, h, rot);
		sprite = Sprite.create(this);
	}

	/**
	 * Construct a new Tank with the given initial parameters and the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tank.
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
	public Tank(float x, float y, int w, int h, float rot, UUID id)
	{
		super(x, y, w, h, rot, id);
		sprite = Sprite.create(this);
	}

	@Override
	public void update()
	{
		// TODO Determine update behavior for Tanks.

	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub

	}

	// TODO: Add Tank functionality!
}
