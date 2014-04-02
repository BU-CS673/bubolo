package bubolo.world.entity.concrete;

import static org.junit.Assert.*;

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
	public void setState(){
		wall.setTilingState(7);
		assertEquals("Wall's state does not match what it was set to!", 7, wall.getTilingState());
	}

	// Will fail until updateState is implemented.
	@Test
	public void updateState()
	{
		wall.updateTilingState(null);
	}
}
