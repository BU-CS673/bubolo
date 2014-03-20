package bubolo.ui;

//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.FontMetrics;
//import java.awt.Graphics;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.WindowEvent;




import javax.swing.JButton;
import javax.swing.JPanel;

import bubolo.ui.Preferences.PreferencesController;
import bubolo.ui.Preferences.PreferencesModel;
import bubolo.ui.Preferences.PreferencesView;

public class ButtonPanel extends JPanel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1624357058854582729L;
	PreferencesModel prefs;
	/**
	 * The Runnable that should be activated when the Start menu button is pressed.
	 */
	Runnable BUBOLOTarget; 
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO: Stuff
		
	}
	
	
	public ButtonPanel(Runnable targetApp) throws InterruptedException
	{
	/**
	 * 
	 */
	BUBOLOTarget = targetApp;
	this.prefs = new PreferencesModel();	
	JButton singleBtn = new JButton("NEW SINGLE PLAYER GAME");
	JButton newMPBtn = new JButton("NEW MULTI-PLAYER GAME");
	JButton joinMPBtn = new JButton("JOIN MULTI-PLAYER GAME");
	JButton prefBtn = new JButton("PREFERENCES");
	JButton exitBtn = new JButton("EXIT");
		
	setLayout(new GridLayout(5,1));
	
	// Set Button Sizes
	singleBtn.setPreferredSize(new Dimension(125,20));
	newMPBtn.setPreferredSize(new Dimension(125,20));
	joinMPBtn.setPreferredSize(new Dimension(125,20));
	prefBtn.setPreferredSize(new Dimension(125,20));		
	exitBtn.setPreferredSize(new Dimension(125,20));

	
	// Add Buttons to panel
	add(singleBtn);
	add(newMPBtn);
	add(joinMPBtn);
	add(prefBtn);
	add(exitBtn);
	
	// Handle singleBtn push
	singleBtn.addActionListener(new java.awt.event.ActionListener()
	{
		@Override
		public void actionPerformed(java.awt.event.ActionEvent evt)
		{
			singleBtnPerformed(evt);
		}
	});

	// Handle exitBtn push
	exitBtn.addActionListener(new java.awt.event.ActionListener()
	{
		@Override
		public void actionPerformed(java.awt.event.ActionEvent evt)
		{
			System.exit(0);
		}
	});
	
	
	// Handle prefBtn push
	prefBtn.addActionListener(new java.awt.event.ActionListener()
	{
		@Override
		public void actionPerformed(java.awt.event.ActionEvent evt)
		{
			PreferencesView ps = new PreferencesView(prefs);
			ps.setVisible(true);
		}
	});
	
	}
	
	/**
	 * Starts the game by creating a new Thread and running the Runnable passed into the
	 * Constructor. For most implementations, this will launch the primary game thread.
	 * 
	 * @param evt
	 *            is the event created by this button being pressed. Currently unused.
	 */
	private void singleBtnPerformed(java.awt.event.ActionEvent evt)
	{
		(new Thread(BUBOLOTarget)).start();
	}
	
}