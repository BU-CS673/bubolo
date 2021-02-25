package bubolo.integration.sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.json.simple.parser.ParseException;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import bubolo.GameApplication;
import bubolo.audio.Audio;
import bubolo.graphics.Graphics;
import bubolo.map.Parser;
import bubolo.net.Network;
import bubolo.net.NetworkSystem;
import bubolo.net.command.CreateTank;
import bubolo.net.command.HelloNetworkCommand;
import bubolo.world.World;
import bubolo.world.entity.concrete.Tank;

/**
 * For testing only.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Sprint2_JoinMultiPlayerApp implements GameApplication
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Server IP Address: ");
		String addressString = br.readLine();
		InetAddress address = Inet4Address.getByName(addressString);

		Network net = NetworkSystem.getInstance();
		net.connect(address, "Client");

		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "BUBOLO Net Client Integration";
		cfg.width = 1067;
		cfg.height = 600;
		new LwjglApplication(new Sprint2_JoinMultiPlayerApp(1067, 600), cfg);
	}

	private int windowWidth;
	private int windowHeight;

	private Graphics graphics;
	private World world;
	private Network network;

	private long lastUpdate;

	private boolean ready;
	

	/**
	 * The number of game ticks (calls to <code>update</code>) per second.
	 */
	public static final int TICKS_PER_SECOND = 30;

	/**
	 * The number of milliseconds per game tick.
	 */
	public static final float MILLIS_PER_TICK = 500 / TICKS_PER_SECOND;

	/**
	 * Constructs an instance of the game application. Only one instance should ever
	 * exist.
	 * 
	 * @param windowWidth
	 *            the width of the window.
	 * @param windowHeight
	 *            the height of the window.
	 */
	public Sprint2_JoinMultiPlayerApp(int windowWidth, int windowHeight)
	{
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
	}

	@Override
	public boolean isReady()
	{
		return ready;
	}

	/**
	 * Create anything that relies on graphics, sound, windowing, or input devices here.
	 * 
	 * @see <a
	 *      href="http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/ApplicationListener.html">ApplicationListener</a>
	 */
	@Override
	public void create()
	{
		network = NetworkSystem.getInstance();
		network.send(new HelloNetworkCommand("Hello from the client."));

		graphics = new Graphics(windowWidth, windowHeight);

		Parser fileParser = Parser.getInstance();
		Path path = FileSystems.getDefault().getPath("res", "maps/Everard Island.json");
		try
		{
			world = fileParser.parseMap(path);
		}
		catch (ParseException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (!isGameStarted())
		{
			network.update(world);
		}
		
		Tank tank = world.addEntity(Tank.class);
		tank.setParams(1250, 100, 0);
		tank.setLocalPlayer(true);

		network.send(new CreateTank(tank));
		Audio.startMusic();
		ready = true;
	}

	/**
	 * Called automatically by the rendering library.
	 * 
	 * @see <a
	 *      href="http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/ApplicationListener.html">ApplicationListener</a>
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
	 * 
	 * @see <a
	 *      href="http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/ApplicationListener.html">ApplicationListener</a>
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
	public boolean isGameStarted()
	{
		return world.getMapTiles() != null;
	}

	@Override
	public void setState(State state)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public State getState()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
