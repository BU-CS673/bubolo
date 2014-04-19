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
	public void isLocalPlayer()
	{
		act.setLocalPlayer(true);
		assertEquals("Actor local player ownership set correctly.", true, act.isLocalPlayer());
	}

	@Test
	public void isOwned()
	{
		act.setOwned(true);
		assertEquals("Actor ownership state set correctly.", true, act.isOwned());
	}

	@Test
	public void constructId()
	{
		Actor act2 = new Tank(EntityTestCase.TARGET_UUID);
		assertEquals("Actor UUID set correctly.", EntityTestCase.TARGET_UUID, act2.getId());
	}
}
