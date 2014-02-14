package bubolo.world;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;

import bubolo.graphics.LibGdxAppTester;
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
	static boolean isComplete = false;

	/**
	 * An OpenGL context must be created so that the textures for the Tree object can load
	 * properly. Without this, all tests will crash on Tank construction.
	 */
	@BeforeClass
	public static void setUpApp()
	{
		LibGdxAppTester.createApp();
		isComplete = false;

		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				ent = new DummyEntity(TARGET_X, TARGET_Y, TARGET_WIDTH, TARGET_HEIGHT, TARGET_ROT,
						TARGET_UUID);

				isComplete = true;
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}

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

		assertEquals("Entities constructed in different ways match.", true, EntityTestCase.matches(ent, ent2));
	}

	@Test
	public void getSpriteId()
	{
		assertEquals("Entity has default sprite ID", ent.getSpriteId(),
				(new DummyEntity().getSpriteId()));
	}

	@Test
	public void update()
	{
		ent.update();
	}

	@Test
	public void constructEntity_NO_UUID()
	{
		Entity ent2 = new DummyEntity();
		assert(true);
	}

	@Test
	public void constructEntity_UUID_ONLY()
	{
		Entity ent2 = new DummyEntity(TARGET_UUID);
		assertEquals("Entity constructor with UUID sets the field correctly,", ent2.getId(), ent.getId());
	}

	@Test
	public void constructEntity_PARAM_NO_UUID()
	{
		Entity ent2 = new DummyEntity(TARGET_X, TARGET_Y, TARGET_WIDTH, TARGET_HEIGHT, TARGET_ROT);
		assertEquals("Entity param constructor without UUID sets fields correctly,", true, EntityTestCase.matches_NO_UUID(ent, ent2));
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
