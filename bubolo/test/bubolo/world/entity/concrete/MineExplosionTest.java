package bubolo.world.entity.concrete;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.World;
import bubolo.world.entity.EntityTestCase;

import static org.mockito.Mockito.mock;

public class MineExplosionTest
{
	static MineExplosion exp;

	/**
	 * Constructs a Bullet object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		exp = new MineExplosion();
		EntityTestCase.setTestParams(exp);
	}

	@Test
	public void MineExplosion()
	{
		assertTrue(true);
	}
	
	@Test
	public void update()
	{
		exp.update(mock(World.class));
	}
}
