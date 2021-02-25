package bubolo.integration;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.json.simple.parser.ParseException;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import bubolo.AbstractGameApplication;
import bubolo.GameApplication;
import bubolo.audio.Audio;
import bubolo.graphics.Graphics;
import bubolo.map.Parser;
import bubolo.net.Network;
import bubolo.net.NetworkSystem;
import bubolo.world.GameWorld;
import bubolo.world.World;
import bubolo.world.entity.Entity;
import bubolo.world.entity.StationaryElement;
import bubolo.world.entity.StationaryEntity;
import bubolo.world.entity.concrete.Grass;
import bubolo.world.entity.concrete.Mine;
import bubolo.world.entity.concrete.MineExplosion;
import bubolo.world.entity.concrete.Pillbox;
import bubolo.world.entity.concrete.Spawn;
import bubolo.world.entity.concrete.Tank;

/**
 * For testing only.
 * 
 * @author BU CS673 - Clone Productions
 */
public class MineControllerTestApplication extends AbstractGameApplication
{
	public static void main(String[] args)
	{
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "BUBOLO Mine Controller Integration";
		cfg.width = 1067;
		cfg.height = 600;
		new LwjglApplication(new MineControllerTestApplication(1067, 600), cfg);
	}
	
	private int windowWidth;
	private int windowHeight;
	
	private Graphics graphics;
	
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
	public MineControllerTestApplication(int windowWidth, int windowHeight)
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
		
		Network net = NetworkSystem.getInstance();
		net.startDebug();
		
		graphics = new Graphics(windowWidth, windowHeight);
		Parser fileParser = Parser.getInstance();
		Path path = FileSystems.getDefault().getPath("res", "maps/TankSpeedTest.json");
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
		//StationaryElement pillbox = (StationaryElement)world.addEntity(Pillbox.class).setParams(32*9, 32*6, 0);
		//world.getMapTiles()[9-1][9-1].setElement(pillbox);
		world.addEntity(Mine.class).setParams(32*18, 32*6, 0);
		world.addEntity(Mine.class).setParams(32*18, 32*12, 0);
		world.addEntity(Mine.class).setParams(32*9, 32*12, 0);
		
		world.addEntity(Spawn.class).setParams(100, 100, 0);
		
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
