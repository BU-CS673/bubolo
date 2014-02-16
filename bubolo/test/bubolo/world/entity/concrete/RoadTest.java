package bubolo.world.entity.concrete;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.graphics.MockSprite;
import bubolo.world.entity.base.EntityTestCase;

public class RoadTest
{
	static Road road;

	/**
	 * Constructs a Road object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		road = new Road(new MockSprite<Road>());
		EntityTestCase.setTestParams(road);
	}

	@Test
	public void Road()
	{
		assert (true);
	}
}
