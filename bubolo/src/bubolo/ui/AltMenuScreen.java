package bubolo.ui;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

/**
 * This is the Main Menu JFrame which will have Buttons for: START SINGLE PLAYER JOIN MULTI-PLAYER
 * (not functional) START MULTI-PLAYER (not functional) PREFERENCES EXIT
 * 
 * @author BU673 - Clone Industries
 */
public class AltMenuScreen extends JFrame
{
	private static final long serialVersionUID = -5355152035949516532L;

	/**
	 * Constructor for the Main Menu JFrame
	 * 
	 * @param singlePlayer
	 *            a runnable that launches the single player game.
	 * @param hostMultiPlayer
	 *            a runnable that launches the host multiplayer game.
	 * @param joinMultiPlayer
	 *            a runnable that launches the client multiplayer game.
	 */
	public AltMenuScreen(Runnable singlePlayer, Runnable hostMultiPlayer, Runnable joinMultiPlayer)
	{

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});

		// Set the details of the Frame
		setTitle("B.U.B.O.L.O.");
		setSize(500, 500);
		setLayout(new GridLayout(2, 1));
		setLocationRelativeTo(null);
		setIconImage(UserInterface.gameIcon.getImage());
		setResizable(false);

		// Add the MainPanel which contains our logo/title
		add(new MainPanel(), BorderLayout.NORTH);

		// Add the ButtonPanel which contains the main buttons
		add(new ButtonPanel(singlePlayer, hostMultiPlayer, joinMultiPlayer), BorderLayout.CENTER);
	}
}
