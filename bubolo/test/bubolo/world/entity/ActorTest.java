package bubolo.world.entity;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;

import bubolo.graphics.LibGdxAppTester;
import bubolo.graphics.MockSprite;
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
		act = new Tank(new MockSprite<Tank>());
		EntityTestCase.setTestParams(act);
	}
	
	@Test
	public void constructId(){
		Actor act2 = new Tank(new MockSprite<Tank>());
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
		// Should test whether the .getMaxHP() method returns the same value as the
		// Actor's actual maximum HP.
		// This test is useless without knowing what value to look for.
		fail();
	}

	@Test
	public void isAlive()
	{
		boolean living = act.isAlive();
		// Should return true if the Actor is alive, and false otherwise.
		// Useless until we have some conditions under which Actors should be alive or
		// dead.
		fail();
	}

	@Test
	public void destroy()
	{
		Actor act2 = new Tank(new MockSprite<Tank>());
		act2.destroy();
		// Should check to make sure the Actor was removed properly.
		// Useless until we have some conditions to test whether a Actor has been
		// destroyed.
		fail();
	}

}
