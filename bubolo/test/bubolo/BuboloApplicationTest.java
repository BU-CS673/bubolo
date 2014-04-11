package bubolo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;

import bubolo.graphics.LibGdxAppTester;

public class BuboloApplicationTest
{
	private BuboloApplication ga;
	
	private boolean isComplete;
	private boolean passed;
	
	@Before
	public void setup()
	{
		LibGdxAppTester.createApp();
		ga = new BuboloApplication(500, 400);
	}
	
	@Test
	public void testIsReady()
	{
		assertFalse(ga.isReady());
		ga.setReady(true);
		assertTrue(ga.isReady());
	}

	@Test
	public void testCreate()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				try {
					ga.create();
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
	
	@Test
	public void testRender()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				try {
					ga.create();
					ga.render();
					passed = true;
				} catch (Exception e) {
					e.printStackTrace();
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

	@Test
	public void testDispose()
	{
		ga.dispose();
	}

	@Test
	public void pause()
	{
		ga.pause();
	}

	@Test
	public void resize()
	{
		ga.resize(600, 700);
	}

	@Test
	public void resume()
	{
		ga.resume();
	}
	
	@Test
	public void isGameStarted()
	{
		assertFalse(ga.isGameStarted());
	}
	
	@Test
	public void disposeTest()
	{
		ga.dispose();
	}
}
