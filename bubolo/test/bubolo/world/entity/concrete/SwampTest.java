package bubolo.world.entity.concrete;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class SwampTest
{
	static Swamp swamp;

	/**
	 * Constructs a Swamp object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		swamp = new Swamp();
		EntityTestCase.setTestParams(swamp);
	}

	@Test
	public void Swamp()
	{
		assertTrue(true);
	}
	@Test
	public void maxSpeedModifierTest()
	{
		swamp = new Swamp();
		float maxSpeedModifier = 0;
		maxSpeedModifier = swamp.getMaxSpeedModifier();
		assertEquals(swamp.getMaxSpeedModifier(), maxSpeedModifier, 0);
	}
}
