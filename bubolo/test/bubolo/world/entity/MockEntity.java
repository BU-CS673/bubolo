package bubolo.world.entity;

import java.util.UUID;

import bubolo.graphics.DrawLayer;
import bubolo.graphics.MockSprite;
import bubolo.world.entity.Entity;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Dummy Entity class for unit testing methods without any Graphics involvement. This
 * allows the tests to run outside of an OpenGL environment, because no textures are
 * fetched.
 * 
 * @author BU CS673 - Clone Productions
 */
public class MockEntity extends Entity
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = -2381816338619957177L;

	/**
	 * Construct a new DummyEntity with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new DummyEntity.
	 */
	public MockEntity(UUID id, MockSprite<?> m)
	{
		super(id);
		sprite = m;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construct a new DummyEntity with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new DummyEntity.
	 */
	public MockEntity(MockSprite<?> m)
	{
		super();
		sprite = m;
		// TODO Auto-generated constructor stub
	}

}
