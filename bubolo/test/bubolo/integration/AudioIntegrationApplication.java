package bubolo.integration;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import bubolo.GameApplication;
import bubolo.audio.Audio;
import bubolo.audio.Sfx;
import bubolo.graphics.Graphics;
import bubolo.ui.LoadingScreen;
import bubolo.ui.MenuScreen;
import bubolo.world.GameWorld;
import bubolo.world.World;
import bubolo.world.entity.concrete.Grass;
import bubolo.world.entity.concrete.Tank;

/**
 * For testing only.
 * 
 * @author BU CS673 - Clone Productions
 */
public class AudioIntegrationApplication implements GameApplication
{
	public static void main(String[] args)
	{
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "BUBOLO Sprint 1";
		cfg.width = 600;
		cfg.height = 600;
		cfg.useGL20 = true;
		new LwjglApplication(new AudioIntegrationApplication(300, 300), cfg);
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
	public static final float MILLIS_PER_TICK = 1000 / TICKS_PER_SECOND;
	
	/**
	 * Constructs an instance of the game application. Only one instance should 
	 * ever exist.
	 * @param windowWidth the width of the window.
	 * @param windowHeight the height of the window.
	 */
	public AudioIntegrationApplication(int windowWidth, int windowHeight)
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

		world = new GameWorld(32*30, 32*30);
		
		Audio.startMusic();
		
		ready = true;
	}
	
	/**
	 * Called automatically by the rendering library.
	 * @see <a href="http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/ApplicationListener.html">ApplicationListener</a>
	 */
	@Override
	public void render()
	{
		Audio.play(Sfx.CANNON_FIRED);
		Audio.play(Sfx.ENGINEER_KILLED);
		Audio.play(Sfx.EXPLOSION);
		Audio.play(Sfx.PILLBOX_BUILT);
		Audio.play(Sfx.PILLBOX_HIT);
		Audio.play(Sfx.ROAD_BUILT);
		Audio.play(Sfx.TANK_DROWNED);
		Audio.play(Sfx.TANK_HIT);
		Audio.play(Sfx.TANK_IN_SHALLOW_WATER);
		Audio.play(Sfx.TREE_GATHERED);
		Audio.play(Sfx.TREE_HIT);
		Audio.play(Sfx.WALL_BUILT);
		Audio.play(Sfx.WALL_HIT);
		
		graphics.draw(world);
		
		try
		{
			Thread.sleep(1250);
		}
		catch (InterruptedException e)
		{
		}
		
		Gdx.app.exit();
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
