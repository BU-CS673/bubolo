package bubolo.integration.sprint2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import bubolo.audio.Audio;
import bubolo.integration.CollisionTestApplication;
import bubolo.integration.NetClientTestApplication;
import bubolo.integration.NetServerTestApplication;
import bubolo.net.Network;
import bubolo.net.NetworkSystem;
import bubolo.ui.AltMenuScreen;
import bubolo.ui.LoadingScreen;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

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
	static JFrame IP_popup;

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
		if (IP_popup != null){
			IP_popup.setVisible(false);
		}
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "BUBOLO v0.2.0";
		cfg.width = 1067;
		cfg.height = 600;
		cfg.useGL20 = true;
		new LwjglApplication(new Sprint2_SinglePlayerApp(1067, 600), cfg);
	}

	public static void setupHostMultiPlayer()
	{
		fp.setVisible(false);
		if (IP_popup != null){
			IP_popup.setVisible(false);
		}
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "BUBOLO v0.2.0, Multiplayer Server";
		cfg.width = 1067;
		cfg.height = 600;
		cfg.useGL20 = true;
		new LwjglApplication(new Sprint2_HostMultiPlayerApp(1067, 600), cfg);
	}

	public static void setupJoinMultiPlayer() throws IOException
	{
		//Set IP Address dialog
		boolean gotIP = false;
		final JPanel IP_panel = new JPanel(new BorderLayout());
		final JTextField textField = new JTextField("192.168.1.1");
		IP_panel.add(textField);
		JButton joinButton = new JButton("Join Game");
		IP_panel.add(joinButton, BorderLayout.SOUTH);
		
		IP_popup = new JFrame("Enter Server IP Address");

		IP_popup.setLocationRelativeTo(fp);
		IP_popup.setSize(250, 100);
		IP_popup.setContentPane(IP_panel);
	
		IP_popup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		IP_panel.setVisible(true);
		IP_popup.setVisible(true);

		joinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String addressString;
				addressString = textField.getText();

				InetAddress address = null;
				try
				{
					address = Inet4Address.getByName(addressString);
					Network net = NetworkSystem.getInstance();
					net.connect(address, "Client");
					LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
					cfg.title = "BUBOLO v0.2.0, Multiplayer Client";
					cfg.width = 1067;
					cfg.height = 600;
					cfg.useGL20 = true;
					new LwjglApplication(new Sprint2_JoinMultiPlayerApp(1067, 600), cfg);
					IP_popup.setVisible(false);
					fp.setVisible(false);
				}
				catch (UnknownHostException e1)
				{
					e1.printStackTrace();
				}

			}
		});

	}
}
