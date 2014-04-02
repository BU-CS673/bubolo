package bubolo.world.entity.concrete;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class RubbleTest
{
	static Rubble rubble;

	/**
	 * Constructs a Rubble object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		rubble = new Rubble();
		EntityTestCase.setTestParams(rubble);
	}

	@Test
	public void Rubble()
	{
		assertTrue(true);
	}
}
