/**
 *
 */

package bubolo.net.command;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;

import bubolo.graphics.Graphics;
import bubolo.graphics.LibGdxAppTester;
import bubolo.net.Network;
import bubolo.net.NetworkSystem;
import bubolo.test.MockWorld;
import bubolo.world.GameWorld;
import bubolo.world.Tile;
import bubolo.world.World;
import bubolo.world.entity.concrete.Grass;
import bubolo.world.entity.concrete.Water;

/**
 * @author BU CS673 - Clone Productions
 */
public class SendMapTest
{
	private boolean isComplete;
	private boolean passed;
	
	@Before
	public void setUp()
	{	
		LibGdxAppTester.createApp();
		
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				Graphics g = new Graphics(50, 500);
			}
		});
	}
	
	/**
	 * Test method for {@link bubolo.net.command.SendMap#execute(bubolo.world.World)}.
	 */
	@Test
	public void testExecute()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				World world = new GameWorld(32 * 4, 32 * 4);
				Tile[][] tiles = new Tile[2][2];
				tiles[0][0] = new Tile(0, 0, new Grass());
				tiles[0][1] = new Tile(0, 1, new Grass());
				tiles[1][0] = new Tile(1, 0, new Grass());
				tiles[1][1] = new Tile(0, 1, new Water());
				world.setMapTiles(tiles);
				
				Network net = NetworkSystem.getInstance();
				net.startDebug();
				try
				{
					SendMap sendMapCommand = new SendMap(world);
					sendMapCommand.execute(new GameWorld(32 * 4, 32 * 4));
					passed = true;
				}
				catch (Exception e)
				{
					passed = false;
				}
				finally {
					isComplete = true;
				}
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}
		
		assertTrue(passed);
	}
}
