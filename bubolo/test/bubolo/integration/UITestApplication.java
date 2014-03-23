package bubolo.integration;

import bubolo.ui.AltMenuScreen;
import bubolo.ui.LoadingScreen;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * Test the UI
 * @author BU CS673 - Clone Productions
 */
public class UITestApplication
{
	/**
	 * New Application
	 */
	static Sprint1Application app = new Sprint1Application(300, 300);

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{

		LoadingScreen s = new LoadingScreen();
		s.setVisible(true);
		Thread t = Thread.currentThread();
		try
		{
			Thread.sleep(0);

			s.dispose();
			AltMenuScreen fp = new AltMenuScreen(new Runnable() {
				@Override
				public void run()
				{
					setup();
				}
			});

			fp.setVisible(true);
		}
		catch (InterruptedException ex)
		{
			ex.printStackTrace();
		}
	}
	

	public static void setup()
	{
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "BUBOLO Sprint 1";
		cfg.width = 600;
		cfg.height = 600;
		cfg.useGL20 = true;
		new LwjglApplication(app, cfg);
	}
}
