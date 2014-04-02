package bubolo.graphics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Crater;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CraterSpriteTest
{
	private SpriteBatch batch;
	private Camera camera;
	
	private boolean isComplete;
	private boolean passed;
	
	@Before
	public void setUp()
	{	
		LibGdxAppTester.createApp();
		
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				batch = new SpriteBatch();
				camera = new OrthographicCamera(100, 100);
				Graphics g = new Graphics(50, 500);
			}
		});
	}
	
	@Test
	public void constructCraterSprite() throws InterruptedException
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
					Sprite<?> sprite = Sprites.getInstance().createSprite(new Crater());
					
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
	public void drawCraterSprite()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				Sprite<?> sprite = Sprites.getInstance().createSprite(new Crater());
				batch.begin();
				sprite.draw(batch, camera, DrawLayer.TERRAIN);
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
