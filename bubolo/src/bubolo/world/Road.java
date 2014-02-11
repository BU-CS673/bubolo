package bubolo.world;

import java.util.UUID;

import bubolo.graphics.DrawLayer;
import bubolo.graphics.Sprite;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Roads are Modifiers for Terrain that allow Tanks to drive more quickly. They can be
 * created and destroyed by Tanks.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Road extends Modifier
{
	Sprite<Road> sprite;

	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = -5302600252810938564L;

	/**
	 * Construct a new Road with a random UUID.
	 */
	public Road()
	{
		super();
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
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construct a new Road with the given initial parameters and a random UUID.
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
	public Road(float x, float y, int w, int h, float rot)
	{
		super(x, y, w, h, rot);
	}

	/**
	 * Construct a new Road with the given initial parameters and the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Road.
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
	public Road(float x, float y, int w, int h, float rot, UUID id)
	{
		super(x, y, w, h, rot, id);
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		// TODO: Create RoadSprite class!
		// sprite.draw(batch, camera, layer);
	}

	@Override
	public UUID getSpriteId()
	{
		// TODO: Create RoadSprite class!
		// return sprite.getId();

		return null;
	}

	// TODO: Add Road functionality!
}
