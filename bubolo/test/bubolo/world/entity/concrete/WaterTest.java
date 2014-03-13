package bubolo.world.entity.concrete;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class WaterTest
{
	static Water water;

	/**
	 * Constructs a Water object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		water = new Water();
		EntityTestCase.setTestParams(water);
	}

	@Test
	public void Water()
	{
		assert (true);
	}
}
