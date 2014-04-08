package bubolo.world.entity.concrete;

import static org.junit.Assert.*;

import org.junit.BeforeClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.peer.LightweightPeer;

import org.junit.Before;
import org.junit.Test;

import bubolo.graphics.LibGdxAppTester;
import bubolo.test.MockBulletCreator;
import bubolo.test.MockWorld;
import bubolo.world.GameWorld;
import bubolo.world.World;
import bubolo.world.entity.Entity;
import bubolo.world.entity.EntityTestCase;

public class TankTest
{
	private Tank tank;
	private World world;

	/**
	 * Constructs a Tank object and sets the default parameters.
	 */
	@Before
	public void setup()
	{
		world = new GameWorld(32, 32);
		tank = (Tank) world.addEntity(Tank.class).setParams(16, 16, 0);
		world.addEntity(Grass.class).setParams(16, 16, 0);
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
}
