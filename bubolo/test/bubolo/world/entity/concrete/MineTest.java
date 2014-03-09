package bubolo.world.entity.concrete;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class MineTest
{
	static Mine mine;

	/**
	 * Constructs a Mine object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		mine = new Mine();
		EntityTestCase.setTestParams(mine);
	}

	@Test
	public void Mine()
	{
		assert (true);
	}

}
