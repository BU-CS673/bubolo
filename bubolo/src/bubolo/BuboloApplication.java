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
import bubolo.ui.Screen;
import bubolo.util.GameRuntimeException;
import bubolo.util.Parser;
import bubolo.world.World;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Tank;

/**
 * The Game: this is where the subsystems are initialized, as well as where
 * the main game loop is. 
 * @author BU CS673 - Clone Productions
 */
public class BuboloApplication extends AbstractGameApplication
{
	private int windowWidth;
	private int windowHeight;
	
	private Graphics graphics;

	private String playerName;
	
	private Network network;

	private Screen gameLobby;
	
	/**
	 * Constructs an instance of the game application. Only one instance should 
	 * ever exist.
	 * @param windowWidth the width of the window.
	 * @param windowHeight the height of the window.
	 */
	public BuboloApplication(int windowWidth, int windowHeight)
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
		Audio.initialize();
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
			throw new GameRuntimeException(e);
		}

		network = NetworkSystem.getInstance();
		network.startServer(playerName);

		setState(State.GAME_LOBBY);
	}
	
	/**
	 * Called automatically by the rendering library.
	 * @see <a href="http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/ApplicationListener.html">ApplicationListener</a>
	 */
	@Override
	public void render()
	{
		final State state = getState();
		if (state == State.GAME)
		{
			gameLobby.dispose();

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
	public void onStateChanged()
	{
		if (getState() == State.GAME)
		{
			gameLobby.dispose();

			Tank tank = world.addEntity(Tank.class);
			Vector2 spawnLocation = getRandomSpawn(world);
			tank.setParams(spawnLocation.x, spawnLocation.y, 0);
			tank.setLocalPlayer(true);
			network.send(new CreateTank(tank));

			setReady(true);
		}
		else if (getState() == State.GAME_LOBBY)
		{
			gameLobby = new LobbyScreen(this, world);
		}
	}
	
	/**
	 * Returns a random spawn point.
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
	
	/**
	 * Called when the application is destroyed.
	 * @see <a href="http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/ApplicationListener.html">ApplicationListener</a>
	 */
	@Override
	public void dispose()
	{
		Audio.dispose();
	}
}
