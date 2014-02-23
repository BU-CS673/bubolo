package bubolo.graphics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

import bubolo.world.entity.concrete.Road;
import bubolo.world.entity.concrete.Tank;

public class TankSpriteTest
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
	public void constructTankSprite() throws InterruptedException
	{
		synchronized(LibGdxAppTester.getLock())
		{
			isComplete = false;
			passed = false;
			
			Gdx.app.postRunnable(new Runnable() {
				@Override
				public void run()
				{
					// Fails if the constructor throws an exception.
					Sprite<?> sprite = Sprites.getInstance().createSprite(new Tank());
					
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
	public void drawTankSprite()
	{
		synchronized(LibGdxAppTester.getLock())
		{
			isComplete = false;
			passed = false;
			
			Gdx.app.postRunnable(new Runnable() {
				@Override
				public void run()
				{
					Sprite<?> sprite = Sprites.getInstance().createSprite(new Tank());
					batch.begin();
					sprite.draw(batch, camera, DrawLayer.TANKS);
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
}
