package bubolo.integration;

import bubolo.ui.LoadingScreen;
import bubolo.ui.MenuScreen;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Sprint1
{
	static Sprint1Application app = new Sprint1Application(300, 300);

	public static void main(String[] args)
	{

		LoadingScreen s = new LoadingScreen();
		s.setVisible(true);
		Thread t = Thread.currentThread();
		try
		{
			Thread.sleep(2750);

			s.dispose();
			MenuScreen fp = new MenuScreen(new Runnable() {
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
