package bubolo;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


/**
 * The application's entry point.
 * @author BU CS673 - Clone Productions
 */
public class Main
{
	private static LwjglApplication application;
	
	/**
	 * The application's entry point. 
	 * @param args Unused. Arguments passed on startup will be ignored. 
	 */
	public static void main(String[] args)
	{
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "BUBOLO Audio Integration";
		cfg.width = 600;
		cfg.height = 600;
		setApplication(new LwjglApplication(new BuboloApplication(1067, 600), cfg));
	}
	
	/**
	 * Sets the game application.
	 * @param application the game application.
	 */
	static void setApplication(LwjglApplication application)
	{
		Main.application = application;
	}
	
	/**
	 * Returns a reference to the application.
	 * @return the game application.
	 */
	static LwjglApplication getApplication()
	{
		return Main.application;
	}
}
