package bubolo.world;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import bubolo.world.Entity;

public class EntityTest
{
	static final UUID TARGET_UUID = UUID.fromString("5231b533-ba17-4787-98a3-f2df37de2aD7"); // random
																								// UUID
																								// string
	static final float TARGET_X = 26.7f;
	static final float TARGET_Y = 72.5f;
	static final float TARGET_ROT = (float) Math.PI / 2;
	static final int TARGET_WIDTH = 50;
	static final int TARGET_HEIGHT = 100;
	boolean LOAD_TEXTURES = false;
	static Entity ent;

	@Before
	public void setUpApp()
	{
		ent = new DummyEntity(TARGET_X, TARGET_Y, TARGET_WIDTH, TARGET_HEIGHT, TARGET_ROT,
				TARGET_UUID);
	}

	/**
	 * Tests to see if the initial parameters of this Entity are equivalent to those of
	 * another Entity.
	 * 
	 * @param e
	 *            is the Entity that this one should be compared against.
	 * @return true if the Entities match each other and false if they do not.
	 */
	public boolean matches(Entity e)
	{
		if (!e.getId().equals(ent.getId()) || e.getX() != ent.getX() || e.getY() != ent.getY()
				|| e.getWidth() != ent.getWidth() || e.getHeight() != ent.getHeight()
				|| e.getRotation() != ent.getRotation())
		{
			return false;
		}
		else
			return true;
	}

	/**
	 * Intended to ensure that Entities constructed or set with the same values are
	 * equivalent. Also tests inheritance of constructed values from subclasses of Entity.
	 */
	@Test
	public void constructorMatch()
	{
		Entity ent2 = new DummyEntity(TARGET_UUID);
		ent2.setX(TARGET_X);
		ent2.setY(TARGET_Y);
		ent2.setWidth(TARGET_WIDTH);
		ent2.setHeight(TARGET_HEIGHT);
		ent2.setRotation(TARGET_ROT);

		assertEquals("Entities constructed in different ways match.", true, matches(ent2));
	}

	@Test
	public void constructEntityUUID()
	{
		assertEquals("UUID of new Entity consistent with input UUID.", TARGET_UUID, ent.getId());
	}

	@Test
	public void getX()
	{
		assertEquals("Entity x position matches target.", TARGET_X, ent.getX(), .0001);
	}

	@Test
	public void getY()
	{
		assertEquals("Entity y position matches target.", TARGET_Y, ent.getY(), .0001);
	}

	@Test
	public void getRotation()
	{
		assertEquals("Entity rotation matches target.", TARGET_Y, ent.getY(), .0001);
	}

	@Test
	public void getWidth()
	{
		assertEquals("Entity xSize matches target.", TARGET_WIDTH, ent.getWidth());
	}

	@Test
	public void getHeight()
	{
		assertEquals("Entity xSize matches target.", TARGET_HEIGHT, ent.getHeight());
	}

}
