package bubolo.world.entity.base;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;

import bubolo.graphics.LibGdxAppTester;
import bubolo.graphics.MockSprite;
import bubolo.world.entity.StationaryElement;
import bubolo.world.entity.concrete.Tank;
import bubolo.world.entity.concrete.Tree;

public class StationaryElementTest
{
	static StationaryElement sta;

	/**
	 * Sets up a Tree object with the default parameters.
	 */
	@BeforeClass
	public static void setUpApp()
	{
		sta = new Tree(new MockSprite<Tree>());
		EntityTestCase.setTestParams(sta);

	}

	@Test
	public void setHP()
	{
		assertEquals("StationaryElement HP set correctly.", 10, sta.setHP(10).getHP());
	}

	@Test
	public void modifyHP()
	{
		assertEquals("StationaryElement HP modified correctly.", 10, sta.setHP(5).modifyHP(5)
				.getHP());
	}

	@Test
	public void getMaxHP()
	{
		int max = sta.getMaxHP();
		// Should test whether the .getMaxHP() method returns the same value as the
		// StationaryElement's actual maximum HP.
		// This test is useless without knowing what value to look for.
		fail();
	}

	@Test
	public void isAlive()
	{
		boolean living = sta.isAlive();
		// Should return true if the StationaryElement is alive, and false otherwise.
		// Useless until we have some conditions under which StationaryElements should be
		// alive or dead.
		fail();
	}

	@Test
	public void destroy()
	{
		StationaryElement sta2 = new Tree(new MockSprite<Tree>());
		sta2.destroy();
		// Should check to make sure the StationaryElement was removed properly.
		// Useless until we have some conditions to test whether a StationaryElement has
		// been destroyed.
		fail();
	}

}
