package bubolo.graphics;

import bubolo.GameApplication;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * This is required to test the graphics code. Otherwise, multiple OpenGL and OpenAL
 * contexts may be created, which is not allowed. This should not be used outside
 * of tests.
 * @author BU673 - Clone Industries
 */
public class LibGdxAppTester extends ApplicationAdapter
{
	private static LwjglApplication app;
	private static boolean ready;
	
	private static Object lock = new Object();
	
	synchronized public static void createApp()
	{
		if (app == null)
		{
			ready = false;
			LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
			cfg.title = "test";
			cfg.width = 400;
			cfg.height = 400;
			app = new LwjglApplication(new LibGdxAppTester(), cfg);
		}
		
		while (!ready)
		{
			Thread.yield();
		}
	}
	
	synchronized public static void createApp(GameApplication ga)
	{
		if (app == null)
		{
			ready = false;
			LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
			cfg.title = "test";
			cfg.width = 400;
			cfg.height = 400;
			app = new LwjglApplication(ga, cfg);
		}
		
		while (!ga.isReady())
		{
			Thread.yield();
		}
	}
	
	public static Object getLock()
	{
		return lock;
	}
	
	@Override
	public void create()
	{
		ready = true;
	}
}
