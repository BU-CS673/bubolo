package bubolo.world.entity.concrete;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class CraterTest
{
	static Crater crater;

	/**
	 * Constructs a Crater object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		crater = new Crater();
		EntityTestCase.setTestParams(crater);
	}
	
	@Test 
	public void setState(){
		crater.setTilingState(7);
		assertEquals("Crater's state does not match what it was set to!", 7, crater.getTilingState());
	}
	
	
	// Will fail until updateState is implemented.
	@Test
	public void updateState()
	{
		crater.updateTilingState(null);
	}
	
	@Test
	public void maxSpeedModifierTest()
	{
		crater = new Crater();
		float maxSpeedModifier = 0;
		maxSpeedModifier = crater.getMaxSpeedModifier();
		assertEquals(crater.getMaxSpeedModifier(), maxSpeedModifier, 0);
	}
}
