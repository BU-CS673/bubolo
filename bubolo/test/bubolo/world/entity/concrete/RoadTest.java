package bubolo.world.entity.concrete;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import bubolo.world.World;
import bubolo.world.entity.EntityTestCase;

public class RoadTest
{
	private Road road;

	/**
	 * Constructs a Road object and sets the default parameters.
	 */
	@Before
	public void setup()
	{
		road = new Road();
		EntityTestCase.setTestParams(road);
	}
	
	@Test 
	public void setState(){
		road.setTilingState(7);
		assertEquals("Road's state does not match what it was set to!", 7, road.getTilingState());
	}
	
	@Test
	public void update()
	{
		road.update(mock(World.class));
	}
	
	@Test
	public void updateState()
	{
		road.updateTilingState(null);
	}
	
	@Test
	public void maxSpeedModifierTest()
	{
		road = new Road();
		float maxSpeedModifier = 0;
		maxSpeedModifier = road.getMaxSpeedModifier();
		assertEquals(road.getMaxSpeedModifier(), maxSpeedModifier, 0);
	}
}
