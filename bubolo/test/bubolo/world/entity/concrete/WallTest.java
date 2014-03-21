package bubolo.world.entity.concrete;

import static org.junit.Assert.assertEquals;

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
	
	@Test 
	public void setState(){
		wall.setState(7);
		assertEquals("Wall's state does not match what it was set to!", 7, wall.getState());
	}

	// Will fail until updateState is implemented.
	@Test
	public void updateState()
	{
		wall.updateState();
	}
}
