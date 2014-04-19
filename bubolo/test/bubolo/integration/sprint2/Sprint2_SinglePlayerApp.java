package bubolo.integration.sprint2;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.json.simple.parser.ParseException;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import bubolo.GameApplication;
import bubolo.audio.Audio;
import bubolo.graphics.Graphics;
import bubolo.net.Network;
import bubolo.net.NetworkSystem;
import bubolo.util.Parser;
import bubolo.world.World;
import bubolo.world.entity.concrete.Tank;

public class Sprint2_SinglePlayerApp implements GameApplication
{
	public static void main(String[] args)
	{
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "BUBOLO Tank Controller Integration";
		cfg.width = 1067;
		cfg.height = 600;
		cfg.useGL20 = true;
		new LwjglApplication(new Sprint2_SinglePlayerApp(1067, 600), cfg);
	}

	private Network network;
	private int windowWidth;
	private int windowHeight;

	private Graphics graphics;
	private World world;

	private long lastUpdate = 0;

	private boolean ready;

	/**
	 * The number of game ticks (calls to <code>update</code>) per second.
	 */
	public static final int TICKS_PER_SECOND = 300;

	/**
	 * The number of milliseconds per game tick.
	 */
	public static final float MILLIS_PER_TICK = 1000 / TICKS_PER_SECOND;

	/**
	 * Constructs an instance of the game application. Only one instance should ever
	 * exist.
	 * 
	 * @param windowWidth
	 *            the width of the window.
	 * @param windowHeight
	 *            the height of the window.
	 */
	public Sprint2_SinglePlayerApp(int windowWidth, int windowHeight)
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
		network.startDebug();

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

		Tank tank = world.addEntity(Tank.class);
		tank.setParams(100, 100, 0);
		tank.setLocalPlayer(true);
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
		// Ensure that the world is only updated as frequently as MILLIS_PER_TICK.
		long currentMillis = System.currentTimeMillis();
		if (currentMillis > (lastUpdate + (long)MILLIS_PER_TICK))
		{
			//graphics.draw(world);
			//world.update();
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
}
