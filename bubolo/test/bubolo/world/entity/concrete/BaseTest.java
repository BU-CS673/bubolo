package bubolo.world.entity.concrete;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class BaseTest
{
	static Base base;

	/**
	 * Constructs a Base object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		base = new Base();
		EntityTestCase.setTestParams(base);
	}

	@Test
	public void isLocalPlayer()
	{
		base.setLocalPlayer(true);
		assertEquals("Base local player set correctly.", true, base.isLocalPlayer());
	}

	@Test
	public void isOwned()
	{
		base.setOwned(true);
		assertEquals("Base ownership state set correctly.", true, base.isOwned());
	}

	@Test
	public void isCharging()
	{
		base.setCharging(true);
		assertEquals("Base charging state set correctly.", true, base.isCharging());
	}
	
	@Test
	public void  getHitPoints()
	{
		assertEquals(100, base.getHitPoints(), 0);
	}
	
	@Test
	public void getMaxHitPoints()
	{
		assertEquals(100, base.getMaxHitPoints(), 0);
	}
	
	@Test
	public void healDamageTest()
	{
		base.takeHit(1);
		assertEquals(99, base.getHitPoints(), 0);
		base.heal(1);
		assertEquals(100, base.getHitPoints(), 0);
	}
	
	@Test
	public void ammoTest()
	{
		assertEquals(base.getMaxAmmoCount(), base.getAmmoCount(), 0);
		base.gatherAmmo();
		assertEquals(base.getMaxAmmoCount(), base.getAmmoCount(), 0);
		base.giveAmmo();
		assertEquals(base.getMaxAmmoCount() - 5, base.getAmmoCount(), 0);
		base.gatherAmmo();
		assertEquals(base.getMaxAmmoCount(), base.getAmmoCount(), 0);		
	}
	
	@Test
	public void mineTest()
	{
		assertEquals(base.getMaxMineCount(), base.getMineCount(), 0);
		base.gatherMines();
		assertEquals(base.getMaxMineCount(), base.getMineCount(), 0);
		base.giveMine();
		assertEquals(base.getMaxMineCount() - 1, base.getMineCount(), 0);
		base.gatherMines();
		assertEquals(base.getMaxMineCount(), base.getMineCount(), 0);
	}
	@Test
	public void setOwner()
	{
		base.setOwnerUID(base.getId());
		assertEquals(base.getId(), base.getOwnerUID());
	}
	@Test
	public void getOwner()
	{
		base.setOwnerUID(base.getId());
		assertEquals(base.getId(), base.getOwnerUID());
	}
	
	@Test
	public void giveHitPoints()
	{
		assertEquals(5, base.giveHitPoints(), 0);
		base.heal(5);
	}
}
