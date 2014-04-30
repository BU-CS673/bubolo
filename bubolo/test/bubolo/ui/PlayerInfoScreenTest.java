/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.ui;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;

import bubolo.GameApplication;
import bubolo.graphics.LibGdxAppTester;
import bubolo.net.Network;
import bubolo.net.NetworkSystem;
import bubolo.world.World;
import static org.mockito.Mockito.mock;

/**
 * @author BU CS673 - Clone Productions
 */
public class PlayerInfoScreenTest
{
	private GameApplication app;
	private Network net;
	private PlayerInfoScreen screen;
	
	private boolean isComplete;
	private boolean passed;

	@Before
	public void setup()
	{
		LibGdxAppTester.createApp();
		
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				app = mock(GameApplication.class);
				
				net = NetworkSystem.getInstance();
				net.startDebug();
				
				screen = new PlayerInfoScreen(app, true);
			}
		});
		
		isComplete = false;
		passed = false;
	}
	
	@Test
	public void update()
	{
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				try {
					screen.update(true);
					screen.update();
					passed = true;
				} catch (Exception e) {
					passed = false;
				} finally {
					isComplete = true;
				}
			}
		});
		
		while (!isComplete)
		{
			Thread.yield();
		}
		
		assertTrue(passed);
	}
	
	/**
	 * Test method for {@link bubolo.ui.LobbyScreen#dispose()}.
	 */
	@Test
	public void testDispose()
	{
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				try {
					screen.dispose();
					passed = true;
				} catch (Exception e) {
					passed = false;
				} finally {
					isComplete = true;
				}
			}
		});
		
		while (!isComplete)
		{
		Thread.yield();
		}
		
		assertTrue(passed);
	}
}
