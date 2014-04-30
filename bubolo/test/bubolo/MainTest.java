/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

/**
 * @author BU CS673 - Clone Productions
 */
public class MainTest
{

	/**
	 * Test method for {@link bubolo.Main#setApplication(com.badlogic.gdx.backends.lwjgl.LwjglApplication)}.
	 */
	@Test
	public void getSetApplication()
	{
		class TestApp implements GameApplication
		{
			@Override
			public void create()
			{
			}

			@Override
			public void dispose()
			{
			}

			@Override
			public void pause()
			{
			}

			@Override
			public void render()
			{
			}

			@Override
			public void resize(int arg0, int arg1)
			{
			}

			@Override
			public void resume()
			{
			}

			@Override
			public boolean isReady()
			{
				return false;
			}

			@Override
			public boolean isGameStarted()
			{
				return false;
			}

			@Override
			public void setState(State state)
			{
			}

			@Override
			public State getState()
			{
				return null;
			}
		}
		
		assertNull(Main.getApplication());
		
		LwjglApplication app = new LwjglApplication(new TestApp());
		
		Main.setApplication(app);
		assertSame(app, Main.getApplication());
	}
}
