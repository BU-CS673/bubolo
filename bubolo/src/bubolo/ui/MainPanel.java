package bubolo.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
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
	
	int cx, cy = 50;
	
	
	/**
	 * Constructor for the Main Panel
	 */
	public MainPanel()
	{

	}
	
	/**
	 * Paints the Main Panel which has just Title/Company for now
	 */
	@Override
	public void paintComponent(Graphics g) {
	    Dimension si = this.getSize();
		
	    double width = si.getWidth();
	    	    
		super.paintComponent(g);
	    Font ft = new Font("Courier", Font.BOLD, 32);
	    FontMetrics fmt = g.getFontMetrics(ft);
	    cx = (((int)width / 2) - (fmt.stringWidth(title) / 2));
	    g.setFont(ft);
	    g.drawString(title, cx, cy);
	    
	    
	    Font fa = new Font("Courier", Font.BOLD, 20);
	    FontMetrics fma = g.getFontMetrics(fa);
	    cx = (((int)width / 2) - (fma.stringWidth(author) / 2));
	    cy += 30;
	    g.setFont(fa);
	    g.drawString(author, cx, cy);
	    
	}
}