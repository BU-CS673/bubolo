package bubolo.world.entity.concrete;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import bubolo.world.World;
import bubolo.world.entity.EntityTestCase;

public class WallTest
{
	private Wall wall;

	/**
	 * Constructs a Wall object and sets the default parameters.
	 */
	@Before
	public void setup()
	{
		wall = new Wall();
		EntityTestCase.setTestParams(wall);
	}

	@Test 
	public void setState(){
		wall.setTilingState(7);
		assertEquals("Wall's state does not match what it was set to!", 7, wall.getTilingState());
	}

	@Test
	public void update()
	{
		wall.update(mock(World.class));
	}
	
	@Test
	public void updateState()
	{
		wall.updateTilingState(null);
	}
	
	@Test
	public void  getHitPoints()
	{
		assertEquals(100, wall.getHitPoints(), 0);
	}
	
	@Test
	public void getMaxHitPoints()
	{
		assertEquals(100, wall.getMaxHitPoints(), 0);
	}
	
	@Test
	public void healDamageTest()
	{
		wall.takeHit(1);
		assertEquals(99, wall.getHitPoints(), 0);
		wall.heal(1);
		assertEquals(100, wall.getHitPoints(), 0);
	}
}
