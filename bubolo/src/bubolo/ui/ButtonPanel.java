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

public class ButtonPanel extends JPanel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 12134123L;	
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
		
	JButton newGameBtn = new JButton("NEW GAME");
	JButton prefBtn = new JButton("PREFERENCES");
	JButton exitBtn = new JButton("EXIT");
		
	setLayout(new GridLayout(3,1));
	
	// Set Button Sizes
	newGameBtn.setPreferredSize(new Dimension(125,40));
	prefBtn.setPreferredSize(new Dimension(125,40));		
	exitBtn.setPreferredSize(new Dimension(125,40));

	
	// Add Buttons to panel
	add(newGameBtn);
	add(prefBtn);
	add(exitBtn);
	
	// Handle newGameBtn push
	newGameBtn.addActionListener(new java.awt.event.ActionListener()
	{
		@Override
		public void actionPerformed(java.awt.event.ActionEvent evt)
		{
			newGameBtnPerformed(evt);
		}
	});

	// Handle exitGameBtn push
	exitBtn.addActionListener(new java.awt.event.ActionListener()
	{
		@Override
		public void actionPerformed(java.awt.event.ActionEvent evt)
		{
			System.exit(0);
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
	private void newGameBtnPerformed(java.awt.event.ActionEvent evt)
	{
		(new Thread(BUBOLOTarget)).start();
	}
	
}