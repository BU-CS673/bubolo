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
		bullet = new Bullet(true);
		EntityTestCase.setTestParams(bullet);
	}
	
	@Test
	public void update()
	{
		bullet.update(mock(World.class));
	}
	@Test
	public void getParent()
	{
		Tank tank = mock(Tank.class);
		bullet.setParent(tank);
		assertEquals(tank, bullet.getParent());
	}
	@Test
	public void setParent()
	{
		Tank tank = mock(Tank.class);
		bullet.setParent(tank);
		assertEquals(tank, bullet.getParent());
	}
}
