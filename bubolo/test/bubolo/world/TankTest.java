package bubolo.world;

import org.junit.Test;

import bubolo.world.Entity;
import bubolo.world.Tank;


public class TankTest
{

	@Test
	public Entity constructTank()
	{
		// Fails if an exception is thrown in the constructor.
		Entity tank = new Tank();
		return tank;
	}

}
