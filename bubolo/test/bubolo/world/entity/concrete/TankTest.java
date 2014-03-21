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
import bubolo.world.entity.EntityTestCase;

public class TankTest
{
	private Tank tank;

	/**
	 * Constructs a Tank object and sets the default parameters.
	 */
	@Before
	public void setup()
	{
		tank = new Tank();
		EntityTestCase.setTestParams(tank);
	}
	
	@Test
	public void getSpeed()
	{
		assertEquals(0, (int)tank.getSpeed());
	}
	
	@Test
	public void fireCannon()
	{	
		LibGdxAppTester.createApp();
		tank.fireCannon(new MockBulletCreator(), 50, 40, 10, 10);
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
		tank.fireCannon(new MockBulletCreator(), 50, 40, 10, 10);
		assertFalse(tank.isCannonReady());
	}
	
	@Test
	public void rotateLeft()
	{
		float rotation = tank.getRotation();
		tank.rotateLeft();
		assertEquals(rotation - 0.05f, tank.getRotation(), 0.0001f);
	}
	
	@Test
	public void rotateRight()
	{
		float rotation = tank.getRotation();
		tank.rotateRight();
		assertEquals(rotation + 0.05f, tank.getRotation(), 0.0001f);
	}
	
	@Test
	public void setLocal()
	{
		assertTrue(tank.isLocal());
		
		tank.setLocal(false);
		assertFalse(tank.isLocal());
	}
	
	@Test
	public void setLocalBreakInvariant()
	{
		tank.setLocal(false);
		try
		{
			tank.setLocal(false);
			fail("tank.setLocal called twice. This is not allowed.");
		}
		catch (Exception e) {}
	}
}
