package bubolo.graphics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;

import bubolo.world.entity.concrete.Grass;
import bubolo.world.entity.concrete.Road;
import bubolo.world.entity.concrete.Tank;
import bubolo.world.entity.concrete.Tree;

public class SpriteTest
{
	private static boolean isComplete;
	private static boolean hadException;
	
	@Before
	public void setUp()
	{	
		LibGdxAppTester.createApp();
	}
	
	@Test
	public void spriteCreateTank() throws InterruptedException
	{
		isComplete = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				try
				{
					Sprite<?> sprite = Sprite.create(new Tank());
					assertNotNull(sprite);
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
		assertFalse("Exception thrown when creating sprite.", hadException);
	}
	
	@Test
	public void spriteCreateTree() throws InterruptedException
	{
		isComplete = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				try
				{
					Sprite<?> sprite = Sprite.create(new Tree());
					assertNotNull(sprite);
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
		assertFalse("Exception thrown when creating sprite.", hadException);
	}
	
	@Test
	public void spriteCreateGrass() throws InterruptedException
	{
		isComplete = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				try
				{
					Sprite<?> sprite = Sprite.create(new Grass());
					assertNotNull(sprite);
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
		assertFalse("Exception thrown when creating sprite.", hadException);
	}
	
	@Test
	public void spriteCreateRoad() throws InterruptedException
	{
		isComplete = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				try
				{
					Sprite<?> sprite = Sprite.create(new Road());
					assertNotNull(sprite);
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
		assertFalse("Exception thrown when creating sprite.", hadException);
	}
}
