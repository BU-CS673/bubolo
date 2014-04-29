package bubolo.world.entity.concrete;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class MineTest
{
	static Mine mine;

	/**
	 * Constructs a Mine object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		mine = new Mine();
		EntityTestCase.setTestParams(mine);
	}

	@Test
	public void isLocalPlayer()
	{
		mine.setLocalPlayer(true);
		assertEquals("Mine local player ownership set correctly.", true, mine.isLocalPlayer());
	}

	@Test
	public void isOwned()
	{
		mine.setOwned(true);
		assertEquals("Mine ownership state set correctly.", true, mine.isOwned());
	}
	
	@Test
	public void isExploding()
	{
		mine.setExploding(true);
		assertEquals("Mine exploding state set correctly.", true, mine.isExploding());
	}
	@Test
	public void setOwner()
	{
		mine.setOwnerUID(mine.getId());
		assertEquals(mine.getId(), mine.getOwnerUID());
	}
	@Test
	public void getOwner()
	{
		mine.setOwnerUID(mine.getId());
		assertEquals(mine.getId(), mine.getOwnerUID());
	}
	
	@Test
	public void onDispose()
	{
		mine.onDispose();
	}
}
