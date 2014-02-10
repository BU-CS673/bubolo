package bubolo.graphics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

import bubolo.world.Tank;

public class SpriteTest
{
	private SpriteBatch batch;
	private Camera camera;
	private static boolean isComplete;
	private static boolean hadException;
	
	@Before
	public void setUp()
	{	
		LibGdxAppTester.createApp();
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera(100, 100);
	}
	
	@Test
	public void testValid() throws InterruptedException
	{
		isComplete = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				try
				{
					Sprite<Tank> sprite = Sprite.create(new Tank());
					hadException = false;
				}
				catch (Exception e)
				{
					hadException = true;
				}
				isComplete = true;
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}
	}
	
	@Test
	public void createInvalidArgument() throws InterruptedException
	{
		isComplete = false;
		hadException = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				// Fails if the constructor throws an exception.
				try
				{
					Sprite<?> sprite = Sprite.create(null);
					hadException = false;
				}
				catch (Exception e)
				{
					hadException = true;
					isComplete = true;
				}
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}
		assertTrue("Expected an exception, but none encountered", hadException);
	}
}
