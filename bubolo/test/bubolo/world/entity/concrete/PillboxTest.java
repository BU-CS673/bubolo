package bubolo.world.entity.concrete;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class PillboxTest
{
	static Pillbox pillbox;

	/**
	 * Constructs a Pillbox object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		pillbox = new Pillbox();
		EntityTestCase.setTestParams(pillbox);
	}

	@Test
	public void Pillbox()
	{
		assert (true);
	}

	@Test
	public void isLocalPlayer()
	{
		pillbox.setLocalPlayer(true);
		assertEquals("Pillbox local player ownership set correctly.", true, pillbox.isLocalPlayer());
	}

	@Test
	public void isOwned()
	{
		pillbox.setOwned(true);
		assertEquals("Pillbox ownership state set correctly.", true, pillbox.isOwned());
	}

}
