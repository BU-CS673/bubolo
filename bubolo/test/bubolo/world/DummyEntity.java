package bubolo.world;

import java.util.UUID;

import bubolo.graphics.DrawLayer;
import bubolo.graphics.MockSprite;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Dummy Entity class for unit testing methods without any Graphics involvement. This
 * allows the tests to run outside of an OpenGL environment, because no textures are
 * fetched.
 * 
 * @author BU CS673 - Clone Productions
 */
public class DummyEntity extends Entity
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = -2381816338619957177L;

	/**
	 * Construct a new DummyEntity with a random UUID.
	 */
	public DummyEntity()
	{
		super();
		
	}

	/**
	 * Construct a new DummyEntity with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new DummyEntity.
	 */
	public DummyEntity(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construct a new DummyEntity with the given initial parameters and a random UUID.
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
	public DummyEntity(float x, float y, int w, int h, float rot)
	{
		super(x, y, w, h, rot);
	}

	/**
	 * Construct a new DummyEntity with the given initial parameters and the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new DummyEntity.
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
	public DummyEntity(float x, float y, int w, int h, float rot, UUID id)
	{
		super(x, y, w, h, rot, id);
	}

}
