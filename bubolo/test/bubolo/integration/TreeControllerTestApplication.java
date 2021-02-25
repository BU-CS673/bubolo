package bubolo.integration;

import java.io.IOException;
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
import bubolo.world.World;


/**
 * For testing only.
 * 
 * @author BU CS673 - Clone Productions
 */
public class TreeControllerTestApplication implements GameApplication
{
	public static void main(String[] args)
	{
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "BUBOLO Tree Controller Integration";
		cfg.width = 640;
		cfg.height = 640;
	new LwjglApplication(new TreeControllerTestApplication(640, 640), cfg);
	}
	
	private int windowWidth;
	private int windowHeight;
	
	private Graphics graphics;
	private World world;
	
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
	 * Constructs an instance of the game application. Only one instance should 
	 * ever exist.
	 * @param windowWidth the width of the window.
	 * @param windowHeight the height of the window.
	 */
	public TreeControllerTestApplication(int windowWidth, int windowHeight)
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
	 * @see <a href="http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/ApplicationListener.html">ApplicationListener</a> 
	 */
	@Override
	public void create()
	{
		Network net = NetworkSystem.getInstance();
		net.startDebug();
		
		Audio.initialize();
		graphics = new Graphics(windowWidth, windowHeight);
		
		Parser fileParser = Parser.getInstance();
		Path path = FileSystems.getDefault().getPath("res", "maps/ParserTestMap.json");
		try
		{
			world = fileParser.parseMap(path);
		}
		catch (ParseException | IOException e )
		{
			e.printStackTrace();
		}
//		for (int row = 0; row < 94; row++)
//		{
//			for (int column = 0; column < 94; column++)
//			{
//				world.addEntity(Grass.class).setParams(column * 32, row * 32, 0);
//			}
//		}
//		Tree tree = world.addEntity(Tree.class);
//		tree.setParams(320, 320, 0);

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
	public boolean isGameStarted() {
		// TODO Auto-generated method stub
		return false;
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
