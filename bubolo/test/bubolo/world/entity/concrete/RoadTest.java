package bubolo.world.entity.concrete;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class RoadTest
{
	static Road road;

	/**
	 * Constructs a Road object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		road = new Road();
		EntityTestCase.setTestParams(road);
	}

	@Test
	public void Road()
	{
		assert (true);
	}
}
