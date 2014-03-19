package bubolo.integration;

import bubolo.ui.LoadingScreen;
import bubolo.ui.MenuScreen;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class TextureProcessing
{
	static TextureProcessingApp app = new TextureProcessingApp(300, 300);
	
	public static void main(String[] args)
	{
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "BUBOLO Sprint 1";
		cfg.width = 600;
		cfg.height = 600;
		cfg.useGL20 = true;
		new LwjglApplication(app, cfg);
	}
}
