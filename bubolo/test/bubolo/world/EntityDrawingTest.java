package bubolo.world;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bubolo.graphics.DrawLayer;
import bubolo.graphics.Graphics;
import bubolo.graphics.LibGdxAppTester;
import bubolo.world.Entity;
import bubolo.world.Tank;

public class EntityDrawingTest
{

	static boolean isComplete = false;
	static Entity ent;
	static SpriteBatch batch;
	static Camera camera;

	/**
	 * Create an OpenGL context, make a DummyEntity object, and try to draw it to the screen. 
	 * To make this test useful, we need proper integration of Graphics and World code, and a way to determine whether the Entity is displaying correctly.
	 * For now, this is just a check to make sure trying to call Entity.draw() doesn't crash the program.
	 */
	@Test
	public void drawingTest()
	{
		LibGdxAppTester.createApp();
		isComplete = false;
		
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera(100, 100);

		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				ent = new DummyEntity();
				batch.begin();
				ent.draw(batch, camera, DrawLayer.OBJECTS);

				isComplete = true;
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}

	}
}
