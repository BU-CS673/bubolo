package bubolo.world.entity;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.Entity;

public class EntityTest
{
	static Entity ent;

	/**
	 * Create an Entity and set its initial parameters.
	 */
	@BeforeClass
	public static void setUpApp()
	{

		ent = new MockEntity(EntityTestCase.TARGET_UUID);
		EntityTestCase.setTestParams(ent);
	}

	@Test
	public void setParams()
	{
		assertEquals("Entity parameters set correctly.", true,
				ent.getX() == EntityTestCase.TARGET_X && ent.getY() == EntityTestCase.TARGET_Y
						&& ent.getWidth() == EntityTestCase.TARGET_WIDTH
						&& ent.getHeight() == EntityTestCase.TARGET_HEIGHT
						&& ent.getRotation() == EntityTestCase.TARGET_ROT);

	}

	@Test
	public void update()
	{
		ent.update();
	}

	@Test
	public void getId()
	{
		assertEquals("UUID of new Entity consistent with input UUID.", EntityTestCase.TARGET_UUID,
				ent.getId());
	}

	@Test
	public void getX()
	{
		assertEquals("Entity x position matches target.", EntityTestCase.TARGET_X, ent.getX(),
				.0001);
	}

	@Test
	public void getY()
	{
		assertEquals("Entity y position matches target.", EntityTestCase.TARGET_Y, ent.getY(),
				.0001);
	}

	@Test
	public void getRotation()
	{
		assertEquals("Entity rotation matches target.", EntityTestCase.TARGET_ROT,
				ent.getRotation(), .0001);
	}

	@Test
	public void getWidth()
	{
		assertEquals("Entity width matches target.", EntityTestCase.TARGET_WIDTH, ent.getWidth());
	}

	@Test
	public void getHeight()
	{
		assertEquals("Entity height matches target.", EntityTestCase.TARGET_HEIGHT, ent.getHeight());
	}
	
	@Test
	public void isDestroyed()
	{
		Entity e = new MockEntity();
		assertFalse(e.isDestroyed());
	}

	@Test
	public void destroyTest()
	{
		ent.destroy();
		assertTrue(ent.isDestroyed());
	}
}
