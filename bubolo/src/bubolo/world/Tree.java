package bubolo.world;

import java.util.UUID;

/**
 * Trees are StationaryElements that can spread over time, and hide Tanks that drive over them.
 * @author BU CS673 - Clone Productions
 */
public class Tree extends StationaryElement
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = 4072369464678115753L;

	/**
	 * Construct a new Tree with a random UUID.
	 */
	public Tree()
	{
		super();
		//sprite = Sprite.create(this);
	}

	/**
	 * Construct a new Tree with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tree.
	 */
	public Tree(UUID id)
	{
		super(id);
		//sprite = Sprite.create(this);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construct a new Tree with the given initial parameters and a random UUID.
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
	public Tree(float x, float y, int w, int h, float rot)
	{
		super(x, y, w, h, rot);
		//sprite = Sprite.create(this);
	}

	/**
	 * Construct a new Tree with the given initial parameters and the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tree.
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
	public Tree(float x, float y, int w, int h, float rot, UUID id)
	{
		super(x, y, w, h, rot, id);
		//sprite = Sprite.create(this);
	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub

	}

	// TODO: Add Tree functionality!
}
