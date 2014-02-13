package bubolo.world;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import bubolo.graphics.Graphics;
import bubolo.graphics.LibGdxAppTester;
import bubolo.graphics.MockSprite;
import bubolo.world.Entity;
import bubolo.world.Tank;
import bubolo.world.EntityTest;

public class GrassTest
{

	static final UUID TARGET_UUID = UUID.fromString("5231b533-ba17-4787-98a3-f2df37de2aD7"); // random
	// UUID
	// string
	static final float TARGET_X = 26.7f;
	static final float TARGET_Y = 72.5f;
	static final float TARGET_ROT = (float) Math.PI / 2;
	static final int TARGET_WIDTH = 50;
	static final int TARGET_HEIGHT = 100;

	static boolean isComplete = false;
	static Grass grass;

	/**
	 * An OpenGL context must be created so that the textures for the Grass object can load
	 * properly. Without this, all tests will crash on Grass construction.
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
				grass = new Grass(TARGET_X, TARGET_Y, TARGET_WIDTH, TARGET_HEIGHT, TARGET_ROT,
						TARGET_UUID);

				isComplete = true;
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}

	}

	@Test
	public void constructGrass_NO_UUID()
	{
		Grass grass2 = new Grass();
		assert(true);
	}

	@Test
	public void constructGrass_UUID_ONLY()
	{
		Grass grass2 = new Grass(TARGET_UUID);
		assertEquals("Grass constructor with UUID sets the field correctly,", grass.getId(), grass2.getId());
	}

	@Test
	public void constructGrass_PARAM_NO_UUID()
	{
		Grass grass2 = new Grass(TARGET_X, TARGET_Y, TARGET_WIDTH, TARGET_HEIGHT, TARGET_ROT);
		assertEquals("Grass param constructor without UUID sets fields correctly,", true, EntityTestCase.matches_NO_UUID(grass, grass2));
	}
	
	@Test
	public void checkSprite(){
		assertNotEquals("Grass is not using the default entity sprite.", grass.getSpriteId(), new DummyEntity().getSpriteId());
	}

}
