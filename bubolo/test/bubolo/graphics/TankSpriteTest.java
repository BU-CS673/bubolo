package bubolo.graphics;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import bubolo.world.Tank;

public class TankSpriteTest extends ApplicationAdapter
{
	private SpriteBatch batch;
	private Camera camera;
	private static LwjglApplication app;
	private static boolean isReady;
	private static boolean isComplete;
	
	@BeforeClass
	public static void setUp()
	{
		isReady = false;
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "test";
		cfg.width = 400;
		cfg.height = 400;
		app = new LwjglApplication(new TankSpriteTest(), cfg);
	}
	
	@AfterClass
	public static void tearDown()
	{
		app = null;
	}
	
	@Test
	public void constructTankSprite() throws InterruptedException
	{
		isComplete = false;
		while (!isReady)
		{
			Thread.yield();
		}
		
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
		while (!isReady)
		{
			Thread.yield();
		}
		
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

	@Override
	public void create()
	{
		batch = new SpriteBatch();
		camera = new OrthographicCamera(100, 100);
		isReady = true;
	}

	@Override
	public void dispose()
	{
		batch.dispose();
	}

	@Override
	public void render()
	{
		// TODO Auto-generated method stub
		
	}
}
