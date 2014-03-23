package bubolo.integration;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import bubolo.GameApplication;
import bubolo.audio.Audio;
import bubolo.graphics.Graphics;
import bubolo.net.Network;
import bubolo.net.NetworkSystem;
import bubolo.net.command.CreateEntity;
import bubolo.net.command.HelloNetworkCommand;
import bubolo.world.GameWorld;
import bubolo.world.World;
import bubolo.world.entity.concrete.Grass;
import bubolo.world.entity.concrete.Tank;

/**
 * For testing only.
 * 
 * @author BU CS673 - Clone Productions
 */
public class NetClientTestApplication implements GameApplication
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Server IP Address: ");
        String addressString = br.readLine();
        InetAddress address = Inet4Address.getByName(addressString);
        
        Network net = NetworkSystem.getInstance();
        net.connect(address);
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "BUBOLO Net Client Integration";
		cfg.width = 1067;
		cfg.height = 600;
		cfg.useGL20 = true;
		new LwjglApplication(new NetClientTestApplication(1067, 600, address), cfg);
	}
	
	private int windowWidth;
	private int windowHeight;
	
	private Graphics graphics;
	private World world;
	private Network network;
	
	private long lastUpdate;
	
	private boolean ready;
	
	private InetAddress serverAddress;
	
	/**
	 * The number of game ticks (calls to <code>update</code>) per second.
	 */
	public static final int TICKS_PER_SECOND = 30;
	
	/**
	 * The number of milliseconds per game tick.
	 */
	public static final float MILLIS_PER_TICK = 500 / TICKS_PER_SECOND;
	
	/**
	 * Constructs an instance of the game application. Only one instance should 
	 * ever exist.
	 * @param windowWidth the width of the window.
	 * @param windowHeight the height of the window.
	 * @param serverAddress the server's ip address.
	 */
	public NetClientTestApplication(int windowWidth, int windowHeight, InetAddress serverAddress)
	{
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;

		this.serverAddress = serverAddress;
	}
	
	@Override
	public boolean isReady()
	{
		return ready;
	}

	/**
	 * Create anything that relies on graphics, sound, windowing, or input devices here.
	 * @see <a href="http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/ApplicationListener.html">ApplicationListener</a> 
	 */
	@Override
	public void create()
	{
		network = NetworkSystem.getInstance();
		network.send(new HelloNetworkCommand("Hello from the client."));
		
		graphics = new Graphics(windowWidth, windowHeight);
		
		world = new GameWorld(32*94, 32*94);
		
		for (int row = 0; row < 94; row++)
		{
			for (int column = 0; column < 94; column++)
			{
				world.addEntity(Grass.class).setParams(column * 32, row * 32, 32, 32, 0);
			}
		}
		
		Tank tank = world.addEntity(Tank.class);
		tank.setParams(400, 100, 32, 32, 0);
		
		network.send(new CreateEntity(Tank.class, tank.getId(), tank.getX(), tank.getY(), tank.getRotation()));
		
		ready = true;
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
		
		// Ensure that the world is only updated as frequently as MILLIS_PER_TICK. 
		long currentMillis = System.currentTimeMillis();
		if (currentMillis > (lastUpdate + MILLIS_PER_TICK))
		{
			world.update();
			lastUpdate = currentMillis;
		}
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
}
