package prototype;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

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
		try
		{
			app.start();
		}
		catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public void start() throws UnknownHostException, IOException
	{
		try (Socket socket = new Socket("whois.internic.net", 43))
		{
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			
			String requestText = "christopherdcanfield.com\n";
			byte buffer[] = requestText.getBytes();
			
			out.write(buffer);
			
			int c = 0;
			while ((c = in.read()) != -1)
			{
				System.out.print((char)c);
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{

	}
}
