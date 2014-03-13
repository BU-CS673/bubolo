package bubolo.world.entity.concrete;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class WallTest
{
	static Wall wall;

	/**
	 * Constructs a Wall object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		wall = new Wall();
		EntityTestCase.setTestParams(wall);
	}

	@Test
	public void Wall()
	{
		assert (true);
	}

}
