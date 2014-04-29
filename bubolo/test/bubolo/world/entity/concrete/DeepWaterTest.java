package bubolo.world.entity.concrete;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import bubolo.world.World;
import bubolo.world.entity.EntityTestCase;

public class DeepWaterTest
{
	private DeepWater deepWater;

	/**
	 * Constructs a DeepWater object and sets the default parameters.
	 */
	@Before
	public void setup()
	{
		deepWater = new DeepWater();
		EntityTestCase.setTestParams(deepWater);
	}

	@Test 
	public void setState(){
		deepWater.setTilingState(7);
		assertEquals("DeepWater's state does not match what it was set to!", 7, deepWater.getTilingState());
	}

	@Test
	public void update()
	{
		deepWater.update(mock(World.class));
	}

	@Test
	public void updateState()
	{
		deepWater.updateTilingState(null);
	}
	
	@Test
	public void maxSpeedModifierTest()
	{
		deepWater = new DeepWater();
		float maxSpeedModifier = 0;
		maxSpeedModifier = deepWater.getMaxSpeedModifier();
		assertEquals(deepWater.getMaxSpeedModifier(), maxSpeedModifier, 0);
	}
}
