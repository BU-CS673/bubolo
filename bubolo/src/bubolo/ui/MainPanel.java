package bubolo.ui;

import java.awt.Component;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The Main Panel for the Main Menu JFrame, should contain Title/Company and maybe a nifty graphic?
 * @author BU673 - Clone Industries
 */
public class MainPanel extends JPanel
{
	private static final long serialVersionUID = 3979824711855571791L;
	
	private String title = "B.U.B.O.L.O.";
	private String author = "Clone Productions";
	
	/**
	 * Constructor for the Main Panel
	 */
	public MainPanel()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JLabel titleLabel = new JLabel(title);
		JLabel authorLabel = new JLabel(author);
		
		titleLabel.setFont(new Font("Courier", Font.BOLD, 32));
		authorLabel.setFont(new Font("Courier", Font.BOLD, 20));
		
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.add(titleLabel);
		this.add(authorLabel);
		
	}
}