package bubolo.world.entity;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.StationaryElement;
import bubolo.world.entity.concrete.Tree;

public class StationaryElementTest
{
	static StationaryElement sta;

	/**
	 * Sets up a Tree object with the default parameters.
	 */
	@BeforeClass
	public static void setUpApp()
	{
		sta = new Tree();
		EntityTestCase.setTestParams(sta);

	}

	@Test
	public void setHP()
	{
		assertEquals("StationaryElement HP set correctly.", 10, sta.setHP(10).getHP());
	}

	@Test
	public void modifyHP()
	{
		assertEquals("StationaryElement HP modified correctly.", 10, sta.setHP(5).modifyHP(5).getHP());
	}

	@Test
	public void getMaxHP()
	{
		int max = sta.getMaxHP();	
	}

	@Test
	public void isAlive()
	{
		assertTrue(sta.isAlive());
	}

	@Test
	public void destroy()
	{
		StationaryElement sta2 = new Tree();
		sta2.destroy();	
	}

}
