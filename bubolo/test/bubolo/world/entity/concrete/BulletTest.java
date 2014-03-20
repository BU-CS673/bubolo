package bubolo.world.entity.concrete;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.Actor;
import bubolo.world.entity.EntityTestCase;

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
	public void Bullet()
	{
		assert (true);
	}
	
}
