package bubolo.world.entity.concrete;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class RoadTest
{
	static Road road;

	/**
	 * Constructs a Road object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		road = new Road();
		EntityTestCase.setTestParams(road);
	}

	@Test
	public void Road()
	{
		assert (true);
	}
	
	@Test 
	public void setState(){
		road.setState(7);
		assertEquals("Road's state does not match what it was set to!", 7, road.getState());
	}
	
	// Will fail until updateState is implemented.
	@Test
	public void updateState()
	{
		road.updateState();
	}
}
