package bubolo.integration;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Sprint1
{
	static Sprint1Application app = new Sprint1Application(300, 300);
	
	public static void main(String args[])
	{
			LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
			cfg.title = "test";
			cfg.width = 600;
			cfg.height = 600;
			cfg.useGL20 = true;
			new LwjglApplication(app, cfg);
	}

	
}
