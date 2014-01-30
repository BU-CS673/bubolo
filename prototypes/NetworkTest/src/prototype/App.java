package prototype;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Christopher D. Canfield
 */
public class App extends JPanel
{
	private JFrame frame;
	
	public static void main(String[] args)
	{
		App app = new App();
		app.start();
	}
	
	public App()
	{
		frame = new JFrame("Network Prototype");
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.setVisible(true);
	}
	
	public void start()
	{
		//frame.addKeyListener(controller);
		
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{

	}
}
