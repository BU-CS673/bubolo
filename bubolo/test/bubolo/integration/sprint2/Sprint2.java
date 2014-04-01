package bubolo.integration.sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;

import bubolo.integration.CollisionTestApplication;
import bubolo.integration.NetClientTestApplication;
import bubolo.integration.NetServerTestApplication;
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
public class Sprint2
{
	static LoadingScreen s = new LoadingScreen();
	static AltMenuScreen fp;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{

		// s.setVisible(true);
		try
		{
			// s.dispose();
			Thread.sleep(0);

			// s.dispose();
			Runnable spRun = new Runnable() {
				@Override
				public void run()
				{
					sp();
				}
			};

			Runnable hmpRun = new Runnable() {
				@Override
				public void run()
				{
					hmp();
				}
			};

			Runnable jmpRun = new Runnable() {
				@Override
				public void run()
				{
					jmp();
				}
			};

			fp = new AltMenuScreen(spRun, hmpRun, jmpRun);

			fp.setVisible(true);
		}
		catch (InterruptedException ex)
		{
			ex.printStackTrace();
		}
	}

	public static void sp()
	{
		setupSinglePlayer();
	}

	public static void hmp()
	{
		setupHostMultiPlayer();
	}

	public static void jmp()
	{
		try
		{
			setupJoinMultiPlayer();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setupSinglePlayer()
	{
		fp.setVisible(false);
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "BUBOLO Tank Controller Integration";
		cfg.width = 1067;
		cfg.height = 600;
		cfg.useGL20 = true;
		new LwjglApplication(new Sprint2_SinglePlayerApp(1067, 600), cfg);
	}

	public static void setupHostMultiPlayer()
	{
		fp.setVisible(false);
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "BUBOLO Net Server Integration";
		cfg.width = 1067;
		cfg.height = 600;
		cfg.useGL20 = true;
		new LwjglApplication(new Sprint2_HostMultiPlayerApp(1067, 600), cfg);
	}

	public static void setupJoinMultiPlayer() throws IOException
	{
		fp.setVisible(false);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Server IP Address: ");
		String addressString;
		addressString = br.readLine();
		InetAddress address = Inet4Address.getByName(addressString);

		Network net = NetworkSystem.getInstance();
		net.connect(address);

		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "BUBOLO Net Client Integration";
		cfg.width = 1067;
		cfg.height = 600;
		cfg.useGL20 = true;
		new LwjglApplication(new Sprint2_JoinMultiPlayerApp(1067, 600, address), cfg);
	}
}
