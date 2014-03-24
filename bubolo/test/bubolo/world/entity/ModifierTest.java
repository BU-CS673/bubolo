package bubolo.world.entity;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.Modifier;
import bubolo.world.entity.concrete.Road;

public class ModifierTest
{
	static Modifier mod;

	/**
	 * Sets up a Road object with the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		mod = new Road();

		EntityTestCase.setTestParams(mod);
	}

	@Test
	public void setHP()
	{
		assertEquals("Modifier HP set correctly.", 10, mod.setHP(10).getHP());
	}

	@Test
	public void modifyHP()
	{
		assertEquals("Modifier HP modified correctly.", 10, mod.setHP(5).modifyHP(5).getHP());
	}

	@Test
	public void getMaxHP()
	{
		int max = mod.getMaxHP();
	}

	@Test
	public void isAlive()
	{
		assertTrue(mod.isAlive());
	}

	@Test
	public void destroy()
	{
		Modifier mod2 = new Road();
		mod2.destroy();
	}

}
