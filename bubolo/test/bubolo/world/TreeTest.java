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
import bubolo.world.Entity;
import bubolo.world.Tank;

public class TreeTest
{

	static boolean isComplete = false;
	static Tree tree;

	/**
	 * An OpenGL context must be created so that the textures for the Tank object can load properly.
	 * Without this, all tests will crash on Tank construction.
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
				tree = new Tree();

				isComplete = true;
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}

	}	
	
}
