package bubolo.ui;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A loading screen for the Bubolo game. Currently uses a spoofed loading bar to make it
 * look like we're doing actual game things.
 * 
 * @author BU CS673 - Clone Productions
 */
public class LoadingScreen extends JFrame
{
	private static final long serialVersionUID = 4414221517373441452L;
	
	private static String SPLASH_PATH = "res/splash/";
	private JLabel imglabel;
	private ImageIcon img;
	private static JProgressBar pbar;

	/**
	 * Creates the LoadingScreen object and shoots off a thread to integrate the progress
	 * bar.
	 */
	public LoadingScreen()
	{
		super("Splash");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setUndecorated(true);

		img = new ImageIcon(SPLASH_PATH + "bubolo_splash.jpg");

		imglabel = new JLabel(img);

		add(imglabel);
		setLayout(null);

		pbar = new JProgressBar();
		pbar.setMinimum(0);
		pbar.setMaximum(100);
		pbar.setStringPainted(true);
		pbar.setForeground(Color.LIGHT_GRAY);
		imglabel.setBounds(0, 0, 500, 500);
		add(pbar);
		pbar.setPreferredSize(new Dimension(310, 30));
		pbar.setBounds(0, 350, 500, 20);

		Thread spoofLoadingBar = new Thread() {
			@Override
			public void run()
			{
				int i = 0;
				while (i <= 100)
				{
					pbar.setValue(i);
					try
					{
						sleep(25);
					}
					catch (InterruptedException ex)
					{
						Logger.getLogger(LoadingScreen.class.getName()).log(Level.SEVERE, null, ex);
					}
					i++;
				}
			}
		};
		spoofLoadingBar.start();

	}
}
