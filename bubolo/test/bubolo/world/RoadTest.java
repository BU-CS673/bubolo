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

public class RoadTest
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
	static Road road;

	/**
	 * An OpenGL context must be created so that the textures for the Road object can load
	 * properly. Without this, all tests will crash on Road construction.
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
				road = new Road(TARGET_X, TARGET_Y, TARGET_WIDTH, TARGET_HEIGHT, TARGET_ROT,
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
	public void constructRoad_NO_UUID()
	{
		Road road2 = new Road();
	}

	@Test
	public void constructRoad_UUID_ONLY()
	{
		Road road2 = new Road(TARGET_UUID);
		assertEquals("Road constructor with UUID sets the field correctly,", road.getId(), road2.getId());
	}

	@Test
	public void constructRoad_PARAM_NO_UUID()
	{
		Road road2 = new Road(TARGET_X, TARGET_Y, TARGET_WIDTH, TARGET_HEIGHT, TARGET_ROT);
		assertEquals("Road param constructor without UUID sets fields correctly,", true, EntityTestCase.matches_NO_UUID(road, road2));
	}
	
	@Test
	public void checkSprite(){
		assertNotEquals("Road is not using the default entity sprite.", road.getSpriteId(), new DummyEntity().getSpriteId());
	}

}
