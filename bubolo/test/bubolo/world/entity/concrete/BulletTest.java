package bubolo.world.entity.concrete;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.World;
import bubolo.world.entity.EntityTestCase;

import static org.mockito.Mockito.mock;

public class BulletTest
{
	static Bullet bullet;

	/**
	 * Constructs a Bullet object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		bullet = new Bullet();
		EntityTestCase.setTestParams(bullet);
	}
	
	@Test
	public void update()
	{
		bullet.update(mock(World.class));
	}
}
