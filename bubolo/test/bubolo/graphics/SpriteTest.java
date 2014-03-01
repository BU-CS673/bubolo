package bubolo.graphics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteTest
{
	private SpriteBatch batch;
	private Camera camera;
	
	private boolean isComplete;
	private boolean passed;
	
	@Before
	public void setUp()
	{	
		synchronized(LibGdxAppTester.getLock())
		{
			LibGdxAppTester.createApp();
			
			batch = new SpriteBatch();
			camera = new OrthographicCamera(100, 100);
			Graphics g = new Graphics(50, 500);
		}
	}
	
	@Test
	public void testDrawTextureRegion()
	{
		synchronized(LibGdxAppTester.getLock())
		{
			isComplete = false;
			passed = false;
			
			Gdx.app.postRunnable(new Runnable() {
				@Override
				public void run()
				{
					Sprite<?> sprite = new MockSpriteTextureRegion();
					batch.begin();
					sprite.draw(batch, camera, sprite.getDrawLayer());
					passed = true;
					isComplete = true;
				}
			});
	
			while (!isComplete)
			{
				Thread.yield();
			}
			
			assertTrue(passed);
		}
	}
	
	@Test
	public void testDrawTextureRegionWrongLayer()
	{
		Camera camera = new OrthographicCamera();
		SpriteBatch batch = new SpriteBatch();
		Sprite<?> sprite = new MockSpriteTextureRegion();
		sprite.draw(batch, camera, DrawLayer.TERRAIN_MODIFIERS);
	}
}
