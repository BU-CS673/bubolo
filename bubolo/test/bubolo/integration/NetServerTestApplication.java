package bubolo.integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import org.json.simple.parser.ParseException;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import bubolo.AbstractGameApplication;
import bubolo.audio.Audio;
import bubolo.graphics.Graphics;
import bubolo.net.Network;
import bubolo.net.NetworkObserver;
import bubolo.net.NetworkSystem;
import bubolo.net.command.CreateTank;
import bubolo.ui.LobbyScreen;
import bubolo.ui.Screen;
import bubolo.util.Parser;
import bubolo.world.entity.concrete.Tank;

/**
 * For testing only.
 * 
 * @author BU CS673 - Clone Productions
 */
public class NetServerTestApplication extends AbstractGameApplication implements NetworkObserver
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Name: ");
		String name = br.readLine();

		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "BUBOLO Net Server Integration";
		cfg.width = 1067;
		cfg.height = 600;
		new LwjglApplication(new NetServerTestApplication(1067, 600, name), cfg);
	}

	private final int windowWidth;
	private final int windowHeight;

	private final String playerName;

	private Graphics graphics;
	private Network network;

	private Screen gameLobby;

	/**
	 * The number of game ticks (calls to <code>update</code>) per second.
	 */
	public static final long TICKS_PER_SECOND = 30;

	/**
	 * The number of milliseconds per game tick.
	 */
	public static final long MILLIS_PER_TICK = 1000 / TICKS_PER_SECOND;

	/**
	 * Constructs an instance of the game application. Only one instance should ever exist.
	 * 
	 * @param windowWidth
	 *            the width of the window.
	 * @param windowHeight
	 *            the height of the window.
	 * @param name
	 *            the name of the player.
	 */
	public NetServerTestApplication(int windowWidth, int windowHeight, String name)
	{
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.playerName = name;
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
		graphics = new Graphics(windowWidth, windowHeight);

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
		network.startServer(playerName);

		setState(State.GAME_LOBBY);
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
		final State state = getState();
		if (state == State.GAME)
		{
			graphics.draw(world);
			world.update();
			network.update(world);
		}
		else if (state == State.GAME_LOBBY || state == State.GAME_STARTING)
		{
			graphics.draw(gameLobby);
			network.update(world);
		}
	}

	@Override
	protected void onStateChanged()
	{
		final State state = getState();
		if (state == State.GAME)
		{
			gameLobby.dispose();

			Tank tank = world.addEntity(Tank.class);
			tank.setParams(1100, 200, 0);
			tank.setLocalPlayer(true);
			network.send(new CreateTank(tank));

			setReady(true);
		}
		else if (state == State.GAME_LOBBY)
		{
			gameLobby = new LobbyScreen(this, world);
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
	public void onConnect(String clientName, String serverName)
	{
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
	}

	@Override
	public void onMessageReceived(String message)
	{
	}
}
