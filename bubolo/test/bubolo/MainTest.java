/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.utils.Clipboard;

/**
 * @author BU CS673 - Clone Productions
 */
public class MainTest
{

	/**
	 * Test method for
	 * {@link bubolo.Main#setApplication(com.badlogic.gdx.backends.lwjgl.LwjglApplication)}.
	 */
	@Test
	public void getSetApplication()
	{
		class TestApp implements Application
		{
			@Override
			public void addLifecycleListener(LifecycleListener arg0)
			{
			}

			@Override
			public void debug(String arg0, String arg1)
			{
			}

			@Override
			public void debug(String arg0, String arg1, Throwable arg2)
			{
			}

			@Override
			public void error(String arg0, String arg1)
			{
			}

			@Override
			public void error(String arg0, String arg1, Throwable arg2)
			{
			}

			@Override
			public void exit()
			{
			}

			@Override
			public ApplicationListener getApplicationListener()
			{
				return null;
			}

			@Override
			public Audio getAudio()
			{
				return null;
			}

			@Override
			public Clipboard getClipboard()
			{
				return null;
			}

			@Override
			public Files getFiles()
			{
				return null;
			}

			@Override
			public Graphics getGraphics()
			{
				return null;
			}

			@Override
			public Input getInput()
			{
				return null;
			}

			@Override
			public long getJavaHeap()
			{
				return 0;
			}

			@Override
			public int getLogLevel()
			{
				return 0;
			}

			@Override
			public long getNativeHeap()
			{
				return 0;
			}

			@Override
			public Net getNet()
			{
				return null;
			}

			@Override
			public Preferences getPreferences(String arg0)
			{
				return null;
			}

			@Override
			public ApplicationType getType()
			{
				return null;
			}

			@Override
			public int getVersion()
			{
				return 0;
			}

			@Override
			public void log(String arg0, String arg1)
			{
			}

			@Override
			public void log(String arg0, String arg1, Throwable arg2)
			{
			}

			@Override
			public void postRunnable(Runnable arg0)
			{
			}

			@Override
			public void removeLifecycleListener(LifecycleListener arg0)
			{
			}

			@Override
			public void setLogLevel(int arg0)
			{

			}
		}

		assertNull(Main.getApplication());

		Application app = new TestApp();

		Main.setApplication(app);
		assertSame(app, Main.getApplication());
	}
}
