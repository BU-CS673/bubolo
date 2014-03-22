package bubolo.ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import bubolo.ui.Preferences.PreferencesController;
import bubolo.ui.Preferences.PreferencesModel;
import bubolo.ui.Preferences.PreferencesView;

/**
 * A panel with Main Menu Buttons
 * @author BU673 - Clone Industries
 */
public class ButtonPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1624357058854582729L;
	
	/**
	 * Preferences Model used by the game 
	 */
	public PreferencesModel pm = new PreferencesModel();
	
	/**
	 * Preference View used by the game
	 */
	public PreferencesView pv = new PreferencesView(pm);
	
	/**
	 * Preferences Controller used by the game 
	 */
	public PreferencesController pc = new PreferencesController(pm, pv);
	
	/**
	 * The Runnable that should be activated when the Start menu button is pressed.
	 */
	Runnable BUBOLOTarget; 	
	
	/**
	 * Constructor for the Main Menu Button Panel
	 * @param targetApp the BUBOLO application
	 */
	public ButtonPanel(Runnable targetApp)
	{
	/**
	 * 
	 */
	BUBOLOTarget = targetApp;
	setLayout(new GridLayout(5,1));
	
	// Create set of JButtons to be displayed on the Main Menu
	JButton singleBtn = new JButton("NEW SINGLE PLAYER GAME");
	JButton newMPBtn = new JButton("NEW MULTI-PLAYER GAME");
	JButton joinMPBtn = new JButton("JOIN MULTI-PLAYER GAME");
	JButton prefBtn = new JButton("PREFERENCES");
	JButton exitBtn = new JButton("EXIT");
	
	
	// TODO: Enable these buttons later when the features are implemented
	newMPBtn.setEnabled(false);
	joinMPBtn.setEnabled(false);
		
	// Add the buttons to this panel
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
			singleBtnPerformed();
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
			pc.showView();
		}
	});
	
	}
	
	//public void actionPerformed
	
	/**
	 * Starts the game by creating a new Thread and running the Runnable passed into the
	 * Constructor. For most implementations, this will launch the primary game thread.
	 */
	private void singleBtnPerformed()
	{
		(new Thread(BUBOLOTarget)).start();
	}
	
}