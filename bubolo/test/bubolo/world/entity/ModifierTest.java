package bubolo.world.entity;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;

import bubolo.graphics.LibGdxAppTester;
import bubolo.graphics.MockSprite;
import bubolo.world.entity.Modifier;
import bubolo.world.entity.concrete.Road;
import bubolo.world.entity.concrete.Tank;

public class ModifierTest
{
	static Modifier mod;

	/**
	 * Sets up a Road object with the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		mod = new Road(new MockSprite<Road>());

		EntityTestCase.setTestParams(mod);
	}

	@Test
	public void setHP()
	{
		assertEquals("Modifier HP set correctly.", 10, mod.setHP(10).getHP());
	}

	@Test
	public void modifyHP()
	{
		assertEquals("Modifier HP modified correctly.", 10, mod.setHP(5).modifyHP(5).getHP());
	}

	@Test
	public void getMaxHP()
	{
		int max = mod.getMaxHP();
		// Should test whether the .getMaxHP() method returns the same value as the
		// Modifier's actual maximum HP.
		// This test is useless without knowing what value to look for.
		fail();
	}

	@Test
	public void isAlive()
	{
		boolean living = mod.isAlive();
		// Should return true if the Modifier is alive, and false otherwise.
		// Useless until we have some conditions under which Modifiers should be alive or
		// dead.
		fail();
	}

	@Test
	public void destroy()
	{
		Modifier mod2 = new Road(new MockSprite<Road>());
		mod2.destroy();
		// Should check to make sure the Modifier was removed properly.
		// Useless until we have some conditions to test whether a Modifier has been
		// destroyed.
		fail();
	}

}
