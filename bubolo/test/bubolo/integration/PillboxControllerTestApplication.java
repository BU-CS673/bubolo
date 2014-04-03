package bubolo.integration;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import bubolo.GameApplication;
import bubolo.audio.Audio;
import bubolo.graphics.Graphics;
import bubolo.net.Network;
import bubolo.net.NetworkSystem;
import bubolo.world.GameWorld;
import bubolo.world.World;
import bubolo.world.entity.concrete.Grass;
import bubolo.world.entity.concrete.Pillbox;
import bubolo.world.entity.concrete.Tank;

/**
 * For testing only.
 * 
 * @author BU CS673 - Clone Productions
 */
public class PillboxControllerTestApplication implements GameApplication
{
	public static void main(String[] args)
	{
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "BUBOLO Pillbox Controller Integration";
		cfg.width = 1067;
		cfg.height = 600;
		cfg.useGL20 = true;
		new LwjglApplication(new PillboxControllerTestApplication(1067, 600), cfg);
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
	public PillboxControllerTestApplication(int windowWidth, int windowHeight)
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
		
		graphics = new Graphics(windowWidth, windowHeight);
		
		world = new GameWorld(32*94, 32*94);
		
		for (int row = 0; row < 94; row++)
		{
			for (int column = 0; column < 94; column++)
			{
				world.addEntity(Grass.class).setParams(column * 32, row * 32, 0);
			}
		}
		
		Tank tank = world.addEntity(Tank.class);
		tank.setParams(100, 100, 0);
		tank.setLocalPlayer(true);
		world.addEntity(Pillbox.class).setParams(32*9, 32*6, 0);
		world.addEntity(Pillbox.class).setParams(32*18, 32*6, 0);
		world.addEntity(Pillbox.class).setParams(32*18, 32*12, 0);
		world.addEntity(Pillbox.class).setParams(32*9, 32*12, 0);

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
		
		// (cdc - 4/3/2014): Commented out, b/c update was being called twice. Additionally,
		// the game is extremely jittery when this is used instead of calling update continuously.
		
		// Ensure that the world is only updated as frequently as MILLIS_PER_TICK. 
//		long currentMillis = System.currentTimeMillis();
//		if (currentMillis > (lastUpdate + MILLIS_PER_TICK))
//		{
//			world.update();
//			lastUpdate = currentMillis;
//		}
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
