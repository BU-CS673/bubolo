package bubolo.world;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import bubolo.graphics.Graphics;
import bubolo.graphics.LibGdxAppTester;
import bubolo.graphics.MockSprite;
import bubolo.world.Entity;
import bubolo.world.Tank;

public class TreeTest
{

	static final UUID TARGET_UUID = UUID.fromString("5231b533-ba17-4787-98a3-f2df37de2aD7"); // random
	// UUID
	// string
	static final float TARGET_X = 26.7f;
	static final float TARGET_Y = 72.5f;
	static final float TARGET_ROT = (float) Math.PI / 2;
	static final int TARGET_WIDTH = 50;
	static final int TARGET_HEIGHT = 100;

	static boolean isComplete = false;
	static Tree tree;

	/**
	 * An OpenGL context must be created so that the textures for the Tree object can load
	 * properly. Without this, all tests will crash on Tree construction.
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
				tree = new Tree(TARGET_X, TARGET_Y, TARGET_WIDTH, TARGET_HEIGHT, TARGET_ROT,
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
	public void constructTree_NO_UUID()
	{
		Tree tree2 = new Tree();
	}

	@Test
	public void constructTree_UUID_ONLY()
	{
		Tree tree2 = new Tree(TARGET_UUID);
		assertEquals("Tree constructor with UUID sets the field correctly,", tree.getId(), tree2.getId());
	}

	@Test
	public void constructGrass_PARAM_NO_UUID()
	{
		Tree tree2 = new Tree(TARGET_X, TARGET_Y, TARGET_WIDTH, TARGET_HEIGHT, TARGET_ROT);
		assertEquals("Tree param constructor without UUID sets fields correctly,", true, EntityTestCase.matches_NO_UUID(tree, tree2));
	}
	
	@Test
	public void checkSprite(){
		assertNotEquals("Tree is not using the default entity sprite.", tree.getSpriteId(), new DummyEntity().getSpriteId());
	}
	

}
