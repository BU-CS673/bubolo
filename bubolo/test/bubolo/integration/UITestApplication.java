package bubolo.integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;

import bubolo.net.Network;
import bubolo.net.NetworkSystem;
import bubolo.ui.AltMenuScreen;
import bubolo.ui.LoadingScreen;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * Test the UI
 * 
 * @author BU CS673 - Clone Productions
 */
public class UITestApplication
{
	/**
	 * New Application
	 */

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
			Runnable sp = new Runnable() {
				@Override
				public void run()
				{
					setupSinglePlayer();
				}
			};

			Runnable hmp = new Runnable() {
				@Override
				public void run()
				{
					// do nothing
				}
			};

			Runnable jmp = new Runnable() {
				@Override
				public void run()
				{
					// do nothing
				}
			};

			AltMenuScreen fp = new AltMenuScreen(sp, hmp, jmp);

			fp.setVisible(true);
		}
		catch (InterruptedException ex)
		{
			ex.printStackTrace();
		}
	}

	public static void setupSinglePlayer()
	{
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "BUBOLO Tank Controller Integration";
		cfg.width = 1067;
		cfg.height = 600;
		new LwjglApplication(new CollisionTestApplication(1067, 600), cfg);
	}

	public static void setupHostMultiPlayer()
	{
		// Do nothing interesting, for now!
		System.out.println("Hosting multiplayer games is not possible from within this test!");
	}

	public static void setupJoinMultiPlayer() throws IOException
	{
		// Do nothing interesting, for now!
		System.out.println("Joining multiplayer games is not possible from within this test!");
	}
}
