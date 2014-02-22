package bubolo.world.entity.concrete;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class GrassTest
{
	static Grass grass;

	/**
	 * Constructs a Grass object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		grass = new Grass();
		EntityTestCase.setTestParams(grass);
	}

	@Test
	public void Grass()
	{
		assert (true);
	}
}
