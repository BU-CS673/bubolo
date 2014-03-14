package bubolo.audio;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.graphics.LibGdxAppTester;

import com.badlogic.gdx.Gdx;

public class MusicTest
{
	static boolean isComplete = false;
	static boolean passed = false;
	
	@BeforeClass
	public static void setUp()
	{	
		LibGdxAppTester.createApp(true);
	}
	
	@Test
	public void startStopMusic()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				try {
					Audio.startMusic();
					Audio.stopMusic();
					passed = true;
					isComplete = true;
				} catch (Exception e) {
					System.out.println(e);
					isComplete = true;
					fail("Exception in startStopMusic");
				}
			}
		});
		
		while (!isComplete)
		{
			Thread.yield();
		}
		
		assertTrue(passed);
	}
}
