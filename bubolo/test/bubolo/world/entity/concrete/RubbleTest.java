package bubolo.world.entity.concrete;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class RubbleTest
{
	static DeepWater rubble;

	/**
	 * Constructs a Rubble object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		rubble = new DeepWater();
		EntityTestCase.setTestParams(rubble);
	}

	@Test
	public void Rubble()
	{
		assert (true);
	}
}
