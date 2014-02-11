package bubolo.graphics;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

public class GraphicsTest extends ApplicationAdapter
{
	private static boolean isComplete;
	
	@Before
	public void setUp()
	{
		LibGdxAppTester.createApp();
	}
	
	@Test
	public void getTexture() throws InterruptedException
	{
		isComplete = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				Texture texture = Graphics.getTexture(Graphics.TEXTURE_PATH + "tank.png");
				assertNotNull(texture);
				
				isComplete = true;
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}
	}

	@Test
	public void disposeTest()
	{
		isComplete = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				Texture texture = Graphics.getTexture(Graphics.TEXTURE_PATH + "tank.png");
				Graphics.dispose();
				isComplete = true;
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}
	}

	
}
