package bubolo.world.entity.concrete;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class GenericExplosionTest
{
	static GenericExplosion exp;

	/**
	 * Constructs a Bullet object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		exp = new GenericExplosion();
		EntityTestCase.setTestParams(exp);
	}

	@Test
	public void GenericExplosion()
	{
		assert (true);
	}
}
