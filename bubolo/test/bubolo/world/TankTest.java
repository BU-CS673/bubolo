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

	@Test
	public void getX()
	{
		// TODO: Fix this test once Entity.getX() is properly implemented.
		Entity tank = new Tank();
		assertTrue(0.f == tank.getX());
	}
	
	@Test
	public void getY()
	{
		// TODO: Fix this test once Entity.getY() is properly implemented.
		Entity tank = new Tank();
		assertTrue(0.f == tank.getY());	
	}
}
