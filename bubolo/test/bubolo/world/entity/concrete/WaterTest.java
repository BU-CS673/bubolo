package bubolo.world.entity.concrete;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import bubolo.world.World;
import bubolo.world.entity.EntityTestCase;

public class WaterTest
{
	private Water water;

	/**
	 * Constructs a Water object and sets the default parameters.
	 */
	@Before
	public void setup()
	{
		water = new Water();
		EntityTestCase.setTestParams(water);
	}

	@Test 
	public void setState(){
		water.setTilingState(7);
		assertEquals("Water's state does not match what it was set to!", 7, water.getTilingState());
	}
	
	@Test
	public void update()
	{
		water.update(mock(World.class));
	}
	
	@Test
	public void updateState()
	{
		water.updateTilingState(null);
	}
	
	@Test
	public void maxSpeedModifierTest()
	{
		water = new Water();
		float maxSpeedModifier = 0;
		maxSpeedModifier = water.getMaxSpeedModifier();
		assertEquals(water.getMaxSpeedModifier(), maxSpeedModifier, 0);
	}
}
