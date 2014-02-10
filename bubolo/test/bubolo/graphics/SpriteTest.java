package bubolo.graphics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;

import bubolo.world.Tank;

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
		assertTrue("Exception thrown when creating sprite.", !hadException);
	}
}
