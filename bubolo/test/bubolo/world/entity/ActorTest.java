package bubolo.world.entity;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.Actor;
import bubolo.world.entity.concrete.Tank;

public class ActorTest
{
	static Actor act;

	/**
	 * Creates a Tank object and sets the starting parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		act = new Tank();
		EntityTestCase.setTestParams(act);
	}
	
	@Test
	public void constructId(){
		Actor act2 = new Tank();
	}

	@Test
	public void setHP()
	{
		assertEquals("Actor HP set correctly.", 10, act.setHP(10).getHP());
	}

	@Test
	public void modifyHP()
	{
		assertEquals("Actor HP modified correctly.", 10, act.setHP(5).modifyHP(5).getHP());
	}

	@Test
	public void getMaxHP()
	{
		int max = act.getMaxHP();
	}

	@Test
	public void isAlive()
	{
		assertTrue(act.isAlive());
	}

	@Test
	public void destroy()
	{
		Actor act2 = new Tank();
		act2.destroy();
	}

}
