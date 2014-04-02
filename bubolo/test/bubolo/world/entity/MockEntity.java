package bubolo.world.entity;

import java.util.UUID;

import bubolo.world.World;
import bubolo.world.entity.Entity;

/**
 * Dummy Entity class for unit testing methods without any Graphics involvement. This allows the
 * tests to run outside of an OpenGL environment, because no textures are fetched.
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
	public MockEntity(UUID id)
	{
		super(id);
	}

	/**
	 * Construct a new DummyEntity with the specified UUID.
	 */
	public MockEntity()
	{
		this(UUID.randomUUID());
	}

	@Override
	public void update(World world)
	{
		// Do nothing
	}

}
