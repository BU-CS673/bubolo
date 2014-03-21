package bubolo.world.entity.concrete;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class WaterTest
{
	static Water water;

	/**
	 * Constructs a Water object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		water = new Water();
		EntityTestCase.setTestParams(water);
	}

	@Test
	public void Water()
	{
		assert (true);
	}
	
	@Test 
	public void setState(){
		water.setState(7);
		assertEquals("Water's state does not match what it was set to!", 7, water.getState());
	}
	
	// Will fail until updateState is implemented.
	@Test
	public void updateState()
	{
		water.updateState();
	}
}
