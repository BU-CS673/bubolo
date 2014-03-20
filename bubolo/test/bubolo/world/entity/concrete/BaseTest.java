package bubolo.world.entity.concrete;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class BaseTest
{
	static Base base;

	/**
	 * Constructs a Base object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		base = new Base();
		EntityTestCase.setTestParams(base);
	}

	@Test
	public void Base()
	{
		assert (true);
	}

	@Test
	public void isLocalPlayer()
	{
		base.setLocalPlayer(true);
		assertEquals("Base local player set correctly.", true, base.isLocalPlayer());
	}

	@Test
	public void isOwned()
	{
		base.setOwned(true);
		assertEquals("Base ownership state set correctly.", true, base.isOwned());
	}

	@Test
	public void isCharging()
	{
		base.setCharging(true);
		assertEquals("Base charging state set correctly.", true, base.isCharging());
	}
}
