package bubolo.integration;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import bubolo.AbstractGameApplication;
import bubolo.audio.Audio;
import bubolo.graphics.Graphics;
import bubolo.net.Network;
import bubolo.net.NetworkObserver;
import bubolo.net.NetworkSystem;
import bubolo.net.command.CreateTank;
import bubolo.net.command.HelloNetworkCommand;
import bubolo.util.Parser;
import bubolo.world.GameWorld;
import bubolo.world.entity.concrete.Tank;

/**
 * For testing only.
 * 
 * @author BU CS673 - Clone Productions
 */
public class NetServerTestApplication extends AbstractGameApplication implements NetworkObserver
{
	public static void main(String[] args) throws UnknownHostException
	{
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "BUBOLO Net Server Integration";
		cfg.width = 1067;
		cfg.height = 600;
		cfg.useGL20 = true;
		new LwjglApplication(new NetServerTestApplication(1067, 600), cfg);
	}
	
	private final int windowWidth;
	private final int windowHeight;
	
	private Graphics graphics;
	private Network network;
	
	/**
	 * The number of game ticks (calls to <code>update</code>) per second.
	 */
	public static final long TICKS_PER_SECOND = 30;
	
	/**
	 * The number of milliseconds per game tick.
	 */
	public static final long MILLIS_PER_TICK = 1000 / TICKS_PER_SECOND;
	
	/**
	 * Constructs an instance of the game application. Only one instance should 
	 * ever exist.
	 * @param windowWidth the width of the window.
	 * @param windowHeight the height of the window.
	 */
	public NetServerTestApplication(int windowWidth, int windowHeight)
	{
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
	}

	/**
	 * Create anything that relies on graphics, sound, windowing, or input devices here.
	 * @see <a href="http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/ApplicationListener.html">ApplicationListener</a> 
	 */
	@Override
	public void create()
	{
		graphics = new Graphics(windowWidth, windowHeight);
		world = new GameWorld(32*94, 32*94);
		
		Parser fileParser = Parser.getInstance();
		Path path = FileSystems.getDefault().getPath("res", "maps/Everard Island.json");
		try
		{
			world = fileParser.parseMap(path);
		}
		catch (ParseException | IOException e)
		{
			e.printStackTrace();
		}
		
		network = NetworkSystem.getInstance();
		network.addObserver(this);
		network.startServer("Server");
		
		int response = JOptionPane.showConfirmDialog(null,
				"Click OK to start the game.",
				"Start Game",
				JOptionPane.OK_CANCEL_OPTION);
		
		if (response == JOptionPane.CANCEL_OPTION)
		{
			Gdx.app.exit();
		}
		network.startGame(world);
		
		network.send(new HelloNetworkCommand("Hello from the server."));
		
		Tank tank = world.addEntity(Tank.class);
		tank.setParams(1100, 100, 0);
		tank.setLocalPlayer(true);
		network.send(new CreateTank(tank));
		
		setReady(true);
	}
	
	/**
	 * Called automatically by the rendering library.
	 * @see <a href="http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/ApplicationListener.html">ApplicationListener</a>
	 */
	@Override
	public void render()
	{
		graphics.draw(world);
		world.update();
		network.update(world);
	}
	
	/**
	 * Called when the application is destroyed.
	 * @see <a href="http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/ApplicationListener.html">ApplicationListener</a>
	 */
	@Override
	public void dispose()
	{
		Audio.dispose();
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void resume()
	{
	}

	@Override
	public void onConnect(String serverName)
	{
		System.out.println("Connected to server. The host is " + serverName);
	}

	@Override
	public void onClientConnected(String clientName)
	{
		System.out.println(clientName + " joined the game.");
	}

	@Override
	public void onClientDisconnected(String clientName)
	{
		System.out.println(clientName + " left the game.");
	}

	@Override
	public void onGameStart(int timeUntilStart)
	{
		System.out.println("Game is starting.");
	}
}
