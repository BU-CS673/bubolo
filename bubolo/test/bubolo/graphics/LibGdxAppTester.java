package bubolo.graphics;

import java.util.concurrent.atomic.AtomicBoolean;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * This is required to unit test the graphics code. Otherwise, multiple OpenGL and OpenAL
 * contexts may be created, which is not allowed. This should not be used outside
 * of tests.
 * 
 * @author BU673 - Clone Industries
 */
public class LibGdxAppTester extends ApplicationAdapter
{
	private static LwjglApplication app;
	private static AtomicBoolean ready = new AtomicBoolean(false);
	
	private static Object lock = new Object();
	
	synchronized public static void createApp()
	{
		createApp(false);
	}
	
	synchronized public static void createApp(boolean forceCreate)
	{
		if (app == null || forceCreate)
		{
			ready.set(false);
			LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
			cfg.title = "test";
			cfg.width = 400;
			cfg.height = 400;
			cfg.useGL20 = true;
			app = new LwjglApplication(new LibGdxAppTester(), cfg);
		}
		
		while (!ready.get())
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
		ready.set(true);
	}
}
