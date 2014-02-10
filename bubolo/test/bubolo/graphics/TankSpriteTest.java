package bubolo.graphics;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

import bubolo.world.Tank;

public class TankSpriteTest
{
	private SpriteBatch batch;
	private Camera camera;
	private static boolean isComplete;
	
	@Before
	public void setUp()
	{	
		LibGdxAppTester.createApp();
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera(100, 100);
	}
	
	@Test
	public void constructTankSprite() throws InterruptedException
	{
		isComplete = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				// Fails if the constructor throws an exception.
				Sprite<Tank> sprite = new TankSprite(new Tank());
				isComplete = true;
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}
	}

	@Test
	public void drawTankSprite()
	{
		isComplete = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				if (batch == null)
				{
					batch = new SpriteBatch();
				}
				if (camera == null)
				{
					camera = new OrthographicCamera();
				}
				Sprite<Tank> sprite = new TankSprite(new Tank());
				batch.begin();
				sprite.draw(batch, camera, DrawLayer.TANKS);
				isComplete = true;
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}
	}
}
