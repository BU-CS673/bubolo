package bubolo;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

import org.json.simple.parser.ParseException;

import com.badlogic.gdx.math.Vector2;

import bubolo.audio.Audio;
import bubolo.graphics.Graphics;
import bubolo.net.Network;
import bubolo.net.NetworkSystem;
import bubolo.net.command.CreateTank;
import bubolo.ui.LobbyScreen;
import bubolo.ui.PlayerInfoScreen;
import bubolo.ui.Screen;
import bubolo.util.GameRuntimeException;
import bubolo.util.Parser;
import bubolo.world.GameWorld;
import bubolo.world.World;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Tank;

/**
 * The Game: this is where the subsystems are initialized, as well as where the main game loop is.
 * 
 * @author BU CS673 - Clone Productions
 */
public class BuboloApplication extends AbstractGameApplication
{
	private final int windowWidth;
	private final int windowHeight;

	private final boolean isClient;
	private final State initialState;

	private Graphics graphics;

	private Network network;

	private Screen screen;

	/**
	 * Constructs an instance of the game application. Only one instance should ever exist.
	 * 
	 * @param windowWidth
	 *            the width of the window.
	 * @param windowHeight
	 *            the height of the window.
	 * @param isClient
	 *            specifies whether this is a client player.
	 * @param initialState
	 *            the initial application state.
	 */
	public BuboloApplication(int windowWidth, int windowHeight, boolean isClient,
			State initialState)
	{
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.isClient = isClient;
		this.initialState = initialState;
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
		Audio.initialize();
		graphics = new Graphics(windowWidth, windowHeight);
		network = NetworkSystem.getInstance();

		if (!isClient)
		{
			Parser fileParser = Parser.getInstance();
			Path path = FileSystems.getDefault().getPath("res", "maps/Everard Island.json");
			try
			{
				world = fileParser.parseMap(path);
			}
			catch (ParseException | IOException e)
			{
				e.printStackTrace();
				throw new GameRuntimeException(e);
			}
		}
		else
		{
			world = new GameWorld();
		}

		setState(initialState);
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
		if (state == State.NET_GAME)
		{
			graphics.draw(world);
			world.update();
			network.update(world);
		}
		else if (state == State.GAME)
		{
			graphics.draw(world);
			world.update();
		}
		else if (state == State.GAME_LOBBY ||
				state == State.GAME_STARTING)
		{
			graphics.draw(screen);
			network.update(world);
		}
		else if (state == State.PLAYER_INFO)
		{
			graphics.draw(screen);
		}
	}

	@Override
	public void onStateChanged()
	{
		if (getState() == State.NET_GAME)
		{
			screen.dispose();

			Tank tank = world.addEntity(Tank.class);
			if (!isClient)
			{
				Vector2 spawnLocation = getRandomSpawn(world);
				tank.setParams(spawnLocation.x, spawnLocation.y, 0);
			}
			else
			{
				tank.setParams(getRandomX(), 200, 0);
			}
			tank.setLocalPlayer(true);

			network.send(new CreateTank(tank));

			setReady(true);
		}
		else if (getState() == State.GAME)
		{
			if (screen != null)
			{
				screen.dispose();
			}

			Tank tank = world.addEntity(Tank.class);
			Vector2 spawnLocation = getRandomSpawn(world);
			tank.setParams(spawnLocation.x, spawnLocation.y, 0);
			tank.setLocalPlayer(true);

			network.startDebug();
			
			setReady(true);
		}
		else if (getState() == State.GAME_LOBBY)
		{
			screen = new LobbyScreen(this, world);
		}
		else if (getState() == State.PLAYER_INFO)
		{
			screen = new PlayerInfoScreen(this, isClient);
		}
	}

	/**
	 * Returns a random spawn point.
	 * 
	 * @return the location of a random spawn point.
	 */
	private static Vector2 getRandomSpawn(World world)
	{
		List<Entity> spawns = world.getSpawns();
		if (spawns.size() > 0)
		{
			Random randomGenerator = new Random();
			Entity spawn = spawns.get(randomGenerator.nextInt(spawns.size()));
			return new Vector2(spawn.getX(), spawn.getY());
		}
		return null;
	}
	
	private static int getRandomX()
	{
		int val = (new Random()).nextInt(10);
		return (1250 + (100 * val));
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
}
