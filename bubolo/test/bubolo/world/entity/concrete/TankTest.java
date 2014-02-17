package bubolo.world.entity.concrete;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class TankTest
{
	static Tank tank;

	/**
	 * Constructs a Tank object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		tank = new Tank();
		EntityTestCase.setTestParams(tank);
	}

	@Test
	public void Tank()
	{
		assert (true);
	}
}
