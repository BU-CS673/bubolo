package bubolo.world.entity.concrete;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class DeepWaterTest
{
	static DeepWater deepWater;

	/**
	 * Constructs a DeepWater object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		deepWater = new DeepWater();
		EntityTestCase.setTestParams(deepWater);
	}

	@Test
	public void DeepWater()
	{
		assert (true);
	}
}
