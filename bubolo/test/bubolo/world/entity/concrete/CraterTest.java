package bubolo.world.entity.concrete;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import bubolo.world.World;
import bubolo.world.entity.EntityTestCase;

public class CraterTest
{
	private Crater crater;

	/**
	 * Constructs a Crater object and sets the default parameters.
	 */
	@Before
	public void setup()
	{
		crater = new Crater();
		EntityTestCase.setTestParams(crater);
	}
	
	@Test 
	public void setState(){
		crater.setTilingState(7);
		assertEquals("Crater's state does not match what it was set to!", 7, crater.getTilingState());
	}
	
	@Test
	public void update()
	{
		crater.update(mock(World.class));
	}	

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
