package bubolo.world;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;

import bubolo.graphics.LibGdxAppTester;
import bubolo.world.Entity;

public class ModifierTest
{
	static final UUID TARGET_UUID = UUID.fromString("5231b533-ba17-4787-98a3-f2df37de2aD7"); // random
																								// UUID
																								// string
	static final float TARGET_X = 26.7f;
	static final float TARGET_Y = 72.5f;
	static final float TARGET_ROT = (float) Math.PI / 2;
	static final int TARGET_WIDTH = 50;
	static final int TARGET_HEIGHT = 100;
	boolean LOAD_TEXTURES = false;
	static Modifier mod;
	static boolean isComplete = false;

	/**
	 * An OpenGL context must be created so that the textures for the Tree object can load
	 * properly. Without this, all tests will crash on Tank construction.
	 */
	@BeforeClass
	public static void setUpApp()
	{
		LibGdxAppTester.createApp();
		isComplete = false;

		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				mod = new Road(TARGET_X, TARGET_Y, TARGET_WIDTH, TARGET_HEIGHT, TARGET_ROT,
						TARGET_UUID);

				isComplete = true;
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}

	}
	
	@Test
	public void setHP(){
		assertEquals("Modifier HP set correctly.", 10, mod.setHP(10).getHP());
	}
	
	@Test
	public void modifyHP(){
		assertEquals("Modifier HP modified correctly.", 10, mod.setHP(5).modifyHP(5).getHP());
	}
	
	@Test
	public void getMaxHP(){
		int max = mod.getMaxHP();
		//Should test whether the .getMaxHP() method returns the same value as the Modifier's actual maximum  HP.
		//This test is useless without knowing what value to look for.
		fail();
	}
	
	@Test
	public void isAlive(){
		boolean living = mod.isAlive();
		//Should return true if the Modifier is alive, and false otherwise.
		//Useless until we have some conditions under which Modifiers should be alive or dead.
		fail();
	}
	
	@Test
	public void destroy(){
		Modifier mod2 = new Road();
		mod2.destroy();
		//Should check to make sure the Modifier was removed properly.
		//Useless until we have some conditions to test whether a Modifier has been destroyed.
		fail();
	}

}
