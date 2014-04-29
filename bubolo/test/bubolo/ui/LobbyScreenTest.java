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
public class LobbyScreenTest
{
	private GameApplication app;
	private World world;
	private Network net;
	private LobbyScreen screen;
	
	private boolean isComplete;
	private boolean passed;

	@Before
	public void setup()
	{
		LibGdxAppTester.createApp();
		
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				app = mock(GameApplication.class);
				world = mock(World.class);
				
				net = NetworkSystem.getInstance();
				net.startDebug();
				net.startGame(world);
				
				screen = new LobbyScreen(app, world);
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

	/**
	 * Test method for {@link bubolo.ui.LobbyScreen#onConnect(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testOnConnect()
	{
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				try {
					screen.onConnect("Client", "Server");
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
	 * Test method for {@link bubolo.ui.LobbyScreen#onClientConnected(java.lang.String)}.
	 */
	@Test
	public void testOnClientConnected()
	{
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				try {
					screen.onClientConnected("Client");
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
	 * Test method for {@link bubolo.ui.LobbyScreen#onClientDisconnected(java.lang.String)}.
	 */
	@Test
	public void testOnClientDisconnected()
	{
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				try {
					screen.onClientDisconnected("Client");
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
	 * Test method for {@link bubolo.ui.LobbyScreen#onGameStart(int)}.
	 */
	@Test
	public void testOnGameStart()
	{
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				try {
					screen.onGameStart(1);
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
	 * Test method for {@link bubolo.ui.LobbyScreen#onMessageReceived(java.lang.String)}.
	 */
	@Test
	public void testOnMessageReceived()
	{
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				try {
					screen.onMessageReceived("Message");
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
