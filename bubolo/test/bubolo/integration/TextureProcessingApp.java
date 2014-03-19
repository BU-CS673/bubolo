package bubolo.integration;

import bubolo.GameApplication;
import bubolo.graphics.Graphics;
import bubolo.world.GameWorld;
import bubolo.world.World;
import bubolo.world.entity.concrete.Base;
import bubolo.world.entity.concrete.Engineer;
import bubolo.world.entity.concrete.Mine;
import bubolo.world.entity.concrete.MineExplosion;
import bubolo.world.entity.concrete.Pillbox;
import bubolo.world.entity.concrete.Swamp;
import bubolo.world.entity.concrete.Tank;
import bubolo.world.entity.concrete.Grass;
import bubolo.world.entity.concrete.Crater;
import bubolo.world.entity.concrete.Road;
import bubolo.world.entity.concrete.Rubble;
import bubolo.world.entity.concrete.Wall;
import bubolo.world.entity.concrete.Water;

/**
 * For testing only.
 * 
 * @author BU CS673 - Clone Productions
 */
public class TextureProcessingApp implements GameApplication
{
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
	 * Constructs an instance of the game application. Only one instance should ever
	 * exist.
	 * 
	 * @param windowWidth
	 *            the width of the window.
	 * @param windowHeight
	 *            the height of the window.
	 */
	public TextureProcessingApp(int windowWidth, int windowHeight)
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
		graphics = new Graphics(windowWidth, windowHeight);

		// TODO: we need a way to determine the size of the game map. Perhaps we can have
		// a default constructor,
		// and then the map loader or creator could set the size.
		world = new GameWorld(32 * 30, 32 * 30);

		for (int i = 0; i < 30; i++)
		{
			for (int j = 0; j < 30; j++)
			{
				world.addEntity(Grass.class).setParams(i * 32, j * 32, 32, 32, 0);
			}
		}

		Water w1 = (Water) world.addEntity(Water.class).setParams(10 * 32, 13 * 32, 32, 32, 0);
		w1.setState(9);
		Water w2 = (Water) world.addEntity(Water.class).setParams(11 * 32, 13 * 32, 32, 32, 0);
		w2.setState(5);
		Water w3 = (Water) world.addEntity(Water.class).setParams(10 * 32, 14 * 32, 32, 32, 0);
		w3.setState(10);
		Water w4 = (Water) world.addEntity(Water.class).setParams(11 * 32, 14 * 32, 32, 32, 0);
		w4.setState(6);

		Road r1 = (Road) world.addEntity(Road.class).setParams(8 * 32, 13 * 32, 32, 32, 0);
		r1.setState(9);
		Road r2 = (Road) world.addEntity(Road.class).setParams(9 * 32, 13 * 32, 32, 32, 0);
		r2.setState(5);
		Road r3 = (Road) world.addEntity(Road.class).setParams(8 * 32, 14 * 32, 32, 32, 0);
		r3.setState(10);
		Road r4 = (Road) world.addEntity(Road.class).setParams(9 * 32, 14 * 32, 32, 32, 0);
		r4.setState(6);

		Wall wa1 = (Wall) world.addEntity(Wall.class).setParams(6 * 32, 13 * 32, 32, 32, 0);
		wa1.setState(9);
		Wall wa2 = (Wall) world.addEntity(Wall.class).setParams(7 * 32, 13 * 32, 32, 32, 0);
		wa2.setState(5);
		Wall wa3 = (Wall) world.addEntity(Wall.class).setParams(6 * 32, 14 * 32, 32, 32, 0);
		wa3.setState(10);
		Wall wa4 = (Wall) world.addEntity(Wall.class).setParams(7 * 32, 14 * 32, 32, 32, 0);
		wa4.setState(6);

		Crater cra1 = (Crater) world.addEntity(Crater.class).setParams(6 * 32, 11 * 32, 32, 32, 0);
		cra1.setState(9);
		Crater cra2 = (Crater) world.addEntity(Crater.class).setParams(7 * 32, 11 * 32, 32, 32, 0);
		cra2.setState(5);
		Crater cra3 = (Crater) world.addEntity(Crater.class).setParams(6 * 32, 12 * 32, 32, 32, 0);
		cra3.setState(10);
		Crater cra4 = (Crater) world.addEntity(Crater.class).setParams(7 * 32, 12 * 32, 32, 32, 0);
		cra4.setState(6);
		
		Base base = world.addEntity(Base.class);
		base.setParams(9 * 32, 2 * 32, 32, 32, 0);
		base.setOwned(true);
		base.setCharging(true);
		
		Base base2 = world.addEntity(Base.class);
		base2.setParams(8 * 32, 2 * 32, 32, 32, 0);
		base2.setOwned(true);
		base2.setLocalPlayer(false);
		base2.setCharging(false);
		
		Base base3 = world.addEntity(Base.class);
		base3.setParams(7 * 32, 2 * 32, 32, 32, 0);
		base3.setOwned(false);
		base3.setCharging(false);
		
		Mine mine = world.addEntity(Mine.class);
		mine.setParams(8 * 32, 4 * 32, 32, 32, 0);
		mine.setOwned(true);
		mine.setLocalPlayer(true);
		mine.setExploding(false);
		
		Mine mine2 = world.addEntity(Mine.class);
		mine2.setParams(9 * 32, 4 * 32, 32, 32, 0);
		mine2.setOwned(true);
		mine2.setExploding(true);
		mine.setLocalPlayer(false);
		
		
		Mine mine3 = world.addEntity(Mine.class);
		mine3.setParams(7 * 32, 4 * 32, 32, 32, 0);
		mine3.setOwned(false);
		mine3.setExploding(false);
		mine.setLocalPlayer(false);
		
		
		
		
		MineExplosion BOOM = world.addEntity(MineExplosion.class);
		BOOM.setParams(13 * 32, 15 * 32, 60, 60, 0);

		Pillbox pill = world.addEntity(Pillbox.class);
		pill.setParams(10 * 32, 5 * 32, 32, 32, 0);
		pill.setOwned(false);

		Engineer engi = world.addEntity(Engineer.class);
		engi.setParams(15 * 32, 15 * 32, 32, 32, 0);
		engi.setLocalPlayer(false);
		engi.setBuilding(true);

		Engineer engi2 = world.addEntity(Engineer.class);
		engi2.setParams(16 * 32, 15 * 32, 32, 32, 0);
		engi2.setLocalPlayer(true);
		engi2.setRunning(true);

		Tank tank1 = world.addEntity(Tank.class);
		tank1.setParams(12 * 32, 12 * 32, 32, 32, 0);
		tank1.setLocalPlayer(true);
		tank1.setDrivingForward(false);

		Tank tank2 = world.addEntity(Tank.class);
		tank2.setLocalPlayer(true);
		tank2.setDrivingForward(true);
		tank2.setParams(13 * 32, 12 * 32, 32, 32, 0);

		Tank tank3 = world.addEntity(Tank.class);
		tank3.setLocalPlayer(false);
		tank3.setDrivingForward(false);
		tank3.setParams(15 * 32, 12 * 32, 32, 32, 0);

		Tank tank4 = world.addEntity(Tank.class);
		tank4.setParams(16 * 32, 12 * 32, 32, 32, 0);
		tank4.setLocalPlayer(false);
		tank4.setDrivingForward(true);

		// TODO: add other systems here.

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
