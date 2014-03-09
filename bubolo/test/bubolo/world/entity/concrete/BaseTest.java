package bubolo.world.entity.concrete;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class BaseTest
{
	static Base base;

	/**
	 * Constructs a Base object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		base = new Base();
		EntityTestCase.setTestParams(base);
	}

	@Test
	public void Base()
	{
		assert (true);
	}

}
