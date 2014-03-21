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

	@Test
	public void capturePillboxTest()
	{
		// pillbox is dead, owned
		pillbox.setHP(0);
		pillbox.setOwned(true);
		pillbox.capturePillbox();
		assertEquals("re-setOwned", true, pillbox.isOwned());
		assertEquals("set owership to localplayer.", true, pillbox.isLocalPlayer());
		assertEquals("re-build", pillbox.getMaxHP(), pillbox.getHP());
		// pillbox is dead, not owned
		pillbox.setHP(0);
		pillbox.setOwned(false);
		pillbox.capturePillbox();
		assertEquals("re-setOwned", true, pillbox.isOwned());
		assertEquals("set owership to localplayer.", true, pillbox.isLocalPlayer());
		assertEquals("re-build", pillbox.getMaxHP(), pillbox.getHP());
		// pillbox is alive, owned by local
		pillbox.setHP(pillbox.getMaxHP());
		pillbox.setOwned(true);
		pillbox.setLocalPlayer(true);
		pillbox.capturePillbox();
		assertEquals("re-build", pillbox.getMaxHP(), pillbox.getHP());
		// pillbox is alive, not owned by local
		pillbox.setHP(pillbox.getMaxHP() / 2);
		pillbox.setOwned(true);
		pillbox.setLocalPlayer(false);
		pillbox.capturePillbox();
		assertEquals("re-build", pillbox.getMaxHP() / 2, pillbox.getHP());
	}
}
