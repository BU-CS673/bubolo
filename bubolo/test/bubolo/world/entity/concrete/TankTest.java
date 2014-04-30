package bubolo.world.entity.concrete;

import static org.junit.Assert.*;

import org.junit.BeforeClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.peer.LightweightPeer;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bubolo.graphics.Graphics;
import bubolo.graphics.LibGdxAppTester;
import bubolo.mock.MockBulletCreator;
import bubolo.mock.MockMineCreator;
import bubolo.mock.MockPillboxCreator;
import bubolo.mock.MockEngineerCreator;
import bubolo.world.GameWorld;
import bubolo.world.Tile;
import bubolo.world.World;
import bubolo.world.entity.Entity;
import bubolo.world.entity.EntityTestCase;
import static org.mockito.Mockito.*;

public class TankTest
{
	private Tank tank;
	private World world;
	private Grass grass;

	/**
	 * Constructs a Tank object and sets the default parameters.
	 */
	@Before
	public void setup()
	{
		world = new GameWorld(32, 32);
		tank = (Tank) world.addEntity(Tank.class).setParams(16, 16, 0);
		grass = new Grass();
		Tile[][] grassTile = new Tile[1][1];
		grassTile[0][0] = new Tile(0,0,grass);
		world.setMapTiles(grassTile);
		EntityTestCase.setTestParams(tank);
	}

	@Test
	public void isHidden()
	{
		assertEquals(false, tank.isHidden());
	}

	@Test
	public void getSpeed()
	{
		assertEquals(0, (int) tank.getSpeed());
	}

	@Test
	public void fireCannon()
	{
		LibGdxAppTester.createApp();
		tank.fireCannon(new MockBulletCreator(), 50, 40);
	}

	@Test
	public void accelerate()
	{
		float speed = tank.getSpeed();
		tank.accelerate();
		assertEquals(speed + 0.01f, tank.getSpeed(), 0.001f);
	}

	@Test
	public void decelerate()
	{
		assertEquals(0.f, tank.getSpeed(), 0.001f);

		tank.accelerate();
		float speed = (tank.getSpeed() - 0.02f < 0.f) ? 0.f : tank.getSpeed();
		tank.decelerate();
		assertEquals(speed, tank.getSpeed(), 0.001f);
	}

	@Test
	public void isCannonReady()
	{
		LibGdxAppTester.createApp();
		assertTrue(tank.isCannonReady());
		tank.fireCannon(new MockBulletCreator(), 50, 40);
		assertFalse(tank.isCannonReady());
	}

	@Test
	public void rotateLeft()
	{
		tank.setRotation((float) Math.PI / 2);
		float rotation = tank.getRotation();
		tank.rotateLeft();
		assertEquals((rotation - 0.05f), tank.getRotation(), 0.0001f);
	}

	@Test
	public void rotateRight()
	{
		tank.setRotation((float) Math.PI / 2);
		float rotation = tank.getRotation();
		tank.rotateRight();
		assertEquals(rotation + 0.05f, tank.getRotation(), 0.0001f);
	}
	
	@Test
	public void  getHitPoints()
	{
		assertEquals(100, tank.getHitPoints(), 0);
	}
	
	@Test
	public void getMaxHitPoints()
	{
		assertEquals(100, tank.getMaxHitPoints(), 0);
	}
	
	@Test
	public void getAmmoCount()
	{
		assertEquals(100, tank.getAmmoCount(), 0);
	}
	
	@Test
	public void getTreeCount()
	{
		assertEquals(0, tank.getTreeCount(), 0);
	}
	
	@Test
	public void getMineCount()
	{
		assertEquals(10, tank.getMineCount(), 0);
	}
	
	@Test
	public void getPillBoxCount()
	{
		assertEquals(0, tank.getPillboxCount(), 0);
	}
	
	@Test 
	public void isAlive()
	{
		assertTrue(tank.isAlive());
		tank.takeHit(200);
		assertFalse(tank.isAlive());
	}
	
	@Test
	public void takeHit()
	{
		tank.takeHit(20);
		assertEquals(80, tank.getHitPoints(), 0);
	}
	
	@Test
	public void heal()
	{
		tank.takeHit(20);
		tank.heal(5);
		assertEquals(85, tank.getHitPoints(), 0);
	}
	
	@Test
	public void gatherTree()
	{
		tank.gatherTree();
		assertEquals(1, tank.getTreeCount(), 0);
	}
	
	@Test
	public void useTrees()
	{
		tank.gatherTree();
		tank.useTrees(1);
		assertEquals(0, tank.getTreeCount(), 0);
	}
	
	@Test
	public void gatherMine()
	{
		tank.gatherMine(1);
		assertEquals(tank.TANK_MAX_MINE_COUNT, tank.getMineCount(), 0);
	}
	@Test
	public void gatherPillBox()
	{
		tank.gatherPillbox();
		assertEquals(1, tank.getPillboxCount(), 0);
	}
	
	@Test
	public void gatherAmmo()
	{
		tank.gatherAmmo(10);
		assertEquals(100, tank.getAmmoCount(), 0);
	}
	
	@Test
	public void dropMine()
	{
		MockMineCreator m = new MockMineCreator();
		Mine mine = tank.dropMine(m, 16, 16);
		assertNotNull(mine);
		mine = tank.dropMine(m, 16, 16);
		assertNull(mine);
	}
	
	@Test
	public void dropPillbox()
	{
		tank.gatherPillbox();
		Pillbox pillbox = tank.dropPillbox(new MockPillboxCreator(), 16, 16);
		assertNotNull(pillbox);
	}

	/**
	 * Tests evictEngineer()
	 */
	@Test
	public void evictEngineer()
	{
		if (tank.isEngineerInside())
		{
			Engineer engineer = tank.evictEngineer(new MockEngineerCreator(), 16, 16);
			assertNotNull(engineer);
		}
	}
	
	@Test
	public void setOwner()
	{
		tank.setOwnerUID(tank.getId());
		assertEquals(tank.getId(), tank.getOwnerUID());
	}

	@Test
	public void getOwner()
	{
		tank.setOwnerUID(tank.getId());
		assertEquals(tank.getId(), tank.getOwnerUID());
	}
	
	@Test
	public void getMax()
	{
		assertEquals(100, tank.getMaxHitPoints(), 0);
		assertEquals(100, tank.getTankMaxAmmo(), 0);
		assertEquals(10, tank.getTankMaxMineCount(), 0);
	}
}
