package bubolo;

import java.util.LinkedList;
import java.util.List;

import bubolo.graphics.Graphics;
import bubolo.world.GameWorld;
import bubolo.world.World;
import bubolo.controllers.*;

import bubolo.world.entity.concrete.Tank;
import bubolo.inputs.*;

/**
 * The Game: this is where the subsystems are initialized, as well as where
 * the main game loop is. 
 * @author BU CS673 - Clone Productions
 */
public class BuboloApplication implements GameApplication
{
	private int windowWidth;
	private int windowHeight;
	
	private Graphics graphics;
	private World world;
	/**
	 * the list of inputs that will be driving the game (i.e. user, network, A.I.)
	 */
	private List<Input> inputs;
	
	private long lastUpdate;
	
	private boolean ready;
	
	/**
	 * The number of game ticks (calls to <code>update</code>) per second.
	 */
	public static final int TICKS_PER_SECOND = 30;
	
	/**
	 * The number of milliseconds per game tick.
	 */
	public static final float MILLIS_PER_TICK = 1000 / TICKS_PER_SECOND;
	
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
		graphics = new Graphics(windowWidth, windowHeight);
		
		// TODO: we need a way to determine the size of the game map. Perhaps we can have a default constructor,
		// and then the map loader or creator could set the size.
		world = new GameWorld(500, 500);
		inputs = new LinkedList<Input>();
		/**
		 * create the user input that will drive the local tank
		 */
		inputs.add(new KeyboardInput(world.CreateAndAddEntity(Tank.class, TankController.class)));
		// TODO: add other systems here.
		
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
