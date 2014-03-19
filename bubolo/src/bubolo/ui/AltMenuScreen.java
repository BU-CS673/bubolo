package bubolo.ui;

import javax.swing.*;

import bubolo.graphics.Graphics;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

public class AltMenuScreen extends JFrame
{
	private static final long serialVersionUID = -5355152035949516532L;
	
	/**
	 * The Runnable that should be activated when the Start menu button is pressed.
	 */ 
	Runnable BUBOLOTarget; 
	
	public AltMenuScreen(Runnable targetApp) throws InterruptedException
	{
	
		BUBOLOTarget = targetApp;
		
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		
		// Set the details of the Frame
		setTitle("B.U.B.O.L.O.");
		setSize(500,500);
		setLayout(new GridLayout(2,1));
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(Graphics.TEXTURE_PATH + "tank_icon.png").getImage());
		setResizable(false);
		
		// Add the MainPanel which contains our logo/title
		add(new MainPanel(), BorderLayout.NORTH);
		
		// Add the ButtonPanel which contains the main buttons
		add(new ButtonPanel(BUBOLOTarget), BorderLayout.CENTER);
			
	}
	
}
