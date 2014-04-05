package bubolo.world.entity.concrete;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class DeepWaterTest
{
	static DeepWater deepWater;

	/**
	 * Constructs a DeepWater object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		deepWater = new DeepWater();
		EntityTestCase.setTestParams(deepWater);
	}

	@Test 
	public void setState(){
		deepWater.setTilingState(7);
		assertEquals("DeepWater's state does not match what it was set to!", 7, deepWater.getTilingState());
	}
	
	// Will fail until updateState is implemented.
	@Test
	public void updateState()
	{
		deepWater.updateTilingState(null);
	}
}
