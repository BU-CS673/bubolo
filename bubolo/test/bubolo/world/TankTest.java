package bubolo.world;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import bubolo.graphics.Graphics;
import bubolo.graphics.LibGdxAppTester;
import bubolo.world.Entity;
import bubolo.world.Tank;

public class TankTest
{

	static boolean isComplete = false;
	static Entity tank;

	/**
	 * An OpenGL context must be created so that the textures for the Tank object can load properly.
	 * Without this, all tests will crash on Tank construction.
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
				tank = new Tank();

				isComplete = true;
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}

	}

	@Test
	public void getX()
	{
		// TODO: Fix this test once Entity.getX() is properly implemented.
		tank.setX(0);
		assertEquals(0, (int) tank.getX());
	}

	@Test
	public void getY()
	{
		tank.setY(0);
		assertEquals(0, (int) tank.getY());
	}
}
