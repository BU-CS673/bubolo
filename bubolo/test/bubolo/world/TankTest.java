package bubolo.world;

import static org.junit.Assert.*;

import org.junit.Test;

import bubolo.world.Entity;
import bubolo.world.Tank;


public class TankTest
{

	@Test
	public void constructTank()
	{
		// Fails if an exception is thrown in the constructor.
		Entity tank = new Tank();
	}

}
