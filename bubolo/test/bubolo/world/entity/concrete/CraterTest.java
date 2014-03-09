package bubolo.world.entity.concrete;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class CraterTest
{
	static Crater crater;

	/**
	 * Constructs a Crater object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		crater = new Crater();
		EntityTestCase.setTestParams(crater);
	}

	@Test
	public void Crater()
	{
		assert (true);
	}
}
