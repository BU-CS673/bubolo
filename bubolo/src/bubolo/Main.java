package bubolo;

import bubolo.GameApplication.State;
import bubolo.ui.AltMenuScreen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * The application's entry point.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Main
{
	private static Application application;

	/**
	 * The application's entry point.
	 * 
	 * @param args
	 *            Unused. Arguments passed on startup will be ignored.
	 */
	public static void main(String[] args)
	{
		Runnable serverApplication = new Runnable() {
			@Override
			public void run()
			{
				LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
				cfg.title = "B.U.B.O.L.O";
				cfg.width = 1067;
				cfg.height = 600;
				setApplication(new LwjglApplication(new BuboloApplication(1067, 600, false,
						State.PLAYER_INFO), cfg));
			}
		};

		Runnable clientApplication = new Runnable() {
			@Override
			public void run()
			{
				LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
				cfg.title = "B.U.B.O.L.O";
				cfg.width = 1067;
				cfg.height = 600;
				setApplication(new LwjglApplication(new BuboloApplication(1067, 600, true,
						State.PLAYER_INFO), cfg));
			}
		};

		Runnable singlePlayerApplication = new Runnable() {
			@Override
			public void run()
			{
				LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
				cfg.title = "B.U.B.O.L.O";
				cfg.width = 1067;
				cfg.height = 600;
				setApplication(new LwjglApplication(new BuboloApplication(1067, 600, false,
						State.GAME), cfg));
			}
		};

		AltMenuScreen menuScreen = new AltMenuScreen(singlePlayerApplication, serverApplication,
				clientApplication);
		menuScreen.setVisible(true);
	}

	/**
	 * Sets the game application.
	 * 
	 * @param application
	 *            the game application.
	 */
	static void setApplication(Application application)
	{
		Main.application = application;
	}

	/**
	 * Returns a reference to the application.
	 * 
	 * @return the game application.
	 */
	static Application getApplication()
	{
		return Main.application;
	}
}
