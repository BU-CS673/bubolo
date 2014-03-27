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
		crater.setState(7);
		assertEquals("Crater's state does not match what it was set to!", 7, crater.getState());
	}
	
	
	// Will fail until updateState is implemented.
	@Test
	public void updateState()
	{
		crater.updateState();
	}
}
