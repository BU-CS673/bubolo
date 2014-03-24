package bubolo.world.entity.concrete;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;

import bubolo.graphics.LibGdxAppTester;
import bubolo.test.MockBulletCreator;
import bubolo.world.GameWorld;
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
	@Test
	public void aimCannon()
	{
		float direction = 60;
		pillbox.aimCannon(direction);
		assertEquals("Pillbox aimed correctly", true, pillbox.getCannonRotation() == direction);
	}
	@Test
	public void fireCannon()
	{
		LibGdxAppTester.createApp();
		pillbox.fireCannon(new MockBulletCreator());
		
	}
	@Test
	public void getCannonRotation()
	{
		float direction = 60;
		pillbox.aimCannon(direction);
		assertEquals("Pillbox aimed correctly", true, pillbox.getCannonRotation() == direction);
		
	}
	@Test
	public void isCannonReady()
	{
		LibGdxAppTester.createApp();
		assertTrue(pillbox.isCannonReady());
		pillbox.fireCannon(new MockBulletCreator());
		assertFalse(pillbox.isCannonReady());
		
	}

}
