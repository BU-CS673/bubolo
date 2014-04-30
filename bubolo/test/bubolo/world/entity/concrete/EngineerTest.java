package bubolo.world.entity.concrete;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.World;
import bubolo.world.entity.EntityTestCase;
import static org.mockito.Mockito.mock;

public class EngineerTest
{
	static Engineer engi;

	/**
	 * Constructs a Man object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		engi = new Engineer();
		EntityTestCase.setTestParams(engi);
	}
	
	@Test
	public void isBuilding(){
		engi.setBuilding(true);
		assertEquals("Engineer building state set correctly.", true, engi.isBuilding());
	}
	
	@Test
	public void isRunning(){
		engi.setRunning(true);
		assertEquals("Engineer running state set correctly.", true, engi.isRunning());
	}
	
	@Test
	public void update() {
		engi.update(mock(World.class));
	}
	
	@Test
	public void  getHitPoints()
	{
		assertEquals(1, engi.getHitPoints(), 0);
	}
	
	@Test
	public void getMaxHitPoints()
	{
		assertEquals(1, engi.getMaxHitPoints(), 0);
	}
	
	@Test
	public void healDamageTest()
	{
		engi.takeHit(1);
		assertEquals(0, engi.getHitPoints(), 0);
		engi.heal(1);
		assertEquals(1, engi.getHitPoints(), 0);
	}
	@Test
	public void setOwner()
	{
		engi.setOwnerUID(engi.getId());
		assertEquals(engi.getId(), engi.getOwnerUID());
	}
	@Test
	public void getOwner()
	{
		engi.setOwnerUID(engi.getId());
		assertEquals(engi.getId(), engi.getOwnerUID());
	}
	
	/**
	 * Tests isAtWaypoint()
	 */
	@Test
	public void isAtWaypoint()
	{
		assertTrue(engi.isAtWaypoint());
		engi.setWaypoint(engi.getX() + 1, engi.getY() + 1);
		assertFalse(engi.isAtWaypoint());		
	}

	/**
	 * Tests setTank() and getTank()
	 */
	@Test
	public void setTank()
	{
		Tank tank = new Tank();
		engi.setTank(tank);
		assertTrue(engi.getTank() == tank);
	}
}
