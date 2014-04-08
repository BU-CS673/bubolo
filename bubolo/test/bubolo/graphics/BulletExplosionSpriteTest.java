package bubolo.graphics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Test for GenericExplosion Sprite
 */

public class BulletExplosionSpriteTest
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
	public void drawSprite()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				Sprite<?> sprite = new BulletExplosionSprite(1, 1);
				
				try {
					batch.begin();
					sprite.draw(batch, camera, sprite.getDrawLayer());
					passed = true;
				} catch (Exception e) {
					e.printStackTrace();
					passed = false;	
				} finally {
					batch.end();
					isComplete = true;
				}
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}
		
		assertTrue(passed);
	}

	@Test
	public void getX()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				Sprite<?> sprite = new BulletExplosionSprite(1, 1);
				passed = (sprite.getX() == 1.f);
				isComplete = true;
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}
		
		assertTrue(passed);
	}
	
	@Test
	public void getY()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				Sprite<?> sprite = new BulletExplosionSprite(1, 2);
				passed = (sprite.getY() == 2.f);
				isComplete = true;
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}
		
		assertTrue(passed);
	}
	
	@Test
	public void getHeight()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				Sprite<?> sprite = new BulletExplosionSprite(1, 1);
				passed = (sprite.getHeight() == 32);
				isComplete = true;
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}
		
		assertTrue(passed);
	}
	
	@Test
	public void getRotation()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				Sprite<?> sprite = new BulletExplosionSprite(1, 1);
				passed = (sprite.getRotation() == 0.f);
				isComplete = true;
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}
		
		assertTrue(passed);
	}
	
	@Test
	public void getWidth()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				Sprite<?> sprite = new BulletExplosionSprite(1, 1);
				passed = (sprite.getWidth() == 32);
				isComplete = true;
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}
		
		assertTrue(passed);
	}
	
	@Test
	public void isEntityDisposed()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				Sprite<?> sprite = new BulletExplosionSprite(1, 1);
				passed = (sprite.isEntityDisposed() == false);
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
